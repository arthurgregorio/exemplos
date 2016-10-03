package br.eti.arthurgregorio.shirotest.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 03/10/2016
 */
@Entity
@ToString
@Table(name = "group_permissions")
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
    @NamedQuery(name = "GroupPermission.all", 
            query = "select gp from GroupPermission gp"),
    @NamedQuery(name = "GroupPermission.byId", 
            query = "SELECT gp FROM GroupPermission gp WHERE gp.id = :id"),
    @NamedQuery(name = "GroupPermission.byGroup", 
            query = "SELECT gp FROM GroupPermission gp WHERE gp.group = :group")
})
public class GroupPermission extends PersistentEntity {
    
    @Getter
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @Getter
    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

    /**
     * 
     */
    public GroupPermission() { }

    /**
     * 
     * @param group
     * @param permission 
     */
    public GroupPermission(Group group, Permission permission) {
        this.group = group;
        this.permission = permission;
    }
    
    public String getAuthorizationKey() {
        return this.permission.getAuthorizationKey();
    }
}
