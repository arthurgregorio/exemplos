package br.eti.arthurgregorio.shirotest.application.resources.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * Facescontext producer
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 20/07/2015
 */
@ApplicationScoped
public class FacesContextProducer {

    /**
     * @return the facescontext instance
     */
    @Produces
    @RequestScoped
    FacesContext produce() {
        return FacesContext.getCurrentInstance();
    }
}
