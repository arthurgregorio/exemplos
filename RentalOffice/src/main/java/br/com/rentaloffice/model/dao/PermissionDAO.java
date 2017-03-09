package br.com.rentaloffice.model.dao;

import br.com.rentaloffice.model.entities.Permission;
import java.util.List;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 30/09/2016
 */
@Dependent
public class PermissionDAO extends AbstractDAO<Permission> {

    /**
     *
     * @return
     */
    @Override
    public List<Permission> listAll() {
        return this.getQuery("Permission.all", Permission.class)
                .getResultList();        
    }

    /**
     *
     * @param permissionId
     * @return
     */
    @Override
    public Permission findById(long permissionId) {
        return this.getQuery("Permission.byId", Permission.class)
                .setParameter("id", permissionId)
                .getSingleResult();
    }
}
