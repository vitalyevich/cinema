package coursework.models;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalTime;

public class WorkingHour implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Instant workDate;

    private LocalTime startTime;

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