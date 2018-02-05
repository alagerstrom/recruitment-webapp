package se.kth.iv1201.boblaghei.dto;

import java.util.Date;

/**
 * DTO representing entity class <code>Application</code>.
 */
public class ApplicationDTO {

    private long id;
    private Date created;
    private StatusDTO status;
    private PersonDTO person;

    public ApplicationDTO() {
    }

    public ApplicationDTO(Date created, StatusDTO status, PersonDTO person) {
        this.created = created;
        this.status = status;
        this.person = person;
    }

    public ApplicationDTO(long id, Date created, StatusDTO status, PersonDTO person) {
        this.id = id;
        this.created = created;
        this.status = status;
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "ApplicationDTO{" +
                "id=" + id +
                ", created=" + created +
                ", status=" + status +
                ", person=" + person +
                '}';
    }
}
