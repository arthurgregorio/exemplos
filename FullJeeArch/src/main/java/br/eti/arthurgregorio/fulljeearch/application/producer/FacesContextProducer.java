package br.eti.arthurgregorio.fulljeearch.application.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * 
 * @author Arthur
 */
public class FacesContextProducer {

    /**
     * 
     * @return 
     */
    @Produces
    @RequestScoped
    RequestContext produceRequestContext() {
        return RequestContext.getCurrentInstance();
    }
    
    /**
     * 
     * @return 
     */
    @Produces
    @RequestScoped
    FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }
}
