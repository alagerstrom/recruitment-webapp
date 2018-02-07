package se.kth.iv1201.boblaghei.dto;

import java.util.Date;

/**
 * DTO representing entity class <code>Availability</code>.
 */
public class AvailabilityDTO {

    private long id;
    private Date fromDate;
    private Date toDate;

    public AvailabilityDTO() {
    }

    public AvailabilityDTO(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public AvailabilityDTO(long id, Date fromDate, Date toDate) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "AvailabilityDTO{" +
                "id=" + id +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
