package br.eti.arthurgregorio.fulljeearch.application.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.picketlink.annotations.PicketLink;

/**
 * 
 * @author Arthur
 */
public class EntityManagerProducer {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    /**
     * 
     * @return 
     */
    @Produces
    @PicketLink
    @ApplicationScoped
    EntityManager produceEntityManager() {
        return this.entityManager;
    }
}
