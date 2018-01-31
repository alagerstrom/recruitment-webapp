package se.kth.iv1201.boblaghei.entity;

import se.kth.iv1201.boblaghei.dto.StatusDTO;

import javax.persistence.*;

/**
 * O/R Mapping of the table Status in the database.
 */
@Entity
public class Status {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Status() {
    }

    public Status(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public StatusDTO getDTO() {
        return new StatusDTO(id, getName());
    }
}
