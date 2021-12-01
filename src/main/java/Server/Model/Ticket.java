package Server.Model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tickets")
@Entity
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JoinColumns({
            @JoinColumn(name = "row_id", referencedColumnName = "row_num", nullable = false),
            @JoinColumn(name = "seat_id", referencedColumnName = "seat_num", nullable = false)
    })
    @ManyToOne(optional = false)
    private Seat seats;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seance_id", nullable = false)
    private Seance seance;

    @Column(name = "ticket_status", length = 20)
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
}