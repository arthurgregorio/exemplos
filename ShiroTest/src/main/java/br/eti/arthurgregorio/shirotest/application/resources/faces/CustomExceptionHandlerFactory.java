package br.eti.arthurgregorio.shirotest.application.resources.faces;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 04/10/2016
 */
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {

    private final ExceptionHandlerFactory parent;

    /**
     * 
     * @param parent 
     */
    public CustomExceptionHandlerFactory(final ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    /**
     * 
     * @return 
     */
    @Override
    public ExceptionHandler getExceptionHandler() {
        return new CustomExceptionHandler(this.parent.getExceptionHandler());
    }
}
