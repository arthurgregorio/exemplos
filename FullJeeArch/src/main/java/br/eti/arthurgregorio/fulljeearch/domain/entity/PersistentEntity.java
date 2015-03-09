package br.eti.arthurgregorio.fulljeearch.domain.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 
 * @author Arthur
 */
@MappedSuperclass
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public abstract class PersistentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    private Date inclusion;
    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastEdition;

    /**
     * 
     * @return 
     */
    public boolean isSaved(){
        return this.id != null && this.id != 0;
    }
    
    /**
     * 
     */
    @PrePersist
    protected void prePersist() {
        this.inclusion = new Date();
    }
    
    /**
     * 
     */
    @PreUpdate
    protected void preUpdate() {
        this.lastEdition = new Date();
    }
}
