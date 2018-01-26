package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "competence")
    private Set<CompetenceProfile> competenceProfiles;

    public Competence() {
    }

    public Competence(String name, Set<CompetenceProfile> competenceProfiles) {
        this.name = name;
        this.competenceProfiles = competenceProfiles;
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

    public Set<CompetenceProfile> getCompetenceProfiles() {
        return competenceProfiles;
    }

    public void setCompetenceProfiles(Set<CompetenceProfile> competenceProfiles) {
        this.competenceProfiles = competenceProfiles;
    }

    @Override
    public String toString() {
        return "Competence{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", competenceProfiles=" + competenceProfiles +
                '}';
    }
}
