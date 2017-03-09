package br.com.rentaloffice.model.dao;

import br.com.rentaloffice.model.entities.Group;
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
public class GroupDAO extends AbstractDAO<Group> {

    /**
     *
     * @return
     */
    @Override
    public List<Group> listAll() {
        return this.getQuery("Group.all", Group.class)
                .getResultList();
    }

    /**
     *
     * @param groupId
     * @return
     */
    @Override
    public Group findById(long groupId) {
        return this.getQuery("Group.byId", Group.class)
                .setParameter("id", groupId)
                .getSingleResult();
    }
}
