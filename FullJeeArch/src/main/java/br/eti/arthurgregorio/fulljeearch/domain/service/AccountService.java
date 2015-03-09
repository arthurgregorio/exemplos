package br.eti.arthurgregorio.fulljeearch.domain.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.query.IdentityQuery;

/**
 * 
 * @author Arthur
 */
@Stateless
public class AccountService {
    
    @Inject
    private IdentityManager identityManager;

    /**
     * 
     * @param roleName
     * @return 
     */
    public Role createRole(String roleName) {
        
        final IdentityQuery<Role> query = this.identityManager.createIdentityQuery(Role.class);
        
        query.setParameter(Role.NAME, roleName);
        
        final Role role;
        
        if (query.getResultList().isEmpty()) {
            role = new Role(roleName);
            this.identityManager.add(role);
        } else {
            role = query.getResultList().get(0);
        }
        
        return role;
    }
}
