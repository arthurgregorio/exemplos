package br.com.rentaloffice.model.dao;

import br.com.rentaloffice.model.entities.Car;
import java.util.List;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 13/07/2015
 */
@Dependent
public class CarDAO extends AbstractDAO<Car> {

    /**
     *
     * @return
     */
    @Override
    public List<Car> listAll() {
        return this.getQuery("Car.all", Car.class)
                .getResultList();        
    }

    /**
     *
     * @param carId
     * @return
     */
    @Override
    public Car findById(long carId) {
        return this.getQuery("Car.byId", Car.class)
                .setParameter("id", carId)
                .getSingleResult();
    }
}
