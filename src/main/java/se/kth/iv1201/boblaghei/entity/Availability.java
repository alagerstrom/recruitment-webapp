package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;
import java.util.Date;

/**
 *  O/R Mapping of the table Availability in the database.
 */
@Entity
public class Availability {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Date from;

    @Column(nullable = false)
    private Date to;

    @ManyToOne(cascade= CascadeType.ALL)
    private Person person;

    public Availability() {
    }

    public Availability(Date from, Date to, Person person) {
        this.from = from;
        this.to = to;
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
