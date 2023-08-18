package iss.workshop.adproject_team5_movieapp.Model;

import com.google.gson.annotations.SerializedName;

public class WatchLater {

    private int id;
    @SerializedName("userId")
    private int user_id;
    @SerializedName("movieId")
    private int movie_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getMovie_id() {
        return movie_id;
    }
    public WatchLater(){}

    public WatchLater(int user_id, int movie_id) {
        this.id = id;
        this.user_id = user_id;
        this.movie_id = movie_id;
    }
}
