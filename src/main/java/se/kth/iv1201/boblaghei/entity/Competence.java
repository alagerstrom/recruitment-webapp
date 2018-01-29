package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;

/**
 *  O/R Mapping of the table Competence in the database.
 */
@Entity
public class Competence {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    public Competence() {
    }

    public Competence(String name) {
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
        return "Competence{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
