package se.kth.iv1201.boblaghei.entity;

import se.kth.iv1201.boblaghei.dto.PersonDTO;

import javax.persistence.*;

/**
 *  O/R Mapping of the table Person in the database.
 */
@Entity
public class Person {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String personalNumber;

    @Column(nullable = false)
    private String email;

    @OneToOne
    private User user;

    public Person() {
    }

    public Person(String firstName, String lastName, String personalNumber, String email, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.email = email;
        this.user = user;}

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", email='" + email + '\'' +
                ", user=" + user +
                '}';
    }

    public PersonDTO getDTO() {
        return new PersonDTO(id, getFirstName(), getLastName(), getPersonalNumber(),
                getEmail(), getUser().getDTO());
    }
}
