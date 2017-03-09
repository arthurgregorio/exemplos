package br.com.rentaloffice.model.dao;

import br.com.rentaloffice.model.entities.PersistentEntity;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @param <T>
 * 
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 30/09/2016
 */
public abstract class AbstractDAO<T extends PersistentEntity> {

    @Inject
    protected EntityManager entityManager;
    
    /**
     * 
     * @param data
     * @return 
     */
    public T save(T data) {
        return this.entityManager.merge(data);
    }
    
    /**
     * 
     * @param data
     * @return 
     */
    public T update(T data) {
        return this.entityManager.merge(data);
    }
    
    /**
     * 
     * @param data
     */
    public void delete(T data) {
        this.entityManager.remove(this.entityManager
                .getReference(data.getClass(), data.getId()));
    }
    
    /**
     * 
     * @return 
     */
    public List<T> listAll() {
        throw new UnsupportedOperationException("Not implemented yet...");
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public T findById(long id) { 
        throw new UnsupportedOperationException("Not implemented yet...");
    }
    
    /**
     * 
     * @return 
     */
    protected CriteriaBuilder getCriteriaBuilder() {
        return this.entityManager.getCriteriaBuilder();
    }
    
    /**
     * 
     * @param <E>
     * @param named
     * @param entity
     * @return 
     */
    protected <E> TypedQuery<E> getQuery(String named, Class<E> entity) {
        return this.entityManager.createNamedQuery(named, entity);
    }
}
