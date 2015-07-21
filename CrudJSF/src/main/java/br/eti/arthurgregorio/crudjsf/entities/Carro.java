package br.eti.arthurgregorio.crudjsf.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "carro")
public class Carro implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "placa")
    @NotNull(message = "Informe uma placa!")
    private String placa;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_proprietario")
    @NotNull(message = "Informe um proprietario")
    private Proprietario proprietario;

    /**
     * Metamodel
     */
    @Transient
    public static volatile SingularAttribute<Carro, Long> ID;
    
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
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the proprietario
     */
    public Proprietario getProprietario() {
        return proprietario;
    }

    /**
     * @param proprietario the proprietario to set
     */
    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }
}
