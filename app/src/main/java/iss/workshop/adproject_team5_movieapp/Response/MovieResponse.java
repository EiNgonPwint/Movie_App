package iss.workshop.adproject_team5_movieapp.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import iss.workshop.adproject_team5_movieapp.Model.Movie;

//for a single movie request
public class MovieResponse {

    @SerializedName("results")
    @Expose
    private Movie movie;

    public Movie getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }


}
