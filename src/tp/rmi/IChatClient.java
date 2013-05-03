package tp.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Georges OLIVARES <dev@olivares-georges.fr>
 */
public interface IChatClient extends Remote {

    public void connect(IChatServer server) throws RemoteException;

    public void disconnect() throws RemoteException;

    public void send(String msg) throws RemoteException;
}
