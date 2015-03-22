package votacaormi;

import java.util.Scanner;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0
 * @since 1.0, 22/03/2015
 */
public class Console {

    /**
     * 
     * @param console
     * @return 
     */
    public static String readCommand(Scanner console) {
        
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
}
