package se.kth.iv1201.boblaghei.dto;

import se.kth.iv1201.boblaghei.entity.Role;
import se.kth.iv1201.boblaghei.entity.User;

/**
 * DTO representing entity class <code>UserRole</code>.
 */
public class UserRoleDTO {

    private UserDTO user;
    private RoleDTO role;

    public UserRoleDTO(UserDTO user, RoleDTO role) {
        this.user = user;
        this.role = role;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
}
