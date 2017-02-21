package br.eti.arthurgregorio.shirotest.application.resources.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

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

    @PersistenceUnit
    private EntityManagerFactory factory;

    /**
     *
     * @return
     */
    @Produces
    EntityManager produce() {
        return this.factory.createEntityManager();
    }

    /**
     * Encerra um entityManager ja utilizado pelo sistema
     *
     * @param entityManager o entity manager a ser encerrado
     */
    void dispose(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.clear();
            entityManager.close();
        }
    }
}
