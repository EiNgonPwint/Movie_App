package iss.workshop.adproject_team5_movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.adproject_team5_movieapp.Adapter.MovieRecyclerViewSmall;
import iss.workshop.adproject_team5_movieapp.Adapter.OnMovieClickListener;
import iss.workshop.adproject_team5_movieapp.Model.Favourite;
import iss.workshop.adproject_team5_movieapp.Model.Movie;
import iss.workshop.adproject_team5_movieapp.Model.User;
import iss.workshop.adproject_team5_movieapp.Model.WatchLater;
import iss.workshop.adproject_team5_movieapp.Model.Watched;
import iss.workshop.adproject_team5_movieapp.Request.RetrofitService;
import iss.workshop.adproject_team5_movieapp.retrofit.RetrofitServiceFav;
import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import iss.workshop.adproject_team5_movieapp.utils.TmdbApiCred;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHistory extends AppCompatActivity implements OnMovieClickListener {

    private RecyclerView favRecyclerView, wlRecyclerView, watchedRecyclerView;
    MovieRecyclerViewSmall movieRecyclerView1, movieRecyclerView2, movieRecyclerView3;
    private List<Integer> favMovieIdsList, wlMovieIdsList, watchedMovieIdsList;
    private List<Movie> favMovieList, watchLaterMovieList, watchedMovieList;
    private TextView wlLabel, favLabel, watchedLabel;
    User user =new User();
    User user1= new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        favMovieIdsList = new ArrayList<>();
        wlMovieIdsList = new ArrayList<>();
        watchedMovieIdsList = new ArrayList<>();
        favMovieList = new ArrayList<>();
        watchLaterMovieList = new ArrayList<>();
        watchedMovieList = new ArrayList<>();
        favRecyclerView = findViewById(R.id.recyclerViewFav);
        wlRecyclerView = findViewById(R.id.recyclerViewWL);
        watchedRecyclerView = findViewById(R.id.recyclerViewWatched);
        wlLabel= findViewById(R.id.watchLaterLabel);
        favLabel= findViewById(R.id.favLabel);
        watchedLabel = findViewById(R.id.watchedLabel);

        user = (User) getIntent().getSerializableExtra("user");
        String uName = user.getName();
        wlLabel.setText(uName+"'s Watch Later Movies");
        favLabel.setText(uName+"'s Favourite Movies");
        watchedLabel.setText(uName+"'s Watched Movies");

        user1 = (User) getIntent().getSerializableExtra("user1");

        createRecyclerView();

        RetrofitService retrofitService = new RetrofitService();
        MovieApi movieApi = retrofitService.getRetrofit().create(MovieApi.class);
        movieApi.findByUserId(user.getUserId())
                .enqueue(new Callback<List<Favourite>>() {
                    @Override
                    public void onResponse(Call<List<Favourite>> call, Response<List<Favourite>> response) {
                        try {
                            if (response.isSuccessful()) {
                                List<Favourite> favList = response.body();
                                for(Favourite f:favList){
                                    int i = f.getMovie_id();
                                    favMovieIdsList.add(i);
                                }
                            }
                            else {
                                //Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            //Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }


                        RetrofitServiceFav retrofitService1 = new RetrofitServiceFav();
                        MovieApi movieApi1 = retrofitService1.getRetrofit().create(MovieApi.class);
                        for (int id:favMovieIdsList){
                            movieApi1.getMovie(id, TmdbApiCred.API_KEY)
                                    .enqueue(new Callback<Movie>() {
                                        @Override
                                        public void onResponse(Call<Movie> call, Response<Movie> response) {
                                            try {
                                                if (response.isSuccessful()) {

                                                    Movie m = response.body();
                                                    favMovieList.add(m);

                                                } else {
                                                    //Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                                                }
                                            } catch (Exception e) {
                                                //Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                            movieRecyclerView1.setMovieList(favMovieList);

//                                            RetrofitService retrofitService2= new RetrofitService();
//                                            MovieApi movieApi2=retrofitService2.getRetrofit().create(MovieApi.class);
//                                            movieApi2.getMovieCredits(response.body().getId(),TmdbApiCred.API_KEY)
//                                                    .enqueue(new Callback<Credits>() {
//                                                        @Override
//                                                        public void onResponse(Call<Credits> call, Response<Credits> response) {
//                                                            Credits c= response.body();
//                                                            int i=c.getId();
//
//                                                        }
//
//                                                        @Override
//                                                        public void onFailure(Call<Credits> call, Throwable t) {
//
//                                                        }
//                                                    });

                                        }

                                        @Override
                                        public void onFailure(Call<Movie> call, Throwable t) {

                                        }
                                    });
                        }
                    }


                    @Override
                    public void onFailure(Call<List<Favourite>> call, Throwable t) {

                    }
                });


        RetrofitService retrofitServiceWl = new RetrofitService();
        MovieApi movieApiWl = retrofitServiceWl.getRetrofit().create(MovieApi.class);
        movieApiWl.findWLByUserId(user.getUserId())
                .enqueue(new Callback<List<WatchLater>>() {
                    @Override
                    public void onResponse(Call<List<WatchLater>> call, Response<List<WatchLater>> response) {
                        try {
                            if (response.isSuccessful()) {
                                List<WatchLater> wlList = response.body();
                                for(WatchLater wl:wlList){
                                    int i = wl.getMovie_id();
                                    wlMovieIdsList.add(i);
                                }
                            }
                            else {
                                //Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            //Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }


                        RetrofitServiceFav retrofitServiceWl1 = new RetrofitServiceFav();
                        MovieApi movieApi1 = retrofitServiceWl1.getRetrofit().create(MovieApi.class);
                        for (int id:wlMovieIdsList){
                            movieApi1.getMovie(id, TmdbApiCred.API_KEY)
                                    .enqueue(new Callback<Movie>() {
                                        @Override
                                        public void onResponse(Call<Movie> call, Response<Movie> response) {
                                            try {
                                                if (response.isSuccessful()) {

                                                    Movie m = response.body();
                                                    watchLaterMovieList.add(m);

                                                } else {
                                                    //Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                                                }
                                            } catch (Exception e) {
                                                //Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                            movieRecyclerView2.setMovieList(watchLaterMovieList);

                                        }

                                        @Override
                                        public void onFailure(Call<Movie> call, Throwable t) {

                                        }
                                    });
                        }
                    }


                    @Override
                    public void onFailure(Call<List<WatchLater>> call, Throwable t) {

                    }
                });


        RetrofitService retrofitServiceWatched = new RetrofitService();
        MovieApi movieApiWatched = retrofitServiceWatched.getRetrofit().create(MovieApi.class);
        movieApiWatched.findWatchedByUserId(user.getUserId())
                .enqueue(new Callback<List<Watched>>() {
                    @Override
                    public void onResponse(Call<List<Watched>> call, Response<List<Watched>> response) {
                        try {
                            if (response.isSuccessful()) {
                                List<Watched> watchedList = response.body();
                                for(Watched watched:watchedList){
                                    int i = watched.getMovie_id();
                                    watchedMovieIdsList.add(i);
                                }
                            }
                            else {
                                //Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            //Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }


                        RetrofitServiceFav retrofitServiceWatched1 = new RetrofitServiceFav();
                        MovieApi movieApi1 = retrofitServiceWatched1.getRetrofit().create(MovieApi.class);
                        for (int id:watchedMovieIdsList){
                            movieApi1.getMovie(id, TmdbApiCred.API_KEY)
                                    .enqueue(new Callback<Movie>() {
                                        @Override
                                        public void onResponse(Call<Movie> call, Response<Movie> response) {
                                            try {
                                                if (response.isSuccessful()) {

                                                    Movie m = response.body();
                                                    watchedMovieList.add(m);

                                                } else {
                                                    //Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                                                }
                                            } catch (Exception e) {
                                                //Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                            movieRecyclerView3.setMovieList(watchedMovieList);

                                        }

                                        @Override
                                        public void onFailure(Call<Movie> call, Throwable t) {

                                        }
                                    });
                        }
                    }


                    @Override
                    public void onFailure(Call<List<Watched>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onMovieClick(View v, int position) {
        Intent intent = new Intent(UserHistory.this, MovieDetailsActivity.class);
        ViewParent parent = v.getParent();
        switch (((RecyclerView) parent).getId()) {
            case R.id.recyclerViewFav:
                Movie m1 = movieRecyclerView1.getSelectedMovie(position);
                passMovieDetails(intent, m1, user1);
                break;
            case R.id.recyclerViewWL:
                Movie m2 = movieRecyclerView2.getSelectedMovie(position);
                passMovieDetails(intent, m2, user1);
                break;
            case R.id.recyclerViewWatched:
                Movie m3 = movieRecyclerView3.getSelectedMovie(position);
                passMovieDetails(intent, m3, user1);
                break;
        }

    }


    private void passMovieDetails(Intent intent, Movie m, User user){
        intent.putExtra("mId", m.getId());
        intent.putExtra("mTitle", m.getTitle());
        intent.putExtra("mBdPath", m.getBackdropPath());
        intent.putExtra("mPPath", m.getPosterPath());
        intent.putExtra("mOverview", m.getOverview());
        intent.putExtra("mVoteCount", m.getVoteCount());
        intent.putExtra("mVoteAvg", m.getVoteAverage());
        intent.putExtra("mRuntime", m.getRuntime());
        intent.putExtra("mReleaseDate", m.getReleaseDate());
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void createRecyclerView(){

        //pass listener via constructor, cant pass Live Data this way
        movieRecyclerView1 = new MovieRecyclerViewSmall(this);

        favRecyclerView.setAdapter(movieRecyclerView1);
        favRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieRecyclerView2 = new MovieRecyclerViewSmall(this);

        wlRecyclerView.setAdapter(movieRecyclerView2);
        wlRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieRecyclerView3 = new MovieRecyclerViewSmall(this);

        watchedRecyclerView.setAdapter(movieRecyclerView3);
        watchedRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }
}
