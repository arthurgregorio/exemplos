package br.eti.arthurgregorio.fulljeearch.domain.security.model.entity;

import br.eti.arthurgregorio.fulljeearch.domain.entity.Person;
import java.io.Serializable;
import javax.persistence.CascadeType;
import org.picketlink.idm.jpa.annotations.AttributeValue;
import org.picketlink.idm.jpa.annotations.entity.IdentityManaged;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;
import org.picketlink.idm.model.basic.User;

/**
 * 
 * @author Arthur
 */
@Entity
@Table(name = "users")
@IdentityManaged(User.class)
public class UserTypeEntity extends IdentityTypeEntity implements Serializable {

    @Getter
    @Setter
    @AttributeValue
    private String userName;
    @Getter
    @Setter
    @AttributeValue
    @OneToOne(cascade = CascadeType.ALL)
    private Person person;
}
