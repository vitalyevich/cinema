package coursework.models;

import java.io.Serializable;

public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String filmName;

    private Integer releaseDate;

    private Integer showTime;

    private String filmDescription;

    private Integer ageNum;

    private Double rating;

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getAgeNum() {
        return ageNum;
    }

    public void setAgeNum(Integer ageNum) {
        this.ageNum = ageNum;
    }

    public String getFilmDescription() {
        return filmDescription;
    }

    public void setFilmDescription(String filmDescription) {
        this.filmDescription = filmDescription;
    }

    public Integer getShowTime() {
        return showTime;
    }

    public void setShowTime(Integer showTime) {
        this.showTime = showTime;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return getCountryName();
    }

    public String getGenreName() {
        return getGenreName();
    }

    public Film () { }

    public Film(int id,String filmName, int releaseDate, int showTime, String filmDescription, int ageNum,
                 Double rating) {

        this.id = id;
        this.filmName = filmName;
        this.releaseDate = releaseDate;
        this.showTime = showTime;
        this.filmDescription = filmDescription;
        this.ageNum = ageNum;
        this.rating = rating;
    }

    public Film(String filmName, int releaseDate, int showTime, String filmDescription, int ageNum,
                 Double rating) {

        this.filmName = filmName;
        this.releaseDate = releaseDate;
        this.showTime = showTime;
        this.filmDescription = filmDescription;
        this.ageNum = ageNum;
        this.rating = rating;
    }
}