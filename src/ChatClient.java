
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.EventListener;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Georges OLIVARES <dev@olivares-georges.fr>
 */
public class ChatClient extends UnicastRemoteObject implements IChatClient {

    private EventChatMessage eventListener;
    private IChatServer server;
    public String name;

    public static void main(String[] args) {
        //try {
        //ChatClient iChatClient = new ChatClient();
        //iChatClient.name = args.length >= 2 ? args[1] : "G" + (int) (Math.random() * 10000);

        ChatClientUi.main(args);
        /*} catch (RemoteException ex) {
         Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
         }*/
    }

    public static IChatServer getInstance(String host, String name) {
        try {
            //Registry registry = LocateRegistry.getRegistry("127.0.0.1", 42666);

            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, "new client({0})", host);
            return (IChatServer) Naming.lookup(host);
        } catch (RemoteException | NotBoundException | MalformedURLException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

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
        this.server.dispatch(this.name + " : " + msg);
    }

    @Override
    public void receive(String msg) throws RemoteException {
        System.out.println(" > " + msg);
        this.eventListener.actionPerformed(this, msg);
    }

    @Override
    public void setReceivePerformed(EventChatMessage event) {
        this.eventListener = event;
    }
}
