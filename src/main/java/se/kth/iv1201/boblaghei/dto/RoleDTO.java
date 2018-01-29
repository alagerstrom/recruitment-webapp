package se.kth.iv1201.boblaghei.dto;

import java.util.Set;

/**
 * DTO representing entity class <code>Role</code>.
 */
public class RoleDTO {

    private String name;
    private Set<UserRoleDTO> userRoles;

    public RoleDTO(String name, Set<UserRoleDTO> userRoles) {
        this.name = name;
        this.userRoles = userRoles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRoleDTO> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoleDTO> userRoles) {
        this.userRoles = userRoles;
    }
}
