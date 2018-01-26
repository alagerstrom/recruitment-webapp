package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 *  O/R Mapping of the table Application in the database.
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

    @OneToMany(mappedBy = "application")
    private Set<Availability> availabilities;

    @OneToMany(mappedBy = "application")
    private Set<CompetenceProfile> competenceProfiles;

    public Application() {
    }

    public Application(Date created, Status status, Person person, Set<Availability> availabilities, Set<CompetenceProfile> competenceProfiles) {
        this.created = created;
        this.status = status;
        this.person = person;
        this.availabilities = availabilities;
        this.competenceProfiles = competenceProfiles;
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

    public Set<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Set<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public Set<CompetenceProfile> getCompetenceProfiles() {
        return competenceProfiles;
    }

    public void setCompetenceProfiles(Set<CompetenceProfile> competenceProfiles) {
        this.competenceProfiles = competenceProfiles;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", created=" + created +
                ", status=" + status +
                ", person=" + person +
                ", availabilities=" + availabilities +
                ", competenceProfiles=" + competenceProfiles +
                '}';
    }
}
