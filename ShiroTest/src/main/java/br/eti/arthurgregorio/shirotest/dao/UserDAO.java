package br.eti.arthurgregorio.shirotest.dao;

import br.eti.arthurgregorio.shirotest.entities.User;
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
public class UserDAO extends AbstractDAO<User> {

    @Override
    public List<User> listAll() {
        return this.getQuery("User.all", User.class)
                .getResultList();
    }

    @Override
    public User findById(long id) {
        return this.getQuery("User.byId", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    /**
     * 
     * @param username
     * @return 
     */
    public User findByUsername(String username) {
        return this.getQuery("User.byUsername", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
