package br.eti.arthurgregorio.shirotest.application.resources.faces;

import java.util.Iterator;
import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 04/10/2016
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    private final ExceptionHandler wrapped;

    /**
     * 
     * @param exceptionHandler 
     */
    public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
        this.wrapped = exceptionHandler;
    }
    
    /**
     *
     * @return
     */
    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    /**
     *
     * @throws FacesException
     */
    @Override
    public void handle() throws FacesException {

        final Iterator<ExceptionQueuedEvent> unhandledExceptionQueuedEvents
                = getUnhandledExceptionQueuedEvents().iterator();

        if (unhandledExceptionQueuedEvents.hasNext()) {

            final Throwable exception = unhandledExceptionQueuedEvents
                    .next()
                    .getContext()
                    .getException();

            unhandledExceptionQueuedEvents.remove();

            final Throwable rootCause = this.unwrap(exception);

            this.showError(rootCause.getMessage());
            
            return;
        }
        this.wrapped.handle();
    }

    /**
     *
     * @param <T>
     * @param exception
     * @param type
     * @return
     */
    private <T extends Throwable> Throwable unwrap(Throwable exception, Class<T> type) {
        while (type.isInstance(exception) && exception.getCause() != null) {
            exception = exception.getCause();
        }
        return exception;
    }

    /**
     *
     * @param <T>
     * @param exception
     * @return
     */
    private <T extends Throwable> Throwable unwrap(Throwable exception) {
        return this.unwrap(this.unwrap(exception, FacesException.class), ELException.class);
    }

    /**
     * 
     * @param cause 
     */
    private void showError(String cause) {
        FacesUtils.showError(cause);
        FacesContext.getCurrentInstance().renderResponse();        
    }
}
