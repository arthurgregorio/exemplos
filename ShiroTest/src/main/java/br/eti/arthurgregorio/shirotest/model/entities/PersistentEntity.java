package br.eti.arthurgregorio.shirotest.model.entities;

import br.eti.arthurgregorio.shirotest.application.resources.jpa.PersistListener;
import br.eti.arthurgregorio.shirotest.application.resources.jpa.JPALocalDateTimeConverter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 29/09/2016
 */
@MappedSuperclass
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@EntityListeners(PersistListener.class)
public abstract class PersistentEntity implements Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Getter
    @Setter
    @Column(name = "included_by", length = 45)
    private String includedBy;
    @Getter
    @Setter
    @Column(name = "edited_by", length = 45)
    private String editedBy;
    
    @Getter
    @Setter
    @Column(name = "inclusion", nullable = false)
    @Convert(converter = JPALocalDateTimeConverter.class)
    private LocalDateTime inclusion;
    @Getter
    @Setter
    @Column(name = "last_edition")
    @Convert(converter = JPALocalDateTimeConverter.class)
    private LocalDateTime lastEdition;

    /**
     * @return 
     */
    public boolean isSaved(){
        return this.id != null && this.id != 0;
    }
    
    /**
     * @return a data de inclusao em formato string
     */
    public String getInclusionDateAsString() {
        return DateTimeFormatter
                .ofPattern("dd/MM/yyyy HH:mm")
                .format(this.inclusion);
    }
}
