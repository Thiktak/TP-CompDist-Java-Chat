import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * @author Georges OLIVARES <dev@olivares-georges.fr>
 */
public interface IChatServer extends Remote {

    public boolean register(IChatClient client) throws RemoteException;

    public boolean unregister(IChatClient client) throws RemoteException;

    public void dispatch(String msg) throws RemoteException;
}
