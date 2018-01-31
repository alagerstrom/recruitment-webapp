package se.kth.iv1201.boblaghei.dto;

import java.util.Set;

/**
 * DTO representing entity class <code>Competence</code>.
 */
public class CompetenceDTO {

    private long id;
    private String name;

    public CompetenceDTO() {
    }

    public CompetenceDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompetenceDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CompetenceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
