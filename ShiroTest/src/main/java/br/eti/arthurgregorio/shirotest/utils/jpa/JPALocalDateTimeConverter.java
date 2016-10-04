package br.eti.arthurgregorio.shirotest.utils.jpa;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.AttributeConverter;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 24/03/2016
 */
public class JPALocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

    /**
     * 
     * @param attribute
     * @return 
     */
    @Override
    public Date convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute == null ? null : Date.from(
                attribute.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 
     * @param dbData
     * @return 
     */
    @Override
    public LocalDateTime convertToEntityAttribute(Date dbData) {
        return dbData == null ? null : LocalDateTime.ofInstant(
                dbData.toInstant(), ZoneId.systemDefault());
    }
}
