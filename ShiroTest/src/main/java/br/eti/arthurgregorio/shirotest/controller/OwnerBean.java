package br.eti.arthurgregorio.shirotest.controller;

import br.eti.arthurgregorio.shirotest.dao.OwnerDAO;
import br.eti.arthurgregorio.shirotest.entities.Owner;
import javax.faces.application.FacesMessage;
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
public class OwnerBean extends GenericBean {

    private Owner proprietario;
    
    @Inject
    private OwnerDAO proprietarioService;
    
    /**
     * 
     * @param proprietarioId
     * @param deletar 
     */
    public void inicializar(long proprietarioId, boolean deletar) {
        
        if (proprietarioId == 0) {
            this.proprietario = new Owner();
            this.viewState = ViewState.ADDING;
        } else if (deletar) {
            this.viewState = ViewState.DELETING;
            this.proprietario = this.proprietarioService.buscarPorId(proprietarioId);
        } else {
            this.viewState = ViewState.EDITING;
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
            this.facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Proprietario possui carros!", null));
            return null;
        }
    }
}
