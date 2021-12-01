package Server.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalTime;

@Table(name = "working_hours")
@Entity
public class WorkingHour implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "staff_id", nullable = false)
    private Integer id;

    @Column(name = "work_date", nullable = false)
    private Instant workDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "stop_time", nullable = false)
    private LocalTime stopTime;

    public LocalTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalTime stopTime) {
        this.stopTime = stopTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Instant getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Instant workDate) {
        this.workDate = workDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WorkingHour() { }

    public WorkingHour(int id, Instant workDate, LocalTime startTime, LocalTime stopTime) {
        this.id = id;
        this.workDate = workDate;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    public WorkingHour(Instant workDate, LocalTime startTime, LocalTime stopTime) {
        this.workDate = workDate;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }
}