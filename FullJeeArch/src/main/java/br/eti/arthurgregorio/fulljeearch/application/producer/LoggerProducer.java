package br.eti.arthurgregorio.fulljeearch.application.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Arthur
 */
public class LoggerProducer {

    /**
     * 
     * @param injectionPoint
     * @return 
     */
    @Produces
    Logger produceLogger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }
}