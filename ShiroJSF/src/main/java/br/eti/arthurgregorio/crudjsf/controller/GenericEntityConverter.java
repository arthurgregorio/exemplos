package br.eti.arthurgregorio.crudjsf.controller;

import br.eti.arthurgregorio.crudjsf.entities.IConvertableEntity;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 20/07/2015
 */
@FacesConverter("entityConverter")
public class GenericEntityConverter implements Converter {

    /**
     * 
     * @param ctx
     * @param component
     * @param value
     * @return 
     */
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {

        if (value != null) {
            return this.getAttributesFrom(component).get(value);
        }

        return null;
    }

    /**
     * 
     * @param ctx
     * @param component
     * @param value
     * @return 
     */
    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {

        if (value != null && !"".equals(value)) {

            IConvertableEntity entity = (IConvertableEntity) value;

            this.addAttribute(component, entity);

            Object codigo = entity.getIdentification();

            if (codigo != null) {
                return String.valueOf(codigo);
            } 
            
            return null;
        }

        return (String) value;
    }

    /**
     * 
     * @param component
     * @param converter 
     */
    protected void addAttribute(UIComponent component, IConvertableEntity converter) {
        final String key = String.valueOf(converter.getIdentification());
        this.getAttributesFrom(component).put(key, converter);
    }

    /**
     * 
     * @param component
     * @return 
     */
    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
}
