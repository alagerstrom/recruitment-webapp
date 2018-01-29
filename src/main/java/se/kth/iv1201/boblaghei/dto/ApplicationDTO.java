package se.kth.iv1201.boblaghei.dto;

import java.util.Date;
import java.util.Set;

/**
 * DTO representing entity class <code>Application</code>.
 */
public class ApplicationDTO {

    private Date created;
    private StatusDTO status;
    private PersonDTO person;

    public ApplicationDTO(Date created, StatusDTO status, PersonDTO person) {
        this.created = created;
        this.status = status;
        this.person = person;
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
}
