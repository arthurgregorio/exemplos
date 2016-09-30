package br.eti.arthurgregorio.shirotest.dao;

import br.eti.arthurgregorio.shirotest.entities.Owner;
import java.util.List;
import javax.enterprise.context.Dependent;
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
@Dependent
public class OwnerDAO {

    @Inject
    private EntityManager entityManager;

    /**
     *
     * @param proprietario
     * @return
     */
    @Transactional
    public Owner salvar(Owner proprietario) {
        return this.entityManager.merge(proprietario);
    }

    /**
     *
     * @param proprietario
     */
    @Transactional
    public void remover(Owner proprietario) {
        this.entityManager.remove(this.entityManager.getReference(Owner.class, proprietario.getId()));
    }

    /**
     *
     * @return
     */
    public List<Owner> listarTodos() {

        final CriteriaBuilder builder = this.getBuilder();

        final CriteriaQuery criteria = builder.createQuery(Owner.class);

        criteria.select(criteria.from(Owner.class));

        final TypedQuery<Owner> query = this.entityManager.createQuery(criteria);

        return query.getResultList();
    }

    /**
     *
     * @param proprietarioId
     * @return
     */
    public Owner buscarPorId(long proprietarioId) {

        final CriteriaBuilder builder = this.getBuilder();

        final CriteriaQuery<Owner> criteria = builder.createQuery(Owner.class);
        final Root<Owner> root = criteria.from(Owner.class);

        final ParameterExpression<Long> parameter = builder.parameter(Long.class);
        
        criteria.select(root)
                .where(builder.equal(root.get("id"), parameter));

        final TypedQuery<Owner> query = this.entityManager.createQuery(criteria);
        
        query.setParameter(parameter, proprietarioId);

        return query.getSingleResult();
    }

    /**
     * @return o builder de criterias
     */
    public CriteriaBuilder getBuilder() {
        return this.entityManager.getCriteriaBuilder();
    }
}
