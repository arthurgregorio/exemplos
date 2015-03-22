package votacaormi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashSet;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0
 * @since 1.0, 22/03/2015
 */
public interface IUrna extends Remote {
    
    /**
     * 
     * @return
     * @throws RemoteException 
     */
    public HashSet<Candidato> getCandidatos() throws RemoteException;
    
    /**
     * 
     * @param numero
     * @return
     * @throws RemoteException 
     */
    public boolean votar(Integer numero) throws RemoteException;
}
