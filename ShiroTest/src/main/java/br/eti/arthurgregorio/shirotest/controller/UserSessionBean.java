package br.eti.arthurgregorio.shirotest.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 03/10/2016
 */
@Named
@ViewScoped
public class UserSessionBean extends GenericBean {

    @Inject
    private Subject subject;

    @Getter
    private final Credential credential;

    /**
     * 
     */
    public UserSessionBean() {
        this.credential = new Credential();
    }

    /**
     * 
     * @return 
     */
    public String initialize() {
        if (this.subject.isAuthenticated()) {
            return "/secured/index.xhtml?faces-redirect=true";
        } else {
            return "/index.xhtml";
        }
    }

    /**
     *
     * @return
     */
    public String login() {
        this.subject.login(this.credential.asToken());
        return "/secured/index.xhtml?faces-redirect=true";
    }
    
    /**
     * 
     * @return 
     */
    public String logout() {
        this.subject.logout();
        return "/index.xhtml?faces-redirect=true";
    }
}
