package se.kth.iv1201.boblaghei.dto;

import se.kth.iv1201.boblaghei.entity.Person;

/**
 * A dto class that represents the response from the server sent on a successful login.
 */
public class LoginResponse {
    private String token;
    private Person person;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
