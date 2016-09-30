package br.eti.arthurgregorio.shirotest.dao;

import br.eti.arthurgregorio.shirotest.entities.Car;
import br.eti.arthurgregorio.shirotest.entities.User;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 30/09/2016
 */
public class UserDAO extends AbstractDAO<User> {

    @Override
    public User save(User data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User update(User data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(User data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> listAll() {
        return this.entityManager.createNamedQuery(
                "User.all", User.class).getResultList();
    }

    @Override
    public User findById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param username
     * @return 
     */
    public User findByUsername(String username) {
        return this.entityManager.createNamedQuery("User.byUsername", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
