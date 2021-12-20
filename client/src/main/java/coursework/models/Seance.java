package coursework.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Seance implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private LocalDate showDate;

    private LocalTime showTime;

    private Hall hall;

    private Film film;

    private String screen;

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHallName() {
        return hall.getHallName();
    }

    public String getFilmName() {
        return film.getFilmName();
    }

    public String getDate() {
        return showDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public Seance() { }

    public Seance(int id, LocalDate showDate, LocalTime showTime, Hall hall, Film film, String screen) {
        this.id = id;
        this.showDate = showDate;
        this.showTime = showTime;
        this.hall = hall;
        this.film = film;
        this.screen = screen;
    }

    public Seance(LocalDate showDate, LocalTime showTime, Hall hall, Film film, String screen) {
        this.showDate = showDate;
        this.showTime = showTime;
        this.hall = hall;
        this.film = film;
        this.screen = screen;
    }
}