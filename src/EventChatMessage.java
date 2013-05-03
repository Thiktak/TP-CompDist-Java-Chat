
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Georges OLIVARES <dev@olivares-georges.fr>
 */
public interface EventChatMessage {

    public void actionPerformed(IChatClient client, String msg) throws RemoteException;
}
