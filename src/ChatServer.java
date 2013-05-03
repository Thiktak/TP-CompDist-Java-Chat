import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Georges OLIVARES <dev@olivares-georges.fr>
 */
public class ChatServer extends UnicastRemoteObject implements IChatServer {

    private ArrayList<IChatClient> clients;

    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            Naming.rebind("n6chat", server);

            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ChatServer() throws RemoteException {
        this.clients = new ArrayList<>();
    }

    @Override
    public boolean register(IChatClient client) throws RemoteException {
        System.out.println("Welcome");
        return this.clients.add(client);
    }

    @Override
    public boolean unregister(IChatClient client) throws RemoteException {
        System.out.println("Goodbye");
        return this.clients.remove(client);
    }

    @Override
    public void dispatch(String msg) throws RemoteException {
        for (IChatClient client : this.clients) {
            try {
                client.receive(msg);
            } catch (RemoteException ex) {
                this.unregister(client);
            }
        }
    }
}
