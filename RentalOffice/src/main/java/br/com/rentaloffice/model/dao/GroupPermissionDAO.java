package br.com.rentaloffice.model.dao;

import br.com.rentaloffice.model.entities.Group;
import br.com.rentaloffice.model.entities.GroupPermission;
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
public class GroupPermissionDAO extends AbstractDAO<GroupPermission> {

    /**
     *
     * @return
     */
    @Override
    public List<GroupPermission> listAll() {
        return this.getQuery("GroupPermission.all", GroupPermission.class)
                .getResultList();        
    }

    /**
     *
     * @param groupPermissionId
     * @return
     */
    @Override
    public GroupPermission findById(long groupPermissionId) {
        return this.getQuery("GroupPermission.byId", GroupPermission.class)
                .setParameter("id", groupPermissionId)
                .getSingleResult();
    }

    /**
     *
     * @param group
     * @return
     */
    public List<GroupPermission> listByGroup(Group group) {
        return this.getQuery("GroupPermission.byGroup", GroupPermission.class)
                .setParameter("group", group)
                .getResultList();
    }
}
