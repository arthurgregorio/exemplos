package br.eti.arthurgregorio.shirotest.service;

import br.eti.arthurgregorio.shirotest.dao.CarDAO;
import br.eti.arthurgregorio.shirotest.dao.OwnerDAO;
import br.eti.arthurgregorio.shirotest.entities.Car;
import br.eti.arthurgregorio.shirotest.entities.Owner;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 03/10/2016
 */
@ApplicationScoped
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
