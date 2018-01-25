package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;

/**
 *  O/R Mapping of the table Person in the database.
 */

@Entity
public class Person {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    private String personalNumber;

    private String email;

    private String username;

    private String password;

    @ManyToOne
    private long roleId;

    public Person() {
    }

    public Person(String firstName, String lastName, String personalNumber, String email, String username, String password, long roleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
