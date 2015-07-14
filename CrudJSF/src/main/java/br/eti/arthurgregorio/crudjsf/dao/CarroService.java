package br.eti.arthurgregorio.crudjsf.dao;

import br.eti.arthurgregorio.crudjsf.entities.Carro;
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
public class CarroService {

    @Inject
    private EntityManager entityManager;
    
    /**
     * 
     * @param carro
     * @return 
     */
    public Carro salvar(Carro carro) {
        return this.entityManager.merge(carro);
    }
    
    /**
     * 
     * @param carro 
     */
    public void remover(Carro carro) {
        this.entityManager.remove(carro);
    }
}
