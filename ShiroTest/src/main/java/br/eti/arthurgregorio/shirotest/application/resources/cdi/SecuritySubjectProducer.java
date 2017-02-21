package br.eti.arthurgregorio.shirotest.application.resources.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 03/10/2016
 */
@ApplicationScoped
public class SecuritySubjectProducer {

    /**
     * 
     * @return 
     */
    @Produces
    @RequestScoped
    Subject produce() {
        return SecurityUtils.getSubject();
    }
}
