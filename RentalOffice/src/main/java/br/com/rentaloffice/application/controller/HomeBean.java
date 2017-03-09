package br.com.rentaloffice.application.controller;

import br.com.rentaloffice.model.entities.Car;
import br.com.rentaloffice.model.entities.Owner;
import br.com.rentaloffice.model.service.RentalService;
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

    @Inject
    private RentalService rentalService;
    
    @Getter
    private List<Car> cars;
    @Getter
    private List<Owner> owners;
    
    /**
     * Initialize the view 
     */
    public void initialize() {
        this.cars = this.rentalService.listAllCars();
        this.owners = this.rentalService.listAllOwners();
    }
    
    /**
     *
     * @return
     */
    public String logout() {
        return "/logout.xhtml?faces-redirect=true";
    }
}
