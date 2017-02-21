package br.eti.arthurgregorio.shirotest.model.service;

import br.eti.arthurgregorio.shirotest.model.dao.CarDAO;
import br.eti.arthurgregorio.shirotest.model.dao.OwnerDAO;
import br.eti.arthurgregorio.shirotest.model.entities.Car;
import br.eti.arthurgregorio.shirotest.model.entities.Owner;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 03/10/2016
 */
@Dependent
public class RentalService {

    @Inject
    private CarDAO carDAO;
    @Inject
    private OwnerDAO ownerDAO;
    
    /**
     * 
     * @param car
     * @return 
     */
    public Car saveCar(Car car) {
        return this.carDAO.save(car);
    }
    
    /**
     * 
     * @param car 
     */
    public void deleteCar(Car car) {
        this.carDAO.delete(car);
    }
    
    /**
     * 
     * @param owner
     * @return 
     */
    public Owner saveOwner(Owner owner) {
        return this.ownerDAO.save(owner);
    }
    
    /**
     * 
     * @param owner 
     */
    public void deleteOwner(Owner owner) {
        this.ownerDAO.delete(owner);
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public Owner findOwnerById(long id) {
        return this.ownerDAO.findById(id);
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public Car findCarById(long id) {
        return this.carDAO.findById(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<Car> listAllCars() {
        return this.carDAO.listAll();
    }
    
    /**
     * 
     * @return 
     */
    public List<Owner> listAllOwners() {
        return this.ownerDAO.listAll();
    }
}
