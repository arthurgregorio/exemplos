package br.com.rentaloffice.infrastructure;

import br.com.rentaloffice.model.entities.Group;
import br.com.rentaloffice.model.entities.Permission;
import br.com.rentaloffice.model.entities.User;
import br.com.rentaloffice.model.service.AccountService;
import br.com.rentaloffice.application.resources.shiro.BCryptPasswordService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * The application startup configuration
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
    @Inject
    private BCryptPasswordService passwordService;

    /**
     * Initialize the basics for this system
     */
    @PostConstruct
    public void initialize() {

        // if can't find the admin, create one
        final User user = this.accountService.findUserByUsername("admin");
        
        if (user == null) {
            this.createDefaultCredentials();
        }
    }

    /**
     * Create the default credentials for the application
     */
    private void createDefaultCredentials() {

        List<Permission> permissions = new ArrayList<>();

        // car permissions
        permissions.add(new Permission("car","access"));
        permissions.add(new Permission("car","edit"));
        permissions.add(new Permission("car","save"));
        permissions.add(new Permission("car","delete"));

        // owner permissions
        permissions.add(new Permission("owner","access"));
        permissions.add(new Permission("owner","edit"));
        permissions.add(new Permission("owner","save"));
        permissions.add(new Permission("owner","delete"));

        // save all the permissions
        permissions.forEach(permission -> {
            this.accountService.savePermission(permission);
        });

        // list all ther permissions saved before
        permissions = this.accountService.listAllPermissions();
        
        // create a administration group and put the permissions on this group
        final Group group = this.accountService.saveGroup(
                new Group("Administrators", permissions));

        // create the admin user
        final User user = new User();

        user.setName("Administrator");
        user.setUsername("admin");
        user.setEnabled(true);
        user.setPassword(this.passwordService.encryptPassword("admin"));
        
        user.setGroup(group);
        
        this.accountService.saveUser(user);
        
        // create other admin user
        final User arthur = new User();

        arthur.setName("Arthur Gregório");
        arthur.setUsername("arthur");
        arthur.setEnabled(true);
        arthur.setPassword(this.passwordService.encryptPassword("ruhtra"));
        
        arthur.setGroup(group);
        
        this.accountService.saveUser(arthur);
    }
}
