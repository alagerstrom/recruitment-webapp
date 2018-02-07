package se.kth.iv1201.boblaghei.dto;

/**
 * DTO representing entity class <code>CompetenceProfile</code>.
 */
public class CompetenceProfileDTO {

    private long id;
    private double yearsOfExperience;
    private CompetenceDTO competence;

    public CompetenceProfileDTO() {
    }

    public CompetenceProfileDTO(double yearsOfExperience, CompetenceDTO competence) {
        this.yearsOfExperience = yearsOfExperience;
        this.competence = competence;
    }

    public CompetenceProfileDTO(long id, double yearsOfExperience, CompetenceDTO competence) {
        this.id = id;
        this.yearsOfExperience = yearsOfExperience;
        this.competence = competence;
    }

    public double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public CompetenceDTO getCompetence() {
        return competence;
    }

    public void setCompetence(CompetenceDTO competence) {
        this.competence = competence;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CompetenceProfileDTO{" +
                "id=" + id +
                ", yearsOfExperience=" + yearsOfExperience +
                ", competence=" + competence +
                '}';
    }
}
