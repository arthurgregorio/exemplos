package br.com.rentaloffice.model.dao;

import br.com.rentaloffice.model.entities.User;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.NoResultException;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 30/09/2016
 */
@Dependent
public class UserDAO extends AbstractDAO<User> {

    @Override
    public List<User> listAll() {
        return this.getQuery("User.all", User.class)
                .getResultList();
    }

    @Override
    public User findById(long id) {
        try {
            return this.getQuery("User.byId", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    /**
     * 
     * @param username
     * @return 
     */
    public User findByUsername(String username) {
        try {
            return this.getQuery("User.byUsername", User.class)
                .setParameter("username", username)
                .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
