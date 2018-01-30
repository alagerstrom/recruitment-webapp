package se.kth.iv1201.boblaghei.dto;

import java.util.Set;

/**
 * DTO representing entity class <code>Competence</code>.
 */
public class CompetenceDTO {

    private String name;

    public CompetenceDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
