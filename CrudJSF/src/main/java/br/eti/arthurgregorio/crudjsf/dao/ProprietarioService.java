package br.eti.arthurgregorio.crudjsf.dao;

import br.eti.arthurgregorio.crudjsf.entities.Proprietario;
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
public class ProprietarioService {

    @Inject
    private EntityManager entityManager;

    /**
     *
     * @param proprietario
     * @return
     */
    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        return this.entityManager.merge(proprietario);
    }

    /**
     *
     * @param proprietario
     */
    @Transactional
    public void remover(Proprietario proprietario) {
        this.entityManager.remove(this.entityManager.getReference(
                Proprietario.class, proprietario.getId()));
    }

    /**
     *
     * @return
     */
    public List<Proprietario> listarTodos() {

        final CriteriaBuilder builder = this.getBuilder();

        final CriteriaQuery criteria = builder.createQuery(Proprietario.class);

        criteria.select(criteria.from(Proprietario.class));

        final TypedQuery<Proprietario> query = this.entityManager.createQuery(criteria);

        return query.getResultList();
    }

    /**
     *
     * @param proprietarioId
     * @return
     */
    public Proprietario buscarPorId(long proprietarioId) {

        final CriteriaBuilder builder = this.getBuilder();

        final CriteriaQuery<Proprietario> criteria = builder.createQuery(Proprietario.class);
        final Root<Proprietario> root = criteria.from(Proprietario.class);

        final ParameterExpression<Long> parameter = builder.parameter(Long.class);
        
        criteria.select(root)
                .where(builder.equal(root.get("id"), parameter));

        final TypedQuery<Proprietario> query = this.entityManager.createQuery(criteria);
        
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
