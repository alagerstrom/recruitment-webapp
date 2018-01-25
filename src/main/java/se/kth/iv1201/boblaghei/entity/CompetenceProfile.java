package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;

/**
 *  O/R Mapping of the table CompetenceProfile in the database.
 */
@Entity
public class CompetenceProfile {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private double yearsOfExperience;

    @ManyToOne(cascade=CascadeType.ALL)
    private Person person;

    @ManyToOne(cascade=CascadeType.ALL)
    private Competence competence;

    public CompetenceProfile() {
    }

    public CompetenceProfile(double yearsOfExperience, Person person, Competence competence) {
        this.yearsOfExperience = yearsOfExperience;
        this.person = person;
        this.competence = competence;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }
}
