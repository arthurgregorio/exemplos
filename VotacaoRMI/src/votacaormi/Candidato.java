package votacaormi;

import java.io.Serializable;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0
 * @since 1.0, 22/03/2015
 */
public class Candidato implements Serializable {

    private String nome;
    private Integer numero;

    /**
     * 
     * @param nome
     * @param numero 
     */
    public Candidato(String nome, Integer numero) {
        this.nome = nome;
        this.numero = numero;
    }
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }
}
