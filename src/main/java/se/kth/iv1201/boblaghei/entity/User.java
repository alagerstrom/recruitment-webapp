package se.kth.iv1201.boblaghei.entity;

import se.kth.iv1201.boblaghei.dto.UserDTO;

import javax.persistence.*;
import java.util.Set;

/**
 *  O/R Mapping of the table User in the database.
 */
@Entity
public class User {

    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    public User() {
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    /**
     * Returns the <code>User</code> as a <code>UserDTO</code> without including password.
     * @return UserDTO representation excluding password.
     */
    public UserDTO getDTO() {
        return new UserDTO(getUsername(), "", isEnabled());
    }

    /**
     * Currently only used for testing purposes, not to be used in production code.
     * @return complete UserDTO representation of User.
     */
    public UserDTO getDTOWithPassword() {
        return new UserDTO(getUsername(), getPassword(), isEnabled());
    }
}
