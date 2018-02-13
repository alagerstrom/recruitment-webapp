package se.kth.iv1201.boblaghei.dto;

/**
 * DTO representing entity class <code>Person</code>.
 */
public class PersonDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String personalNumber;
    private String email;
    private UserDTO user = new UserDTO();

    public PersonDTO() {
    }

    public PersonDTO(long id, String firstName, String lastName, String personalNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.email = email;
    }

    public PersonDTO(String firstName, String lastName, String personalNumber, String email, UserDTO user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.email = email;
        this.user = user;
    }

    public PersonDTO(long id, String firstName, String lastName, String personalNumber, String email, UserDTO user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.email = email;
        this.user = user;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", email='" + email + '\'' +
                ", user=" + user +
                '}';
    }
}
