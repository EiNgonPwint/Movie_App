package iss.workshop.adproject_team5_movieapp.Model;

import java.time.LocalDate;

public class Rating {

    private int id;
    private LocalDate fromDate;

    private int movieId;
    private int userId;
    private float rate;

    public int getId() {
        return id;
    }


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getUserId() {
        return userId;
    }

    public float getRate() {
        return rate;
    }

    public Rating(int user_id, int movie_id, float rate) {

        this.userId = user_id;
        this.movieId = movie_id;
        this.rate=rate;
        // this.fromDate=fromDate;
    }
    public Rating(){}
    public void setfromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }
}
