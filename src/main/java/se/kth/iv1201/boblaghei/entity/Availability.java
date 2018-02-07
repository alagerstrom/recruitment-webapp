package se.kth.iv1201.boblaghei.entity;

import se.kth.iv1201.boblaghei.dto.AvailabilityDTO;

import javax.persistence.*;
import java.util.Date;

/**
 * O/R Mapping of the table Availability in the database.
 */
@Entity
public class Availability {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Date fromDate;

    @Column(nullable = false)
    private Date toDate;

    @ManyToOne
    private Application application;

    public Availability() {
    }

    public Availability(Date fromDate, Date toDate, Application application) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.application = application;
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

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Availability{" +
                "id=" + id +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", application=" + application +
                '}';
    }

    public AvailabilityDTO getDTO() {
        return new AvailabilityDTO(id, fromDate, toDate);
    }
}
