package br.eti.arthurgregorio.shirotest.dao;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
public abstract class AbstractDAO<T> {

    @Inject
    protected EntityManager entityManager;
    
    /**
     * 
     * @param data
     * @return 
     */
    public abstract T save(T data);
    
    /**
     * 
     * @param data
     * @return 
     */
    public abstract T update(T data);
    
    /**
     * 
     * @param data
     */
    public abstract void delete(T data);
    
    /**
     * 
     * @return 
     */
    public abstract List<T> listAll();
    
    /**
     * 
     * @param id
     * @return 
     */
    public abstract T findById(long id);
    
    /**
     * 
     * @return 
     */
    protected CriteriaBuilder getCriteriaBuilder() {
        return this.entityManager.getCriteriaBuilder();
    }
}
