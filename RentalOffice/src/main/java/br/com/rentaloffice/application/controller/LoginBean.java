package br.com.rentaloffice.application.controller;

import br.com.rentaloffice.application.resources.shiro.Credential;
import br.com.rentaloffice.application.resources.shiro.UserSession;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 03/10/2016
 */
@Named
@ViewScoped
public class LoginBean extends GenericBean {

    @Inject
    private UserSession userSession;

    @Getter
    private final Credential credential;

    /**
     *
     */
    public LoginBean() {
        this.credential = new Credential();
    }

    /**
     *
     * @return
     */
    public String initialize() {
        return this.userSession.isValid()
                ? "/secured/index.xhtml?faces-redirect=true" : "/index.xhtml";
    }

    /**
     *
     * @return
     */
    public String login() {
        this.userSession.login(this.credential);
        return "/secured/index.xhtml?faces-redirect=true";
    }
}
