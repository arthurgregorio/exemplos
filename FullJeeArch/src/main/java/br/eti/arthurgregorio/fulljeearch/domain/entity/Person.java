package br.eti.arthurgregorio.fulljeearch.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Arthur
 */
@Entity
@Table(name = "persons")
public class Person extends PersistentEntity {

    @Getter
    @Setter 
    private String name;
    @Getter
    @Setter 
    private String email;
    
    /**
     * 
     */
    public Person(){ }

    /**
     * 
     * @param name
     * @param email 
     */
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
