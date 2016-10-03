package br.eti.arthurgregorio.shirotest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
    @NamedQuery(name = "User.all", 
            query = "select u from User u"),
    @NamedQuery(name = "User.byUsername", 
            query = "SELECT u FROM User u WHERE u.username = :username")
})
public class User extends PersistentEntity {

    @Getter
    @Setter
    @NotNull(message = "{user.username}")
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Getter
    @Setter
    @NotNull(message = "{user.name}")
    @Column(name = "name", nullable = false, length = 90)
    private String name;
    @Getter
    @Setter
    @Column(name = "password", nullable = false)
    private String password;
    @Getter
    @Setter
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
 
    @Getter
    @Setter
    @ManyToOne
    @NotNull(message = "{user.group}")
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
}
