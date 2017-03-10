package br.com.rentaloffice.application.resources.jpa;

import br.com.rentaloffice.model.entities.PersistentEntity;
import br.com.rentaloffice.model.entities.User;
import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 10/02/2015
 */
public final class PersistListener {

    /**
     * 
     * @param entity 
     */
    @PrePersist
    public void prePersist(PersistentEntity entity) {
        entity.setInclusion(LocalDateTime.now());
        entity.setIncludedBy(this.getCurrentUserName());
    }
    
    /**
     * 
     * @param entity 
     */
    @PreUpdate
    public void preUpdate(PersistentEntity entity) {
        entity.setLastEdition(LocalDateTime.now());
        entity.setEditedBy(this.getCurrentUserName());
    }
    
    /**
     * @return the current authenticated user 
     */
    public String getCurrentUserName() {
        
        final Subject subject = SecurityUtils.getSubject();
        
        if (subject != null && subject.isAuthenticated()) {
            return subject.getPrincipals().oneByType(User.class).getUsername();
        } else {
            return "not-an-user";
        }
    }
}
