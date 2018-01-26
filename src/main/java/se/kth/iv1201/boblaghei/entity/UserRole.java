package se.kth.iv1201.boblaghei.entity;


import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class UserRole {

    @Id
    private String username;

    @ManyToOne
    private Role role;

    @ManyToOne
    private User user;

    public UserRole() {
    }

    public UserRole(String username, Role role, User user) {
        this.username = username;
        this.role = role;
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "username='" + username + '\'' +
                ", role=" + role +
                ", user=" + user +
                '}';
    }
}
