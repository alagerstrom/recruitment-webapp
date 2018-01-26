package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;
import java.util.Date;

/**
 *  O/R Mapping of the table Application in the database.
 */
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

    @OneToMany
    private Availability availability;

    @OneToMany
    private CompetenceProfile competenceProfile;

    public Application() {
    }

    public Application(Date created, Status status, Person person, Availability availability, CompetenceProfile competenceProfile) {
        this.created = created;
        this.status = status;
        this.person = person;
        this.availability = availability;
        this.competenceProfile = competenceProfile;
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

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public CompetenceProfile getCompetenceProfile() {
        return competenceProfile;
    }

    public void setCompetenceProfile(CompetenceProfile competenceProfile) {
        this.competenceProfile = competenceProfile;
    }
}
