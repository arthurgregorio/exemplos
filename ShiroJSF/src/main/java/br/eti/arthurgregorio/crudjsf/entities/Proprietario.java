package br.eti.arthurgregorio.crudjsf.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 12/07/2015
 */
@Entity
@Table(name = "proprietario")
public class Proprietario implements Serializable, IConvertableEntity<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;

    /**
     * Implementacao porca de equals somente para o converter funcionar...
     * 
     * Se for usar isso, faca de um jeito descente ou useo lombok
     * 
     * @param object
     * @return 
     */
    @Override
    public boolean equals(Object object) {
       if (object.getClass().isAssignableFrom(this.getClass())) {
           return ((Proprietario) object).getId().equals(this.getId());
       }
       return false;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.nome);
        return hash;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public Long getIdentification() {
        return this.id;
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
