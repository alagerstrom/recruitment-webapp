package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "application", cascade = CascadeType.ALL)
    private Set<CompetenceProfile> competenceProfiles = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "application", cascade = CascadeType.ALL)
    private Set<Availability> availabilities = new HashSet<>();

    public Application() {
    }

    public Application(Date created, Status status, Person person) {
        this.created = created;
        this.status = status;
        this.person = person;
    }

    public Application(Date created, Status status, Person person, Set<CompetenceProfile> competenceProfiles) {
        this.created = created;
        this.status = status;
        this.person = person;
        this.competenceProfiles = competenceProfiles;
    }

    public Application(Date created, Status status, Person person, Set<CompetenceProfile> competenceProfiles, Set<Availability> availabilities) {
        this.created = created;
        this.status = status;
        this.person = person;
        this.competenceProfiles = competenceProfiles;
        this.availabilities = availabilities;
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

    public Set<CompetenceProfile> getCompetenceProfiles() {
        return competenceProfiles;
    }

    public void setCompetenceProfiles(Set<CompetenceProfile> competenceProfiles) {
        this.competenceProfiles = competenceProfiles;
    }

    public Set<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Set<Availability> availabilities) {
        this.availabilities = availabilities;
    }
}
