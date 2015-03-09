package br.eti.arthurgregorio.fulljeearch.infrastructure;

import br.eti.arthurgregorio.fulljeearch.domain.security.model.User;
import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;
import javax.enterprise.event.Observes;
import org.picketlink.idm.credential.encoder.BCryptPasswordEncoder;
import org.picketlink.idm.credential.handler.PasswordCredentialHandler;

/**
 *
 * @author Arthur
 */
public class SecurityConfiguration {

    /**
     * 
     * @param event 
     */
    public void configureIdentityManager(@Observes SecurityConfigurationEvent event) {

        final SecurityConfigurationBuilder builder = event.getBuilder();
        
        builder.idmConfig()
                .named("jpa.config")
                .stores()
                .jpa()
                .supportType(User.class)
                .setCredentialHandlerProperty(
                        PasswordCredentialHandler.PASSWORD_ENCODER, 
                        new BCryptPasswordEncoder(6))
                .supportAllFeatures();
    }
    
    /**
     * 
     * @param event 
     */
    public void configureHttpSecurity(@Observes SecurityConfigurationEvent event) {
        
        final SecurityConfigurationBuilder builder = event.getBuilder();

        builder.http()
                .allPaths()
                .authenticateWith()
                .form()
                .loginPage("/home.xhtml")
                .errorPage("/home.xhtml?login-failed=true")
                .forPath("/logout.xhtml")
                .logout()
                .redirectTo("/home.xhtml?faces-redirect=true")
                .forPath("/javax.faces.resource/*")
                .unprotected();
    }
}
