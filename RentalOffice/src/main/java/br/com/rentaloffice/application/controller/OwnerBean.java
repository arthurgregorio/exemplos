package br.com.rentaloffice.application.controller;

import br.com.rentaloffice.model.entities.Owner;
import br.com.rentaloffice.model.service.RentalService;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;

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

    @Getter
    private Owner owner;

    @Inject
    private RentalService rentalService;

    /**
     *
     * @param ownerId
     * @param viewState
     */
    public void initialize(long ownerId, String viewState) {

        this.viewState = ViewState.valueOf(viewState);

        if (this.viewState == ViewState.ADDING) {
            this.owner = new Owner();
        } else {
            this.owner = this.rentalService.findOwnerById(ownerId);
        }
    }

    /**
     * Salva..
     */
    public void save() {
        this.rentalService.saveOwner(this.owner);
        this.owner = null;
        this.facesContext.addMessage(null, new FacesMessage("Owener saved!", null));
    }

    /**
     * Deleta
     *
     * @return e volta
     */
    public String delete() {
        try {
            this.rentalService.deleteOwner(this.owner);
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            this.facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Owner has cars!", null));
            return null;
        }
    }
}
