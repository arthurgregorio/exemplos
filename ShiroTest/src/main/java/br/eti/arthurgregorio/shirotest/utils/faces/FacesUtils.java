package br.eti.arthurgregorio.shirotest.utils.faces;

import org.omnifaces.util.Messages;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 03/10/2016
 */
public class FacesUtils {

    /**
     * 
     * @param message 
     */
    public static void showError(String message) {
        Messages.addError(null, message);
    }
    
    /**
     * 
     * @param message 
     */
    public static void showInfo(String message) {
        Messages.addInfo(null, message);
    }
}
