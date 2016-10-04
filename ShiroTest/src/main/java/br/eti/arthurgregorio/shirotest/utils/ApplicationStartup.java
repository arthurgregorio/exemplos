package br.eti.arthurgregorio.shirotest.utils;

import br.eti.arthurgregorio.shirotest.entities.Group;
import br.eti.arthurgregorio.shirotest.entities.GroupPermission;
import br.eti.arthurgregorio.shirotest.entities.Permission;
import br.eti.arthurgregorio.shirotest.entities.User;
import br.eti.arthurgregorio.shirotest.service.AccountService;
import br.eti.arthurgregorio.shirotest.utils.shiro.BCryptPasswordService;
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
        permissions.add(new Permission("car.access"));
        permissions.add(new Permission("car.edit"));
        permissions.add(new Permission("car.save"));
        permissions.add(new Permission("car.delete"));

        // owner permissions
        permissions.add(new Permission("owner.access"));
        permissions.add(new Permission("owner.edit"));
        permissions.add(new Permission("owner.save"));
        permissions.add(new Permission("owner.delete"));

        // save all the permissions
        permissions.stream().forEach(permission -> {
            this.accountService.savePermission(permission);
        });

        // cria o grupo e coloca as permissoes
        final Group group = new Group();

        group.setName("Administrators");

        permissions = this.accountService.listAllPermissions();

        permissions.stream().forEach(permission -> {
            group.addPermissions(new GroupPermission(null, permission));
        });

        final Group savedGroup = this.accountService.saveGroup(group);

        // create the admin user
        final User user = new User();

        user.setName("Administrator");
        user.setUsername("admin");
        user.setEnabled(true);
        user.setPassword(this.passwordService.encryptPassword("admin"));
        
        user.setGroup(savedGroup);
        
        this.accountService.saveUser(user);
    }
}
