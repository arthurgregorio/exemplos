package br.eti.arthurgregorio.shirotest.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "groups")
@EqualsAndHashCode(callSuper = true)
public class Group extends PersistentEntity {

    @Getter
    @Setter
    @Column(name = "name", nullable = false, length = 90)
    private String name;
    
    private List<Role> roles;
}
