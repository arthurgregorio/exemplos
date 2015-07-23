package br.eti.arthurgregorio.crudjsf.controller;

import br.eti.arthurgregorio.crudjsf.dao.CarroService;
import br.eti.arthurgregorio.crudjsf.dao.ProprietarioService;
import br.eti.arthurgregorio.crudjsf.entities.Carro;
import br.eti.arthurgregorio.crudjsf.entities.Proprietario;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 13/07/2015
 */
@Named
@ViewScoped
public class HomeBean implements Serializable {

    private List<Carro> carros;
    private List<Proprietario> proprietarios;
    
    @Inject
    private CarroService carroService;
    @Inject
    private ProprietarioService proprietarioService;
    
    /**
     * Inicializa...
     */
    public void inicializar() {
        this.carros = this.carroService.listarTodos();
        this.proprietarios = this.proprietarioService.listarTodos();
    }
    
    /**
     * 
     * @param carroId
     * @return 
     */
    public String editarCarro(long carroId) {
        return "carro/formCarro.xhtml?faces-redirect=true&carroId=" + carroId;
    }
    
    /**
     * 
     * @param carroId
     * @return 
     */
    public String deletarCarro(long carroId) {
        return "carro/formCarro.xhtml?faces-redirect=true&carroId=" + carroId + "&deletar=true";
    }
    
    /**
     * 
     * @param proprietarioId
     * @return 
     */
    public String editarProprietario(long proprietarioId) {
        return "proprietario/formProprietario.xhtml?faces-redirect=true&proprietarioId=" + proprietarioId;
    }
    
    /**
     * 
     * @param proprietarioId
     * @return 
     */
    public String deletarProprietario(long proprietarioId) {
        return "proprietario/formProprietario.xhtml?faces-redirect=true&proprietarioId=" + proprietarioId + "&deletar=true";
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    public List<Proprietario> getProprietarios() {
        return proprietarios;
    }

    public void setProprietarios(List<Proprietario> proprietarios) {
        this.proprietarios = proprietarios;
    }
}
