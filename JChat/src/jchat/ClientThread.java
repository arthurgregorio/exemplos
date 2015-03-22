package jchat;

import java.net.Socket;
import java.util.Scanner;

/**
 * Herdamos de Thread para deixar o cliente receber varias conexoes simultaneas
 *
 * @author Arthur Gregorio
 *
 * @version 1.0
 * @since 1.0, 04/02/2015
 */
public class ClientThread extends Thread {

    private final Socket socket;
    private final String clientAddress;

    /**
     * Cria a thread e liga ela a um socket 
     * 
     * @param socket o socket que vamos escutar
     * @param server o ip do servidor
     */
    public ClientThread(Socket socket, String server) {
        super("JChatClientThread");

        this.socket = socket;
        this.clientAddress = socket.getInetAddress().getHostAddress();

        System.out.println(String.format("server@%s$> novo cliente "
                + "conectado de: %s", server, this.clientAddress));
    }

    /**
     * Cada cliente conectado fica travado aqui, enquanto houver texto no fluxo
     * de dados (input) ele le os dados e loga no console
     */
    @Override
    public void run() {

        try {
            final Scanner input = new Scanner(this.socket.getInputStream());

            while (input.hasNextLine()) {
                final String[] message = input.nextLine().split(":");
                System.out.println(String.format("%s@%s$> %s",
                        message[0], this.clientAddress, message[1]));
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
