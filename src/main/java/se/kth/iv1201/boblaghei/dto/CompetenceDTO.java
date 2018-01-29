package se.kth.iv1201.boblaghei.dto;

import java.util.Set;

/**
 * DTO representing entity class <code>Competence</code>.
 */
public class CompetenceDTO {

    private String name;
    private Set<CompetenceProfileDTO> competenceProfiles;

    public CompetenceDTO(String name, Set<CompetenceProfileDTO> competenceProfiles) {
        this.name = name;
        this.competenceProfiles = competenceProfiles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CompetenceProfileDTO> getCompetenceProfiles() {
        return competenceProfiles;
    }

    public void setCompetenceProfiles(Set<CompetenceProfileDTO> competenceProfiles) {
        this.competenceProfiles = competenceProfiles;
    }
}
