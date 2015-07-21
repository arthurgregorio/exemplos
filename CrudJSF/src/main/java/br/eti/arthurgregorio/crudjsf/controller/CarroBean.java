package br.eti.arthurgregorio.crudjsf.controller;

import br.eti.arthurgregorio.crudjsf.dao.CarroService;
import br.eti.arthurgregorio.crudjsf.dao.ProprietarioService;
import br.eti.arthurgregorio.crudjsf.entities.Carro;
import br.eti.arthurgregorio.crudjsf.entities.Proprietario;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
public class CarroBean implements Serializable {

    private Carro carro;
    
    private List<Proprietario> proprietarios;
    
    private EstadoTela state;
    
    @Inject
    private CarroService carroService;
    @Inject
    private ProprietarioService proprietarioService;
    
    @Inject
    private transient FacesContext facesContext;
    
    /**
     * 
     * @param carroId 
     */
    public void inicializar(long carroId) {
        
        this.proprietarios = this.proprietarioService.listarTodos();
        
        if (carroId == 0) {
            this.carro = new Carro();
            this.state = EstadoTela.INSERINDO;
        } else {
            this.state = EstadoTela.EDITANDO;
            this.carro = this.carroService.buscarPorId(carroId);
        }
    }
    
    /**
     * Salva..
     */
    public void salvar() {
        this.carroService.salvar(this.carro);
        this.carro = null;
        this.facesContext.addMessage(null, new FacesMessage("Carro salvo!", null));
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }
    
    public EstadoTela getState() {
        return state;
    }

    public List<Proprietario> getProprietarios() {
        return proprietarios;
    }
}
