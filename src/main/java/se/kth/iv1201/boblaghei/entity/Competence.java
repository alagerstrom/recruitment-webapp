package se.kth.iv1201.boblaghei.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *  O/R Mapping of the table Competence in the database.
 */
@Entity
public class Competence {
    @Id
    @GeneratedValue
    private long Id;

    @Column(nullable = false)
    private String name;

    public Competence() {
    }

    public Competence(String name) {
        this.name = name;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
