package se.kth.iv1201.boblaghei.dto;

import java.util.Set;

/**
 * DTO representing entity class <code>Status</code>.
 */
public class StatusDTO {

    private String name;

    public StatusDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
