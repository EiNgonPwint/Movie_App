package iss.workshop.adproject_team5_movieapp.Model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Favourite {
    private int id;
    @SerializedName("userId")
    private int user_id;
    @SerializedName("movieId")
    private int movie_id;
    private LocalDate date;
    public Favourite(){

    }
    public Favourite(int user_id, int movie_id, LocalDate date) {

        this.user_id = user_id;
        this.movie_id = movie_id;
        this.date=date;
    }
//    public Favourite(int user_id, int movie_id) {
//
//        this.user_id = user_id;
//        this.movie_id = movie_id;
//        // this.fromDate=fromDate;
//    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }



    public int getId() {
        return id;
    }



    public int getUser_id() {
        return user_id;
    }

    public int getMovie_id() {
        return movie_id;
    }


//    private LocalDate fromDate;
//    private User user;
//    private Movie movie;

//    public Favourite(int id, LocalDate fromDate, User user, Movie movie) {
//        this.id = id;
//        this.fromDate = fromDate;
//        this.user = user;
//        this.movie = movie;
//    }
    public Favourite(int id){
        this.id=id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public void setFromDate(LocalDate fromDate) {
//        this.fromDate = fromDate;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public void setMovie(Movie movie) {
//        this.movie = movie;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public LocalDate getFromDate() {
//        return fromDate;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public Movie getMovie() {
//        return movie;
//    }


}
