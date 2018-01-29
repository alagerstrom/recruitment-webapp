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

    @ManyToOne
    private Application application;

    @ManyToOne
    private Competence competence;

    public CompetenceProfile() {
    }

    public CompetenceProfile(double yearsOfExperience, Application application, Competence competence) {
        this.yearsOfExperience = yearsOfExperience;
        this.application = application;
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

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    @Override
    public String toString() {
        return "CompetenceProfile{" +
                "id=" + id +
                ", yearsOfExperience=" + yearsOfExperience +
                ", application=" + application +
                ", competence=" + competence +
                '}';
    }
}
