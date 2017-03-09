package br.com.rentaloffice.model.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 29/09/2016
 */
@Entity
@ToString
@Table(name = "groups", schema = "security")
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
    @NamedQuery(name = "Group.all",
            query = "SELECT g FROM Group g"),
    @NamedQuery(name = "Group.byId",
            query = "SELECT g FROM Group g WHERE g.id = :id")
})
public class Group extends PersistentEntity {

    @Getter
    @Setter
    @Column(name = "name", nullable = false, length = 90)
    private String name;

    @Transient
    private final List<GroupPermission> groupPermissions;

    /**
     *
     */
    public Group() {
        this.groupPermissions = new ArrayList<>();
    }

    /**
     *
     * @param name
     * @param permissions
     */
    public Group(String name, List<Permission> permissions) {
        this();
        this.name = name;
        
        permissions.forEach(permission -> {
            this.groupPermissions.add(new GroupPermission(this, permission));
        });
    }

    /**
     *
     * @param permission
     */
    public void addPermission(Permission permission) {
        this.groupPermissions.add(new GroupPermission(this, permission));
    }

    /**
     *
     * @param permissions
     */
    public void addPermissions(List<Permission> permissions) {
        permissions.forEach(permission -> {
            this.groupPermissions.add(new GroupPermission(this, permission));
        });
    }

    /**
     *
     * @return
     */
    public List<GroupPermission> getGroupPermissions() {
        return Collections.unmodifiableList(this.groupPermissions);
    }
}
