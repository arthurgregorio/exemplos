package br.eti.arthurgregorio.shirotest.service;

import br.eti.arthurgregorio.shirotest.dao.GroupDAO;
import br.eti.arthurgregorio.shirotest.dao.RoleDAO;
import br.eti.arthurgregorio.shirotest.dao.UserDAO;
import br.eti.arthurgregorio.shirotest.entities.User;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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
    private RoleDAO roleDAO;
    @Inject
    private GroupDAO groupDAO;

    /**
     * 
     * @param username
     * @return 
     */
    public User findUserByUsername(String username) {
        return this.userDAO.findByUsername(username);
    }
}
