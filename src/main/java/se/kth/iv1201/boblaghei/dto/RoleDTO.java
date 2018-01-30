package se.kth.iv1201.boblaghei.dto;

import java.util.Set;

/**
 * DTO representing entity class <code>Role</code>.
 */
public class RoleDTO {

    private String name;

    public RoleDTO(String name) {
        this.name = name;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
