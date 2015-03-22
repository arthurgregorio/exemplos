package votacaormi;

import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Arthur Gregorio
 *
 * @since 1.0
 * @version 1.0, 22/03/2015
 */
public class Servidor implements Runnable {

    private Urna urna;
    private ScheduledExecutorService executorService;
    
    /**
     *
     */
    @Override
    public void run() {
        
        try {
            this.urna = this.prepararUrna();
            
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi:///Urna", this.urna);
            
            System.out.println("urna@localhost$> Urna iniciada, pronta para receber votos!");
        } catch (RemoteException | MalformedURLException ex) {
            System.err.println(ex);
            System.exit(1);
        }

        this.iniciarApurador();
        this.escutarComandos();
    }

    /**
     *
     * @return
     */
    private Urna prepararUrna() throws RemoteException {

        final Urna urnaVazia = new Urna();

        System.out.println("## Iniciando Urna ##\n");
        
        System.out.println("urna@localhost$> Informe os candidados desta eleição seguinte o seguinte esquema:");
        System.out.println("urna@localhost$> nome:numero,nome:numero ...");
        System.out.println("urna@localhost$> Quando inserir todos, tecle [enter] para gravar");
        System.out.print("urna@localhost$> ");
        
        final String comando = Console.readCommand(new Scanner(System.in));
        
        if (comando != null && !comando.isEmpty()) {
            
            final String[] arrayCandidatos = comando.split(",");
            
            for (String dadosCandidato : arrayCandidatos) {
                
                final String nome = dadosCandidato.split(":")[0];
                final String numero = dadosCandidato.split(":")[1];
                
                urnaVazia.adicionaCandidato(new Candidato(nome, 
                        Integer.parseInt(numero)));
            }
        }
        
        this.mostrarCandidatos(urnaVazia);
        
        return urnaVazia;
    }

    /**
     * 
     */
    private void escutarComandos() {

        boolean exit = false;
        
        try {
            final Scanner teclado = new Scanner(System.in);

            System.out.println("\n## Comandos ##");
            System.out.println("/candidatos = ver candidatos");
            System.out.println("/finalizar = fecha votacao e contabiliza\n");

            while (!exit) {
                System.out.print("urna@localhost$> ");

                final String comando = Console.readCommand(teclado);

                switch (comando) {
                    case "/finalizar":
                        this.urna.calcularParcial();
                        this.executorService.shutdown();
                        System.exit(0);
                        break;
                    case "/candidatos":
                        this.mostrarCandidatos(this.urna);
                        break;
                    default:
                        System.err.println("urna@localhost$> Comando desconhecido!");
                        break;
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    /**
     * 
     */
    private void iniciarApurador() {
       
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        
        this.executorService.scheduleAtFixedRate(() -> 
            { urna.calcularParcial(); }, 5, 5, TimeUnit.SECONDS);
    }
    
    /**
     * 
     * @throws Exception 
     */
    private void mostrarCandidatos(Urna urna) throws RemoteException {

        System.out.println("urna@localhost$> Candidatos disponiveis para votação:");
        
        final Set<Candidato> candidatos = urna.getCandidatos();
        
        candidatos.stream().forEach((candidato) -> {
            System.out.println(String.format("-> Nome %s, número %s", 
                    candidato.getNome(), candidato.getNumero()));
        });
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Servidor());
    }
}
