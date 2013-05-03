package tp.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import tp.rmi.IChatClient;
import tp.rmi.IChatServer;

/**
 *
 * @author Georges OLIVARES <dev@olivares-georges.fr>
 */
public class ChatClient extends UnicastRemoteObject implements IChatClient {

    public static void main(String[] args) {
        ChatClientUi.main(args);
    }

    public ChatClient() throws RemoteException {
    }

    @Override
    public void connect(IChatServer server) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void disconnect() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void send(String msg) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
