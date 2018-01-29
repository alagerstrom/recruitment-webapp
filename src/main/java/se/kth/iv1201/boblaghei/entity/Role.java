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

    public Role() {
    }

    public Role(String names) {
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
}