package br.eti.arthurgregorio.shirotest.utils.jpa;

import br.eti.arthurgregorio.shirotest.entities.PersistentEntity;
import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

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
//        entity.setIncludedBy(this.getAuthenticated().getUsername());
    }
    
    /**
     * 
     * @param entity 
     */
    @PreUpdate
    public void preUpdate(PersistentEntity entity) {
        entity.setLastEdition(LocalDateTime.now());
//        entity.setEditedBy(this.getAuthenticated().getUsername());
    }
}
