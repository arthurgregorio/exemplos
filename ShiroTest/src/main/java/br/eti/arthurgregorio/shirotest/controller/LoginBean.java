package br.eti.arthurgregorio.shirotest.controller;

import br.eti.arthurgregorio.shirotest.utils.FacesUtils;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;

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
    private Logger logger;
    @Inject
    private Subject subject;
    
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
    public String login() {
        try {
            this.subject.login(this.credential.asToken());
        } catch (Exception ex) {
            FacesUtils.showError("Login failed, try again");
            this.logger.error("Login failed", ex);
            return "";
        }
        return "/secured/index.xhtml";
    }
}
