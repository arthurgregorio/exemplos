package br.eti.arthurgregorio.shirotest.service;

import br.eti.arthurgregorio.shirotest.dao.GroupDAO;
import br.eti.arthurgregorio.shirotest.dao.GroupPermissionDAO;
import br.eti.arthurgregorio.shirotest.dao.PermissionDAO;
import br.eti.arthurgregorio.shirotest.dao.UserDAO;
import br.eti.arthurgregorio.shirotest.entities.Group;
import br.eti.arthurgregorio.shirotest.entities.GroupPermission;
import br.eti.arthurgregorio.shirotest.entities.Permission;
import br.eti.arthurgregorio.shirotest.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 30/09/2016
 */
@ApplicationScoped
public class AccountService {

    @Inject
    private UserDAO userDAO;
    @Inject
    private GroupDAO groupDAO;
    @Inject
    private PermissionDAO permissionDAO;
    @Inject
    private GroupPermissionDAO groupPermissionDAO;
    
    /**
     * 
     * @param user
     * @return 
     */
    @Transactional
    public User saveUser(User user) {
        return this.userDAO.save(user);
    }
    
    /**
     * 
     * @param permission 
     */
    @Transactional
    public void savePermission(Permission permission) {
        this.permissionDAO.save(permission);
    }
    
    /**
     * 
     * @param group
     * @return 
     */
    @Transactional
    public Group saveGroup(Group group) {
    
        final List<GroupPermission> permissions = group.getGroupPermissions();
        
        final Group savedGroup = this.groupDAO.save(group);
        
        permissions.stream().forEach(permission -> {
            this.groupPermissionDAO.save(
                    new GroupPermission(savedGroup, permission.getPermission()));
        });
        
        return savedGroup;
    }

    /**
     * 
     * @param username
     * @return 
     */
    public User findUserByUsername(String username) {
        return this.userDAO.findByUsername(username);
    }
    
    /**
     * 
     * @param username
     * @return 
     */
    public Group findUserGroup(String username) {
       final User user = this.userDAO.findByUsername(username);
       return user != null ? user.getGroup() : null;
    }
    
    /**
     * 
     * @param username
     * @return 
     */
    public List<GroupPermission> loadUserPermissions(String username) {
       
        final Group group = this.findUserGroup(username);
        
        List<GroupPermission> groupPermissions = new ArrayList<>();
        
        if (group != null) {
            groupPermissions = new ArrayList<>(
                    this.groupPermissionDAO.listByGroup(group));
        }
        return groupPermissions;
    }
    
    /**
     * 
     * @return 
     */
    public List<Permission> listAllPermissions() {
        return this.permissionDAO.listAll();
    }
}
