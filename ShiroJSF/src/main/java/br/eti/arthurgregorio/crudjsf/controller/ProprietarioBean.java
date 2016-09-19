package br.eti.arthurgregorio.crudjsf.controller;

import br.eti.arthurgregorio.crudjsf.dao.ProprietarioService;
import br.eti.arthurgregorio.crudjsf.entities.Proprietario;
import java.io.Serializable;
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
public class ProprietarioBean implements Serializable {

    private Proprietario proprietario;
    
    private EstadoTela state;
    
    @Inject
    private ProprietarioService proprietarioService;
    
    @Inject
    private transient FacesContext facesContext;
    
    /**
     * 
     * @param proprietarioId
     * @param deletar 
     */
    public void inicializar(long proprietarioId, boolean deletar) {
        
        if (proprietarioId == 0) {
            this.proprietario = new Proprietario();
            this.state = EstadoTela.INSERINDO;
        } else if (deletar) {
            this.state = EstadoTela.DELETANDO;
            this.proprietario = this.proprietarioService.buscarPorId(proprietarioId);
        } else {
            this.state = EstadoTela.EDITANDO;
            this.proprietario = this.proprietarioService.buscarPorId(proprietarioId);
        }
    }
    
    /**
     * Salva..
     */
    public void salvar() {
        this.proprietarioService.salvar(this.proprietario);
        this.proprietario = null;
        this.facesContext.addMessage(null, new FacesMessage("Proprietario salvo!", null));
    }
    
    /**
     * Deleta
     * 
     * @return e volta
     */
    public String deletar() {
        try {
            this.proprietarioService.remover(this.proprietario);
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            System.err.println(ex);
            this.facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Proprietario possui carros!", null));
            return null;
        }
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }
    
    public EstadoTela getState() {
        return state;
    }
}
