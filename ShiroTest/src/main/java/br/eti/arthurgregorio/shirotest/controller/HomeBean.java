package br.eti.arthurgregorio.shirotest.controller;

import br.eti.arthurgregorio.shirotest.dao.CarDAO;
import br.eti.arthurgregorio.shirotest.dao.OwnerDAO;
import br.eti.arthurgregorio.shirotest.entities.Car;
import br.eti.arthurgregorio.shirotest.entities.Owner;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 13/07/2015
 */
@Named
@ViewScoped
public class HomeBean extends GenericBean {

    @Getter
    private List<Car> cars;
    @Getter
    private List<Owner> owners;
    
    @Inject
    private CarDAO carDAO;
    @Inject
    private OwnerDAO ownerDAO;
    
    /**
     * Initialize the view 
     */
    public void initialize() {
        this.cars = this.carDAO.listarTodos();
        this.owners = this.ownerDAO.listarTodos();
    }
    
    /**
     * 
     * @param carroId
     * @return 
     */
    public String editarCarro(long carroId) {
        return "carro/formCarro.xhtml?faces-redirect=true&carroId=" + carroId;
    }
    
    /**
     * 
     * @param carroId
     * @return 
     */
    public String deletarCarro(long carroId) {
        return "carro/formCarro.xhtml?faces-redirect=true&carroId=" + carroId + "&deletar=true";
    }
    
    /**
     * 
     * @param proprietarioId
     * @return 
     */
    public String editarProprietario(long proprietarioId) {
        return "proprietario/formProprietario.xhtml?faces-redirect=true&proprietarioId=" + proprietarioId;
    }
    
    /**
     * 
     * @param proprietarioId
     * @return 
     */
    public String deletarProprietario(long proprietarioId) {
        return "proprietario/formProprietario.xhtml?faces-redirect=true&proprietarioId=" + proprietarioId + "&deletar=true";
    }
}
