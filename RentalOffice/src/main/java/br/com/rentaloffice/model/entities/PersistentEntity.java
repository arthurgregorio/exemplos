package br.com.rentaloffice.model.entities;

import br.com.rentaloffice.application.resources.jpa.PersistListener;
import br.com.rentaloffice.application.resources.jpa.JPALocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @Column(name = "included_by", length = 45)
    private String includedBy;
    @Getter
    @Setter
    @JsonIgnore
    @Column(name = "edited_by", length = 45)
    private String editedBy;
    
    @Getter
    @Setter
    @JsonIgnore
    @Column(name = "inclusion", nullable = false)
    @Convert(converter = JPALocalDateTimeConverter.class)
    private LocalDateTime inclusion;
    @Getter
    @Setter
    @JsonIgnore
    @Column(name = "last_edition")
    @Convert(converter = JPALocalDateTimeConverter.class)
    private LocalDateTime lastEdition;

    /**
     * @return 
     */
    @JsonIgnore
    public boolean isSaved(){
        return this.id != null && this.id != 0;
    }
    
    /**
     * @return a data de inclusao em formato string
     */
    @JsonIgnore
    public String getInclusionDateAsString() {
        return DateTimeFormatter
                .ofPattern("dd/MM/yyyy HH:mm")
                .format(this.inclusion);
    }
}
