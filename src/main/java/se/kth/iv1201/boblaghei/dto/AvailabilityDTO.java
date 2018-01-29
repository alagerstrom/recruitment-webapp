package se.kth.iv1201.boblaghei.dto;

import java.util.Date;

/**
 * DTO representing entity class <code>Availability</code>.
 */
public class AvailabilityDTO {

    private Date from;
    private Date to;
    private ApplicationDTO application;

    public AvailabilityDTO(Date from, Date to, ApplicationDTO application) {
        this.from = from;
        this.to = to;
        this.application = application;
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
}
