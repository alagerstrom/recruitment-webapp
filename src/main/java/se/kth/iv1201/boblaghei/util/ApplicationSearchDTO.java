package se.kth.iv1201.boblaghei.util;

import se.kth.iv1201.boblaghei.entity.Competence;

import java.util.Date;
import java.util.Set;

/**
 * Class to ease the process of searching for applications, contains a builder pattern to avoid problem of long parameter-list.
 */
public class ApplicationSearchDTO {
    private final Date availableFrom;
    private final Date availableTo;
    private final String applicantFirstname;
    private final String applicantLastname;
    private final Date applicationCreated;
    private final Set<Competence> competences;
    private final int maxNumberOfResults;

    private ApplicationSearchDTO(Builder builder) {
        this.availableFrom = builder.availableFrom;
        this.availableTo = builder.availableTo;
        this.applicantFirstname = builder.applicantFirstname;
        this.applicantLastname = builder.applicantLastname;
        this.applicationCreated = builder.applicationCreated;
        this.competences = builder.competences;
        this.maxNumberOfResults = builder.maxNumberOfResults;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public Date getAvailableTo() {
        return availableTo;
    }

    public String getApplicantFirstname() {
        return applicantFirstname;
    }

    public String getApplicantLastname() {
        return applicantLastname;
    }

    public Date getApplicationCreated() {
        return applicationCreated;
    }

    public Set<Competence> getCompetences() {
        return competences;
    }

    public int getMaxNumberOfResults() {
        return maxNumberOfResults;
    }

    @Override
    public String toString() {
        return "ApplicationSearchDTO{" +
                "availableFrom=" + availableFrom +
                ", availableTo=" + availableTo +
                ", applicantFirstname='" + applicantFirstname + '\'' +
                ", applicantLastname='" + applicantLastname + '\'' +
                ", applicationCreated=" + applicationCreated +
                ", competences=" + competences +
                ", maxNumberOfResults=" + maxNumberOfResults +
                '}';
    }

    public static class Builder {
        private Date availableFrom;
        private Date availableTo;
        private String applicantFirstname;
        private String applicantLastname;
        private Date applicationCreated;
        private Set<Competence> competences;
        private int maxNumberOfResults;

        public Builder setAvailableFrom(Date availableFrom) {
            this.availableFrom = availableFrom;
            return this;
        }

        public Builder setAvailableTo(Date availableTo) {
            this.availableTo = availableTo;
            return this;
        }

        public Builder setApplicantFirstname(String applicantFirstname) {
            this.applicantFirstname = applicantFirstname;
            return this;
        }

        public Builder setApplicantLastname(String applicantLastname) {
            this.applicantLastname = applicantLastname;
            return this;
        }

        public Builder setApplicationCreated(Date applicationCreated) {
            this.applicationCreated = applicationCreated;
            return this;
        }

        public Builder setCompetences(Set<Competence> competences) {
            this.competences = competences;
            return this;
        }

        public Builder setMaxNumberOfResults(int maxNumberOfResults) {
            this.maxNumberOfResults = maxNumberOfResults;
            return this;
        }

        public ApplicationSearchDTO build() {
            return new ApplicationSearchDTO(this);
        }
    }
}
