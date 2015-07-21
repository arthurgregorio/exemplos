package br.eti.arthurgregorio.crudjsf.dao;

import br.eti.arthurgregorio.crudjsf.entities.Carro;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class CarroService {

    @Inject
    private EntityManager entityManager;

    /**
     *
     * @param carro
     * @return
     */
    @Transactional
    public Carro salvar(Carro carro) {
        return this.entityManager.merge(carro);
    }

    /**
     *
     * @param carro
     */
    @Transactional
    public void remover(Carro carro) {
        this.entityManager.remove(
                this.entityManager.getReference(Carro.class, carro.getId()));
    }

    /**
     *
     * @return
     */
    public List<Carro> listarTodos() {

        final CriteriaBuilder builder = this.getBuilder();

        final CriteriaQuery criteria = builder.createQuery(Carro.class);

        criteria.select(criteria.from(Carro.class));

        final TypedQuery<Carro> query = this.entityManager.createQuery(criteria);

        return query.getResultList();
    }

    /**
     *
     * @param carroId
     * @return
     */
    public Carro buscarPorId(long carroId) {

        final CriteriaBuilder builder = this.getBuilder();

        final CriteriaQuery criteria = builder.createQuery(Carro.class);

        final Root<Carro> root = criteria.from(Carro.class);

        criteria.select(criteria
                .from(Carro.class))
                .where(builder.equal(root.get(Carro.ID), carroId));

        final TypedQuery<Carro> query = this.entityManager.createQuery(criteria);

        return query.getSingleResult();
    }

    /**
     * @return o builder de criterias
     */
    public CriteriaBuilder getBuilder() {
        return this.entityManager.getCriteriaBuilder();
    }
}
