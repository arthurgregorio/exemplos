package jchat;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * O cliente, um console basico de execucao
 *
 * @author Arthur Gregorio
 *
 * @version 1.1
 * @since 1.0, 06/05/2009
 */
public class Client implements Runnable {

    private static final int PORTA = 4444;
    private static final String SERVIDOR = "localhost";

    /**
     * Invocado via queue de eventos
     */
    @Override
    public void run() {
        this.startClient();
    }

    /**
     * Invoca o mundo magico do java
     *
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Client());
    }

    /**
     * Inicia a magia dos sockets com um chat estilo IRC
     */
    private void startClient() {

        try {
            final PrintWriter output = this.getSocketOutput();

            boolean exit = false;

            final Scanner console = new Scanner(System.in);

            System.out.println("## Bem vindo ao JChat V1D4 L0K4 ##\n");

            String nick = this.readNick(console);

            System.out.println("\n## Comandos ##");
            System.out.println("/quit = sair");
            System.out.println("/nick = trocar nick\n");

            while (!exit) {
                System.out.print(String.format("%s digite algo $> ", nick));

                final String command = this.readCommand(console);
                
                switch (command) {
                    case "/quit":
                        System.err.println("\nSaindo!");
                        System.exit(0);
                        break;
                    case "/nick":
                        final String oldNick = nick;
                        nick = this.readNick(console);
                        final String message = String.format(
                                "%s trocou o nick para %s", oldNick, nick);
                        output.println(String.format("%s:%s", oldNick, message));
                        break;
                    default:
                        output.println(String.format("%s:%s", nick, command));
                        break;
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    /**
     * Forca o caboclo a ter um nick
     *
     * @param console o console de leitura
     * @return o nick
     */
    private String readNick(Scanner console) {

        String nick = "";
        boolean invalid = true;

        while (invalid) {
            System.out.print("Informe um nick $> ");
            nick = console.next();

            if (!nick.isEmpty()) {
                invalid = false;
            }
        }
        return nick;
    }

    /**
     * Le os comandos enviados no console, se o comando for valido, devolve 
     * para que este seja processado pelo processador de comandos
     * 
     * @param console o console para fazer a leitura dos comandos
     * @return o comando, se for valido
     */
    public String readCommand(Scanner console) {
        
        String command = "";
        boolean invalid = true;

        while (invalid) {
            command = console.nextLine();

            if (!command.isEmpty()) {
                invalid = false;
            }
        }
        return command;
    }

    /**
     * Cria o scket e direciona o fluxo de escrita
     */
    private PrintWriter getSocketOutput() throws IOException {
        final Socket cliente = new Socket(SERVIDOR, PORTA);
        return new PrintWriter(cliente.getOutputStream(), true);
    }
}
