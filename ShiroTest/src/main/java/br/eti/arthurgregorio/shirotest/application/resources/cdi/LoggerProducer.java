package br.eti.arthurgregorio.shirotest.application.resources.cdi;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The producer for logger instances
 * 
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 03/10/2016
 */
@Dependent
public class LoggerProducer {

    /**
     * The logger to log messages through SL4J
     * 
     * @param injectionPoint the injection point for this logger
     * @return the lgger object
     */
    @Produces
    Logger produce(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }
}