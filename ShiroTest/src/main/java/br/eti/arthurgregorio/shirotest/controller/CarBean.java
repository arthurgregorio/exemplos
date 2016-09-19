package br.eti.arthurgregorio.shirotest.controller;

import br.eti.arthurgregorio.shirotest.dao.CarDAO;
import br.eti.arthurgregorio.shirotest.dao.OwnerDAO;
import br.eti.arthurgregorio.shirotest.entities.Car;
import br.eti.arthurgregorio.shirotest.entities.Owner;
import java.util.List;
import javax.faces.application.FacesMessage;
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
public class CarBean extends GenericBean {

    @Getter
    private Car car;
    
    @Getter
    private List<Owner> owners;
    
    @Inject
    private CarDAO carDAO;
    @Inject
    private OwnerDAO ownerDAO;
    
    /**
     * 
     * @param carroId
     * @param deletar 
     */
    public void inicializar(long carroId, boolean deletar) {
        
        this.owners = this.ownerDAO.listarTodos();
        
        if (carroId == 0) {
            this.car = new Car();
            this.viewState = ViewState.ADDING;
        } else if (deletar) {
            this.viewState = ViewState.DELETING;
            this.car = this.carDAO.buscarPorId(carroId);
        } else {
            this.viewState = ViewState.EDITING;
            this.car = this.carDAO.buscarPorId(carroId);
        }
    }
    
    /**
     * Salva..
     */
    public void salvar() {
        this.carDAO.salvar(this.car);
        this.car = null;
        this.facesContext.addMessage(null, new FacesMessage("Carro salvo!", null));
    }
    
    /**
     * Deleta
     * 
     * @return e volta
     */
    public String deletar() {
        this.carDAO.remover(this.car);
        return "/index.xhtml?faces-redirect=true";
    }
}
