package coursework.models;
import java.io.Serializable;

public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Seat seats;

    private Seance seance;

    private String ticketStatus;

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public Seat getSeats() {
        return seats;
    }

    public void setSeats(Seat seats) {
        this.seats = seats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Ticket() { }

    public Ticket(int id, Seat seats, Seance seance, String ticketStatus) {
        this.id = id;
        this.seats = seats;
        this.seance = seance;
        this.ticketStatus = ticketStatus;
    }

    public Ticket(Seat seats, Seance seance, String ticketStatus) {
        this.seats = seats;
        this.seance = seance;
        this.ticketStatus = ticketStatus;
    }

    public Integer getSeanceId() {
        return seance.getId();
    }

    public String getHallId() {
        return seance.getHall().getHallName();
    }

    public Integer getRowId() {
        return seats.getId().getRowNum();
    }

    public Integer getSeatId() {
        return seats.getId().getSeatNum();
    }

}