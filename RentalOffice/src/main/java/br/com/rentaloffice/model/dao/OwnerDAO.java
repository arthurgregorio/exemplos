package br.com.rentaloffice.model.dao;

import br.com.rentaloffice.model.entities.Owner;
import java.util.List;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 13/07/2015
 */
@Dependent
public class OwnerDAO extends AbstractDAO<Owner> {

    /**
     *
     * @return
     */
    @Override
    public List<Owner> listAll() {
        return this.getQuery("Owner.all", Owner.class)
                .getResultList();        
    }

    /**
     *
     * @param ownerId
     * @return
     */
    @Override
    public Owner findById(long ownerId) {
        return this.getQuery("Owner.byId", Owner.class)
                .setParameter("id", ownerId)
                .getSingleResult();
    }
}
