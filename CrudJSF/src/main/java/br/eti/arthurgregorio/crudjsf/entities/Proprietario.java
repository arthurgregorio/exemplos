package br.eti.arthurgregorio.crudjsf.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.metamodel.SingularAttribute;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "Informe uma nome!")
    @Column(name = "nome", nullable = false)
    private String nome;
    
    /**
     * Metamodel
     */
    @Transient
    public static volatile SingularAttribute<Proprietario, Long> ID;

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
