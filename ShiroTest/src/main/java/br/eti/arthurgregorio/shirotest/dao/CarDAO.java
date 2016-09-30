package br.eti.arthurgregorio.shirotest.dao;

import br.eti.arthurgregorio.shirotest.entities.Car;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 13/07/2015
 */
@Dependent
public class CarDAO extends AbstractDAO<Car> {

    /**
     * 
     * @param data
     * @return 
     */
    @Override
    @Transactional
    public Car save(Car data) {
        return this.entityManager.merge(data);
    }

    /**
     * 
     * @param data
     * @return 
     */
    @Override
    @Transactional
    public Car update(Car data) {
        return this.entityManager.merge(data);
    }

    /**
     *
     * @param data
     */
    @Override
    @Transactional
    public void delete(Car data) {
        this.entityManager.remove(this.entityManager
                .getReference(Car.class, data.getId()));
    }

    /**
     *
     * @return
     */
    @Override
    public List<Car> listAll() {

        final CriteriaBuilder builder = this.getCriteriaBuilder();

        final CriteriaQuery criteria = builder.createQuery(Car.class);

        criteria.select(criteria.from(Car.class));

        final TypedQuery<Car> query = this.entityManager.createQuery(criteria);

        return query.getResultList();
    }

    /**
     *
     * @param carroId
     * @return
     */
    @Override
    public Car findById(long carroId) {

        final CriteriaBuilder builder = this.getCriteriaBuilder();

        final CriteriaQuery<Car> criteria = builder.createQuery(Car.class);
        final Root<Car> root = criteria.from(Car.class);

        final ParameterExpression<Long> parameter = builder.parameter(Long.class);
        
        criteria.select(root)
                .where(builder.equal(root.get("id"), parameter));

        final TypedQuery<Car> query = this.entityManager.createQuery(criteria);
        
        query.setParameter(parameter, carroId);

        return query.getSingleResult();
    }
}
