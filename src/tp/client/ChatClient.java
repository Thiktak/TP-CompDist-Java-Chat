package tp.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import tp.rmi.IChatClient;
import tp.rmi.IChatServer;

/**
 *
 * @author Georges OLIVARES <dev@olivares-georges.fr>
 */
public class ChatClient extends UnicastRemoteObject implements IChatClient {

    private IChatServer server;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 42666);
            IChatServer iChatServer = (IChatServer) registry.lookup("server.n6chat");

            ChatClient iChatClient = new ChatClient();
            iChatClient.connect(iChatServer);
            iChatClient.send("test !");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ChatClient() throws RemoteException {
    }

    @Override
    public void connect(IChatServer server) throws RemoteException {
        Logger.getLogger(ChatClient.class.getName()).log(Level.INFO, "ChatClient connect to ChatServer");
        this.server = server;
        this.server.register(this);
    }

    @Override
    public void disconnect() throws RemoteException {
        this.server.unregister(this);
    }

    @Override
    public void send(String msg) throws RemoteException {
        this.server.dispatch(msg);
    }

    @Override
    public void receive(String msg) throws RemoteException {
        System.out.println(" > " + msg);
    }
}
