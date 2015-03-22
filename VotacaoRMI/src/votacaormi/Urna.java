package votacaormi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0
 * @since 1.0, 22/03/2015
 */
public class Urna extends UnicastRemoteObject implements IUrna {

    private HashMap<Candidato, Integer> votos;

    /**
     * 
     * @throws RemoteException 
     */
    public Urna() throws RemoteException { }
    
    /**
     * 
     * @return
     * @throws RemoteException 
     */
    @Override
    public HashSet<Candidato> getCandidatos() throws RemoteException {
        return new HashSet<>(this.votos.keySet());
    }
    
    /**
     * 
     * @param numero
     * @return
     * @throws RemoteException 
     */
    @Override
    public boolean votar(Integer numero) throws RemoteException {
        
        this.votos.keySet().stream().filter((candidato) -> 
                (candidato.getNumero().equals(numero))).forEach((candidato) -> {
            this.votos.put(candidato, this.votos.get(candidato) + 1);
        });
        
        return true;
    }

    /**
     * 
     * @param candidato 
     */
    public void adicionaCandidato(Candidato candidato) {
        
        if (this.votos == null) {
            this.votos = new HashMap<>();
        }
        
        this.votos.put(candidato, 0);
    }

    /**
     * 
     */
    public void calcularParcial() {
        
        System.out.println("\nurna@localhost$> Parcial: ");
        
        this.votos.keySet().stream().forEach((candidato) -> {
            System.out.println(String.format("Candidato %s "
                    + "recebeu %s votos", candidato.getNome(), this.votos.get(candidato)));
        });
    }
}
