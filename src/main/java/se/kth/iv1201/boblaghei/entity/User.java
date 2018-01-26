package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;

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

    @OneToOne
    private Person person;

    @OneToMany
    private UserRole userRole;

    public User() {
    }

    public User(String username, String password, boolean enabled, Person person, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.person = person;
        this.userRole = userRole;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
