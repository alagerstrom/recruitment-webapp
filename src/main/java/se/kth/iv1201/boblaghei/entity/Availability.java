package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;
import java.util.Date;

/**
 *  O/R Mapping of the table Availability in the database.
 */
@Entity
public class Availability {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Date from;

    @Column(nullable = false)
    private Date to;

    @ManyToOne(cascade= CascadeType.ALL)
    private Application application;

    public Availability() {
    }

    public Availability(Date from, Date to, Application application) {
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
                ", from=" + from +
                ", to=" + to +
                ", application=" + application +
                '}';
    }
}
