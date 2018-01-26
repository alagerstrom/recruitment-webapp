package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;
import java.util.Set;

/**
 *  O/R Mapping of the table Status in the database.
 */
@Entity
public class Status {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    private Set<Application> applications;

    public Status() {
    }

    public Status(String name, Set<Application> applications) {
        this.name = name;
        this.applications = applications;
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

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", applications=" + applications +
                '}';
    }
}
