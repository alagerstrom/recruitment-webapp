package se.kth.iv1201.boblaghei.dto;

import org.springframework.format.annotation.DateTimeFormat;
import se.kth.iv1201.boblaghei.entity.Competence;

import java.util.Date;

/**
 * Class to ease the process of searching for applications, contains a builder pattern to avoid problem of long parameter-list.
 */
public class ApplicationSearchDTO {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date availableFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date availableTo;

    private String applicantFirstname;

    private String applicantLastname;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date applicationCreated;

    public String competence;

    private int maxNumberOfResults;

    public ApplicationSearchDTO() {
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Date getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }

    public String getApplicantFirstname() {
        return applicantFirstname;
    }

    public void setApplicantFirstname(String applicantFirstname) {
        this.applicantFirstname = applicantFirstname;
    }

    public String getApplicantLastname() {
        return applicantLastname;
    }

    public void setApplicantLastname(String applicantLastname) {
        this.applicantLastname = applicantLastname;
    }

    public Date getApplicationCreated() {
        return applicationCreated;
    }

    public void setApplicationCreated(Date applicationCreated) {
        this.applicationCreated = applicationCreated;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public int getMaxNumberOfResults() {
        return maxNumberOfResults;
    }

    public void setMaxNumberOfResults(int maxNumberOfResults) {
        this.maxNumberOfResults = maxNumberOfResults;
    }

    @Override
    public String toString() {
        return "ApplicationSearchDTO{" +
                "availableFrom=" + availableFrom +
                ", availableTo=" + availableTo +
                ", applicantFirstname='" + applicantFirstname + '\'' +
                ", applicantLastname='" + applicantLastname + '\'' +
                ", applicationCreated=" + applicationCreated +
                ", competence=" + competence +
                ", maxNumberOfResults=" + maxNumberOfResults +
                '}';
    }
}
