package se.kth.iv1201.boblaghei.dto;

import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.UserRole;

import java.util.Set;

/**
 * DTO representing entity class <code>User</code>.
 */
public class UserDTO {
    private String username;
    private String password;
    private boolean enabled;
    private PersonDTO person;
    private Set<UserRoleDTO> userRoles;

    public UserDTO(String username, String password, boolean enabled, PersonDTO person, Set<UserRoleDTO> userRoles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.person = person;
        this.userRoles = userRoles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public Set<UserRoleDTO> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoleDTO> userRoles) {
        this.userRoles = userRoles;
    }
}
