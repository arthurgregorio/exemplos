package filemover;

import java.awt.EventQueue;

/**
 *
 * @author Arthur Gregorio
 *
 * @since 1.0
 * @version 1.0, 14/01/2015
 */
public class Application {

    /**
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new PathChooserView().setVisible(true);
        });
    }
}
