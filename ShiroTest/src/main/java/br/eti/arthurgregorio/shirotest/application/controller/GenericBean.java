package br.eti.arthurgregorio.shirotest.application.controller;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import lombok.Getter;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 19/09/2016
 */
public abstract class GenericBean implements Serializable {

    @Getter
    protected ViewState viewState;
    
    @Inject
    protected FacesContext facesContext;
}
