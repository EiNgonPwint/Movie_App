package iss.workshop.adproject_team5_movieapp.Request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import iss.workshop.adproject_team5_movieapp.AppExecutors;
import iss.workshop.adproject_team5_movieapp.Model.Movie;
import iss.workshop.adproject_team5_movieapp.Response.MovieResponse;
import iss.workshop.adproject_team5_movieapp.Response.MovieSearchResponse;
import iss.workshop.adproject_team5_movieapp.utils.TmdbApiCred;
import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    //inherits from LiveData
    private MutableLiveData<List<Movie>> mMovies = new MutableLiveData<>();
    private MutableLiveData<List<Movie>> mMoviesPopular;
    private static MovieApiClient instance;

    private GetMoviesRunnable getMoviesRunnable;
    private GetMoviesRunnablePopular getMoviesRunnablePopular;

    public static MovieApiClient getInstance(){
        if (instance == null){
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient(){

        mMovies = new MutableLiveData<>();
        mMoviesPopular = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies(){
        return mMovies;
    }

    public LiveData<List<Movie>> getPopular(){
        return mMoviesPopular;
    }


    public void searchMoviesApi(String query, int page) {

        if (getMoviesRunnable != null){
            getMoviesRunnable = null;
        }

        getMoviesRunnable = new GetMoviesRunnable(query, page);

        //retrieve data from tmdb api
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(getMoviesRunnable);

        //cancel the retrofit call, if there's no request in 5s
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);

            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    public void searchMoviesPopular(int page) {

        if (getMoviesRunnablePopular != null){
            getMoviesRunnablePopular = null;
        }

        getMoviesRunnablePopular = new GetMoviesRunnablePopular(page);

        final Future myHandler2 = AppExecutors.getInstance().networkIO().submit(getMoviesRunnablePopular);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call
                myHandler2.cancel(true);

            }
        }, 1000, TimeUnit.MILLISECONDS);

    }

    //Retrieving data from restapi by runnable class
    private class GetMoviesRunnable implements Runnable{

        private String query;
        private int page;
        boolean cancelRequest;

        public GetMoviesRunnable(String query, int page) {
            this.query = query;
            this.page = page;
            cancelRequest = false;
        }

            @Override
            public void run(){

                //Get the response objects
                try{
                    Response response = getMovies(query, page).execute();
                    if (cancelRequest){
                        return;
                    }
                    if (response.code()==200){
                        List<Movie> list = new ArrayList(((MovieSearchResponse)response.body()).getMovies());
                        if (page == 1){
                            //sending to live data
                            //use setValue for non background thread

                            //using background thread
                            mMovies.postValue(list);
                        }
                        else{

                            List<Movie> currentMovies = mMovies.getValue();
                            currentMovies.addAll(list);
                            //using background thread
                            mMovies.postValue(currentMovies);
                        }
                    }else{
                        String error =response.errorBody().string();
                        Log.v("Tag", "Error " +error);
                        //using background thread
                        mMovies.postValue(null);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    mMovies.postValue(null);
                }

            }

        private Call<MovieSearchResponse> getMovies(String query, int page){
            return RetrofitSvc.getMovieApi().searchMovie(
                    TmdbApiCred.API_KEY,
                    query,
                    page
            );
        }

        private void cancelSearchRequest(){
            Log.v("Tag","Cancelling search request");
            cancelRequest = true;
        }
    }


    private class GetMoviesRunnablePopular implements Runnable{

        private int page;
        boolean cancelRequest;


        public GetMoviesRunnablePopular(int page) {

            this.page = page;
            cancelRequest = false;
        }

        @Override
        public void run() {
            // Getting the response objects

            try{

                Response response2 = getPopular(page).execute();

                if (cancelRequest) {

                    return;
                }
                if (response2.code() == 200){
                    List<Movie> list = new ArrayList(((MovieSearchResponse)response2.body()).getMovies());
                    if (page == 1){

                        mMoviesPopular.postValue(list);

                    }else{
                        List<Movie> currentMovies = mMoviesPopular.getValue();
                        currentMovies.addAll(list);
                        mMoviesPopular.postValue(currentMovies);
                    }

                }else{
                    String error = response2.errorBody().string();
                    Log.v("Tag", "Error " +error);
                    mMoviesPopular.postValue(null);

                }

            } catch (IOException e) {
                e.printStackTrace();
                mMoviesPopular.postValue(null);

            }

        }


        private Call<MovieSearchResponse> getPopular( int page){
            return RetrofitSvc.getMovieApi().getPopular(
                    TmdbApiCred.API_KEY,
                    page
            );

        }

        private void cancelRequest(){
            Log.v("Tag", "Cancelling Search Request" );
            cancelRequest = true;
        }




}

//===============

    private class GetMovieRunnable implements Runnable {

        private int id;
        boolean cancelRequest;


        public GetMovieRunnable(int id) {

            this.id = id;
        }

        @Override
        public void run() {
            // Getting the response objects

            try {

                Response response3 = getMovieById(1).execute();

                if (response3.code() == 200) {
                    Log.d("MovieR", response3.body().toString());
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        private Call<Movie> getMovieById(int id) {
            return RetrofitSvc.getMovieApi().getMovie(
                    id,
                    TmdbApiCred.API_KEY
            );
        }

        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }


    }}
