package se.kth.iv1201.boblaghei.dto;

/**
 * DTO representing entity class <code>CompetenceProfile</code>.
 */
public class CompetenceProfileDTO {

    private double yearsOfExperience;
    private ApplicationDTO application;
    private CompetenceDTO competence;

    public CompetenceProfileDTO(double yearsOfExperience, ApplicationDTO application, CompetenceDTO competence) {
        this.yearsOfExperience = yearsOfExperience;
        this.application = application;
        this.competence = competence;
    }

    public double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public ApplicationDTO getApplication() {
        return application;
    }

    public void setApplication(ApplicationDTO application) {
        this.application = application;
    }

    public CompetenceDTO getCompetence() {
        return competence;
    }

    public void setCompetence(CompetenceDTO competence) {
        this.competence = competence;
    }
}