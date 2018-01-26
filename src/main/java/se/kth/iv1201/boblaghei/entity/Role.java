package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * O/R Mapping of the table Role in the database.
 */
@Entity
public class Role {

    @Id
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles;

    public Role() {
    }

    public Role(String name, Set<UserRole> userRoles) {
        this.name = name;
        this.userRoles = userRoles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", userRoles=" + userRoles +
                '}';
    }
}