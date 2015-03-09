package br.eti.arthurgregorio.fulljeearch.infrastructure;

import br.eti.arthurgregorio.fulljeearch.domain.entity.Person;
import br.eti.arthurgregorio.fulljeearch.domain.security.ApplicationRoles;
import br.eti.arthurgregorio.fulljeearch.domain.security.model.User;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.picketlink.event.PartitionManagerCreateEvent;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.Grant;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.Realm;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.query.IdentityQuery;
import org.slf4j.Logger;

/**
 * 
 * @author Arthur
 */
@Stateless
public class SecurityInitializer {
    
    @Inject
    private Logger logger;
    
    private Realm defaultRealm;
    
    /**
     * 
     * @param event 
     */
    public void configureDefaultPartition(@Observes PartitionManagerCreateEvent event) {
       
        final PartitionManager partitionManager = event.getPartitionManager();
                
        this.checkForDefaultRealm(partitionManager);
        this.checkForDefaultGroups(partitionManager);
        this.checkForDefaultRoles(partitionManager);
        this.checkForDefaultUsers(partitionManager);
    }
    
    /**
     * 
     * @param partitionManager 
     */
    private void checkForDefaultRealm(PartitionManager partitionManager) {

        this.defaultRealm = partitionManager.getPartition(
                Realm.class, Realm.DEFAULT_REALM);
        
        if (this.defaultRealm == null) {
            
            this.logger.info("Creating default realm");

            this.defaultRealm = new Realm(Realm.DEFAULT_REALM);
            
            partitionManager.add(this.defaultRealm);
        } 
    }
    
    /**
     * 
     * @param partitionManager 
     */
    private void checkForDefaultGroups(PartitionManager partitionManager) {
        
        final IdentityManager identityManager = 
                partitionManager.createIdentityManager(this.defaultRealm);
        
        final IdentityQuery<Group> query = identityManager
                .createIdentityQuery(Group.class);

        query.setParameter(Group.NAME, "Administradores");

        final List<Group> groups = query.getResultList();
        
        if (groups.isEmpty()) {
            
            this.logger.info("Creating default groups");
            
            identityManager.add(new Group("Administradores"));
        }
    }
    
    /**
     * 
     * @param partitionManager 
     */
    private void checkForDefaultRoles(PartitionManager partitionManager) {
        
        final IdentityManager identityManager = 
                partitionManager.createIdentityManager(this.defaultRealm);
        
        final IdentityQuery<Role> query = identityManager
                .createIdentityQuery(Role.class);

        query.setParameter(Role.NAME, ApplicationRoles.ADMINISTRATOR);

        List<Role> roles = query.getResultList();
        
        if (roles.isEmpty()) {
            
            this.logger.info("Creating default roles");
            
            identityManager.add(new Role(ApplicationRoles.ADMINISTRATOR));
        }
    }
    
    /**
     * 
     * @param partitionManager 
     */
    private void checkForDefaultUsers(PartitionManager partitionManager) {

        final IdentityManager identityManager = 
                partitionManager.createIdentityManager(this.defaultRealm);
        
        final IdentityQuery<User> query = identityManager
                .createIdentityQuery(User.class);

        query.setParameter(User.USER_NAME, "admin");

        List<User> users = query.getResultList();
        
        if (users.isEmpty()) {
            
            this.logger.info("Creating default users");
            
            final User user = new User("admin", new Person(
                    "Administrador", "admin@admin.com"));
            
            user.setCreatedDate(new Date());
            user.setEnabled(true);
            user.setExpirationDate(null);
            
            identityManager.add(user);

            identityManager.updateCredential(user, new Password("admin"));
            
            final RelationshipManager relationshipManager = 
                    partitionManager.createRelationshipManager();
            
            final IdentityQuery<Role> roleQuery = 
                    identityManager.createIdentityQuery(Role.class);

            roleQuery.setParameter(Role.NAME, ApplicationRoles.ADMINISTRATOR);
            
            final Role administratorRole = roleQuery.getResultList().get(0);
            
            relationshipManager.add(new Grant(user, administratorRole));
        }
    }    
}

