package se.kth.iv1201.boblaghei.dto;

import se.kth.iv1201.boblaghei.entity.User;

/**
 * DTO representing entity class <code>User</code>.
 */
public class UserDTO {

    private String username;
    private String password;
    private boolean enabled;

    public UserDTO() {
    }

    public UserDTO(String username, String password, boolean enabled) {
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
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    public User getEntity() {
        return new User(getUsername(), "", isEnabled());
    }
}
