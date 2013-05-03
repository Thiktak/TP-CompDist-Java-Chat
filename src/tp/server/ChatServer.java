package tp.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tp.rmi.IChatClient;
import tp.rmi.IChatServer;

/**
 *
 * @author Georges OLIVARES <dev@olivares-georges.fr>
 */
public class ChatServer extends UnicastRemoteObject implements IChatServer {

    private ArrayList<IChatClient> clients;
    
    public static void main(String[] args) {
        try {
            Naming.rebind("server.n6chat", new ChatServer());
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ChatServer() throws RemoteException {
        this.clients = new ArrayList<>();
    }

    @Override
    public boolean register(IChatClient client) throws RemoteException {
        return this.clients.add(client);
    }

    @Override
    public boolean unregister(IChatClient client) throws RemoteException {
        return this.clients.remove(client);
    }

    @Override
    public void dispatch(String msg) throws RemoteException {
        for (IChatClient client : this.clients) {
            try {
                client.receive(msg);
            }
            catch( RemoteException ex){
                this.unregister(client);
            }
        }
    }
}
