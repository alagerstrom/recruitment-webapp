package se.kth.iv1201.boblaghei.dto;

import java.util.Date;

/**
 * DTO representing entity class <code>Availability</code>.
 */
public class AvailabilityDTO {

    private long id;
    private Date from;
    private Date to;
    private ApplicationDTO application;

    public AvailabilityDTO() {
    }

    public AvailabilityDTO(long id, Date from, Date to, ApplicationDTO application) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.application = application;
    }

    public AvailabilityDTO(Date from, Date to, ApplicationDTO application) {
        this.from = from;
        this.to = to;
        this.application = application;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public ApplicationDTO getApplication() {
        return application;
    }

    public void setApplication(ApplicationDTO application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "AvailabilityDTO{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", application=" + application +
                '}';
    }
}
