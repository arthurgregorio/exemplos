package br.eti.arthurgregorio.crudjsf.config;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * Produz instancias do facescontext
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 20/07/2015
 */
public class FacesContextProducer {

    /**
     * @return uma instancia do facescontext
     */
    @Produces
    @RequestScoped
    FacesContext produce() {
        return FacesContext.getCurrentInstance();
    }
}
