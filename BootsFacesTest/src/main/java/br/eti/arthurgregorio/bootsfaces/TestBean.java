package br.eti.arthurgregorio.bootsfaces;

import java.io.Serializable;
import java.util.Date;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 13/01/2016
 */
@Named
@ViewScoped
public class TestBean implements Serializable {

    @Getter
    @Setter
    private String valor;
    @Getter
    @Setter
    private Date date;
}
