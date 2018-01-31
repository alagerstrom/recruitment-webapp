package se.kth.iv1201.boblaghei.entity;

import se.kth.iv1201.boblaghei.dto.ApplicationDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * O/R Mapping of the table Application in the database.
 */
@Entity
public class Application {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Date created;

    @ManyToOne
    private Status status;

    @ManyToOne
    private Person person;

    public Application() {
    }

    public Application(Date created, Status status, Person person) {
        this.created = created;
        this.status = status;
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", created=" + created +
                ", status=" + status +
                ", person=" + person +
                '}';
    }

    public ApplicationDTO getDTO() {
        return new ApplicationDTO(id, getCreated(), getStatus().getDTO(), getPerson().getDTO());
    }
}
