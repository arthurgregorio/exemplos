package br.eti.arthurgregorio.shirotest.controller;

import br.eti.arthurgregorio.shirotest.entities.Car;
import br.eti.arthurgregorio.shirotest.entities.Owner;
import br.eti.arthurgregorio.shirotest.service.RentalService;
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
    private RentalService rentalService;
    
    /**
     * 
     * @param carId
     * @param viewState 
     */
    public void initialize(long carId, String viewState) {
        
        this.viewState = ViewState.valueOf(viewState);
        
        this.owners = this.rentalService.listAllOwners();
        
        if (this.viewState == ViewState.ADDING) {
            this.car = new Car();
        } else {
            this.car = this.rentalService.findCarById(carId);
        }
    }
    
    /**
     * 
     */
    public void save() {
        this.rentalService.saveCar(this.car);
        this.car = null;
        this.facesContext.addMessage(null, new FacesMessage("Carro salvo!", null));
    }
    
    /**
     * 
     * @return 
     */
    public String deletar() {
        this.rentalService.deleteCar(this.car);
        return "/index.xhtml?faces-redirect=true";
    }
}
