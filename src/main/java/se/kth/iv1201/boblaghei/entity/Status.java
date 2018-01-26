package se.kth.iv1201.boblaghei.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *  O/R Mapping of the table Status in the database.
 */
public class Status {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
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
}
