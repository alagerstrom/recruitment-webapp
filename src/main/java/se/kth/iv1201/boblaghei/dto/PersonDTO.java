package se.kth.iv1201.boblaghei.dto;

import se.kth.iv1201.boblaghei.entity.Application;
import se.kth.iv1201.boblaghei.entity.User;

import java.util.Set;

/**
 * DTO representing entity class <code>Person</code>.
 */
public class PersonDTO {

    private String firstName;
    private String lastName;
    private String personalNumber;
    private String email;
    private UserDTO user;



    public PersonDTO(String firstName, String lastName, String personalNumber, String email, UserDTO user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.email = email;
        this.user = user;}

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
