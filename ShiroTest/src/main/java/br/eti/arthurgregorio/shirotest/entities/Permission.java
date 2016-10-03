package br.eti.arthurgregorio.shirotest.entities;

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
@Table(name = "permissions")
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
    @NamedQuery(name = "Permission.all", 
            query = "SELECT p FROM Permission p"),
    @NamedQuery(name = "Permission.byId", 
            query = "SELECT p FROM Permission p WHERE p.id = :id")
})
public class Permission extends PersistentEntity {

    @Getter
    @Setter
    @Column(name = "authorization_key", nullable = false, length = 100)
    private String authorizationKey;
}
