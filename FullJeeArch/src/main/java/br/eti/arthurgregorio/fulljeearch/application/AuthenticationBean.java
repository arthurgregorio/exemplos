package br.eti.arthurgregorio.fulljeearch.application;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;
import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;

/**
 * 
 * @author Arthur
 */
@Named
@RequestScoped
public class AuthenticationBean implements Serializable {

    @Inject
    private Identity identity;

    /**
     * 
     * @return 
     */
    public String login() {
       
        final AuthenticationResult result = this.identity.login();
        
        if (AuthenticationResult.FAILED.equals(result)) {
            Messages.addError(null, "Falha no login!");
            return null;
        } 

        return "/secured/home.xhtml";
    }
}
