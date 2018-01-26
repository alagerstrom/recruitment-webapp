package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;

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

    @OneToMany
    private CompetenceProfile competenceProfile;

    public Competence() {
    }

    public Competence(String name, CompetenceProfile competenceProfile) {
        this.name = name;
        this.competenceProfile = competenceProfile;
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

    public CompetenceProfile getCompetenceProfile() {
        return competenceProfile;
    }

    public void setCompetenceProfile(CompetenceProfile competenceProfile) {
        this.competenceProfile = competenceProfile;
    }
}
