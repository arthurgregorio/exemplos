package br.eti.arthurgregorio.shirotest.controller;

import br.eti.arthurgregorio.shirotest.entities.Car;
import br.eti.arthurgregorio.shirotest.entities.Owner;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
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
    
    /**
     * Initialize the view 
     */
    public void initialize() {
        this.cars = new ArrayList<>();
        this.owners = new ArrayList<>();
    }
}
