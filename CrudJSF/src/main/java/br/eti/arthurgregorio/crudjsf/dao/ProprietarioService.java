package br.eti.arthurgregorio.crudjsf.dao;

import br.eti.arthurgregorio.crudjsf.entities.Proprietario;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 13/07/2015
 */
@Stateless
public class ProprietarioService {

    @Inject
    private EntityManager entityManager;
    
    /**
     * 
     * @param proprietario
     * @return 
     */
    public Proprietario salvar(Proprietario proprietario) {
        return this.entityManager.merge(proprietario);
    }
    
    /**
     * 
     * @param proprietario 
     */
    public void remover(Proprietario proprietario) {
        this.entityManager.remove(proprietario);
    }
}
