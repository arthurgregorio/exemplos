package br.eti.arthurgregorio.shirotest.utils.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The EntityManager producer
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 13/07/2015
 */
@ApplicationScoped
public class EntityManagerProducer {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @return the entity manager
     */
    @Produces
    @RequestScoped
    EntityManager produce() {
        return this.entityManager;
    }
}
