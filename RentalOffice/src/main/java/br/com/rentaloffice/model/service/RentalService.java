package br.com.rentaloffice.model.service;

import br.com.rentaloffice.model.dao.CarDAO;
import br.com.rentaloffice.model.dao.OwnerDAO;
import br.com.rentaloffice.model.entities.Car;
import br.com.rentaloffice.model.entities.Owner;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

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
    @Transactional
    public Car saveCar(Car car) {
        return this.carDAO.save(car);
    }
    
    /**
     * 
     * @param car 
     */
    @Transactional
    public void deleteCar(Car car) {
        this.carDAO.delete(car);
    }
    
    /**
     * 
     * @param owner
     * @return 
     */
    @Transactional
    public Owner saveOwner(Owner owner) {
        return this.ownerDAO.save(owner);
    }
    
    /**
     * 
     * @param owner 
     */
    @Transactional
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
