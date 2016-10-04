package br.eti.arthurgregorio.shirotest.entities;

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
@Table(name = "groups")
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
    @NamedQuery(name = "Group.all", query = "SELECT g FROM Group g"),
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
     * @param groupPermissions 
     */
    public Group(String name, List<GroupPermission> groupPermissions) {
        this.name = name;
        this.groupPermissions = groupPermissions;
    }

    /**
     * 
     * @param permission 
     */
    public void addPermissions(GroupPermission permission) {
        this.groupPermissions.add(permission);
    }

    /**
     * 
     * @return 
     */
    public List<GroupPermission> getGroupPermissions() {
        return Collections.unmodifiableList(this.groupPermissions);
    }
}
