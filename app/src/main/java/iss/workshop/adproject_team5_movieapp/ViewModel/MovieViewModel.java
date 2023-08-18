package iss.workshop.adproject_team5_movieapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import iss.workshop.adproject_team5_movieapp.Model.Movie;
import iss.workshop.adproject_team5_movieapp.Repository.MovieRepository;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieViewModel(){
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getMovies() {
        return movieRepository.getMovies();
    }

    public LiveData<List<Movie>> getPopular(){
        return movieRepository.getPopular();
    }

    public void searchMovieApi(String query, int page){
        movieRepository.searchMovieApi(query, page);
    }

    public void searchMoviePopular(int page){
        movieRepository.searchMoviePopular(page);
    }

    public void searchNextpage(){
        movieRepository.searchNextPage();
    }
}
