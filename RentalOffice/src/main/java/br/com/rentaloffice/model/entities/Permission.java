package br.com.rentaloffice.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@Entity
@ToString
@Table(name = "permissions", schema = "security")
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
    @NamedQuery(name = "Permission.all", 
            query = "SELECT p FROM Permission p"),
    @NamedQuery(name = "Permission.byId", 
            query = "SELECT p FROM Permission p WHERE p.id = :id"),
    @NamedQuery(name = "Permission.byFunctionality", 
            query = "SELECT p FROM Permission p WHERE p.functionality = :functionality")
})
public class Permission extends PersistentEntity {

    @Setter
    @Getter
    @Column(name = "functionality", nullable = false, length = 100)
    private String functionality;
    
    @Setter
    @Getter
    @Column(name = "access", nullable = false, length = 100)
    private String access;

    /**
     * 
     */
    public Permission() { }

    /**
     * 
     * @param functionality
     * @param access 
     */
    public Permission(String functionality, String access) {
        this.access = access;
        this.functionality = functionality;
    }

    /**
     * 
     * @return 
     */
    public String getKey() {
        return this.functionality + ":" + this.access;
    }
}
