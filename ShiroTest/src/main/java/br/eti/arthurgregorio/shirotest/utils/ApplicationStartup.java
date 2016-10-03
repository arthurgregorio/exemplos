package br.eti.arthurgregorio.shirotest.utils;

import br.eti.arthurgregorio.shirotest.service.AccountService;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 03/10/2016
 */
@Startup
@Singleton
public class ApplicationStartup {
    
    @Inject
    private AccountService accountService;
    
    /**
     * 
     */
    @PostConstruct
    public void initialize() {
        this.createDefaultPermissions();
        this.createDefaultGroup();
        this.createDefaultUser();
    }
    
    /**
     * 
     */
    private void createDefaultPermissions() {
    
    }
    
    /**
     * 
     */
    private void createDefaultGroup() {
    
    }
    
    /**
     * 
     */
    private void createDefaultUser() {
    
    }
}
