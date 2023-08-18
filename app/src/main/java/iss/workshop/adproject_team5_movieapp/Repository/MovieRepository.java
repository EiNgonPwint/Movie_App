package iss.workshop.adproject_team5_movieapp.Repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import iss.workshop.adproject_team5_movieapp.Model.Movie;
import iss.workshop.adproject_team5_movieapp.Request.MovieApiClient;

public class MovieRepository {

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPage;

    public static MovieRepository getInstance(){
        if (instance == null){
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<Movie>> getMovies(){
        return movieApiClient.getMovies();
    }


    public void searchMovieApi(String query, int page){
        mQuery = query;
        mPage = page;
        movieApiClient.searchMoviesApi(query, page);
    }

    public void searchNextPage(){
        searchMovieApi(mQuery, mPage+1);
    }

    public LiveData<List<Movie>> getPopular(){
        return movieApiClient.getPopular();
    }

    public void searchMoviePopular(int page){

        mPage = page;
        movieApiClient.searchMoviesPopular( page);
    }
}
