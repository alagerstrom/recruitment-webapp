package se.kth.iv1201.boblaghei.dto;

import java.util.Set;

/**
 * DTO representing entity class <code>Status</code>.
 */
public class StatusDTO {

    private String name;
    private Set<ApplicationDTO> applications;

    public StatusDTO(String name, Set<ApplicationDTO> applications) {
        this.name = name;
        this.applications = applications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ApplicationDTO> getApplications() {
        return applications;
    }

    public void setApplications(Set<ApplicationDTO> applications) {
        this.applications = applications;
    }
}
