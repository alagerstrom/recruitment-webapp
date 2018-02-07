package se.kth.iv1201.boblaghei.entity;

import se.kth.iv1201.boblaghei.dto.RoleDTO;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * O/R Mapping of the table Role in the database.
 */
@Entity
public class Role {

    @Id
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }

    public RoleDTO getDTO() {
        return new RoleDTO(getName());
    }
}