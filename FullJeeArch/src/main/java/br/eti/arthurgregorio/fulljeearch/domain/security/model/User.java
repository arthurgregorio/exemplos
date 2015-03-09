package br.eti.arthurgregorio.fulljeearch.domain.security.model;

import br.eti.arthurgregorio.fulljeearch.domain.entity.Person;
import lombok.Getter;
import lombok.Setter;
import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.IdentityStereotype;
import org.picketlink.idm.model.annotation.StereotypeProperty;
import org.picketlink.idm.model.annotation.Unique;
import org.picketlink.idm.query.QueryParameter;
import static org.picketlink.idm.model.annotation.IdentityStereotype.Stereotype.USER;
import static org.picketlink.idm.model.annotation.StereotypeProperty.Property.IDENTITY_USER_NAME;

/**
 * 
 * @author Arthur
 */
@IdentityStereotype(USER)
public class User extends AbstractIdentityType implements Account {

    @Getter
    @Setter
    @Unique
    @AttributeProperty
    @StereotypeProperty(IDENTITY_USER_NAME)
    private String userName;
    @Getter
    @Setter    
    @AttributeProperty
    private Person person;
    
    public static final QueryParameter USER_NAME = QUERY_ATTRIBUTE.byName("userName");

    /**
     * 
     */
    public User() {
        this(null);
    }

    /**
     * 
     * @param userName 
     */
    public User(String userName) {
        this.userName = userName;
    }
    
    /**
     * 
     * @param userName
     * @param person 
     */
    public User(String userName, Person person) {
        this.person = person;
        this.userName = userName;
    }
}