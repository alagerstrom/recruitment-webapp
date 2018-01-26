package se.kth.iv1201.boblaghei.entity;

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

    @OneToOne(mappedBy = "user")
    private Person person;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles;

    public User() {
    }

    public User(String username, String password, boolean enabled, Person person, Set<UserRole> userRoles) {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", person=" + person +
                ", userRoles=" + userRoles +
                '}';
    }
}
