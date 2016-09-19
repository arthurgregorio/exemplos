package br.eti.arthurgregorio.shirotest.dao;

import br.eti.arthurgregorio.shirotest.entities.Car;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
@RequestScoped
public class CarDAO {

    @Inject
    private EntityManager entityManager;

    /**
     *
     * @param carro
     * @return
     */
    @Transactional
    public Car salvar(Car carro) {
        return this.entityManager.merge(carro);
    }

    /**
     *
     * @param carro
     */
    @Transactional
    public void remover(Car carro) {
        this.entityManager.remove(this.entityManager.getReference(Car.class, carro.getId()));
    }

    /**
     *
     * @return
     */
    public List<Car> listarTodos() {

        final CriteriaBuilder builder = this.getBuilder();

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
    public Car buscarPorId(long carroId) {

        final CriteriaBuilder builder = this.getBuilder();

        final CriteriaQuery<Car> criteria = builder.createQuery(Car.class);
        final Root<Car> root = criteria.from(Car.class);

        final ParameterExpression<Long> parameter = builder.parameter(Long.class);
        
        criteria.select(root)
                .where(builder.equal(root.get("id"), parameter));

        final TypedQuery<Car> query = this.entityManager.createQuery(criteria);
        
        query.setParameter(parameter, carroId);

        return query.getSingleResult();
    }

    /**
     * @return o builder de criterias
     */
    public CriteriaBuilder getBuilder() {
        return this.entityManager.getCriteriaBuilder();
    }
}
