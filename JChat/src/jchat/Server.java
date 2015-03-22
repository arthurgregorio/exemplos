package jchat;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * O servidor, a mae de santo da parada, recebe tudo e despacha para uma thread
 *
 * @author Arthur Gregorio
 *
 * @version 1.1
 * @since 1.0, 06/05/2009
 */
public class Server implements Runnable {

    private static final int PORT = 4444;
    
    /**
     * Invocado via queue de eventos
     */
    @Override
    public void run() {
        this.startServer();
    }
    
    /**
     * Invoca o mundo magico do java
     * 
     * @param args 
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Server());
    }
    
    /**
     * Inicia o server e a cada nova conexcao instancia uma thread para fazer 
     * o trampo de mostrar as mensagens
     */
    private void startServer() {

        System.out.println("## Server iniciado e esperando mensagens! ##\n");
        
        try (ServerSocket server = new ServerSocket(PORT)) { 
            while (true) {
                new ClientThread(server.accept(), 
                        server.getInetAddress().getHostAddress()).start();
            }
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
    }
}
