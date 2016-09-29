package br.eti.arthurgregorio.shirotest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @since 1.0.0, 12/07/2015
 */
@Entity
@ToString
@Table(name = "cars")
@EqualsAndHashCode(callSuper = true)
public class Car extends PersistentEntity {

    @Getter
    @Setter
    @Column(name = "plate")
    private String plate;
    
    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
