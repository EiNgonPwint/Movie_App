package iss.workshop.adproject_team5_movieapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.ViewParent;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import iss.workshop.adproject_team5_movieapp.Adapter.MovieRecyclerView;
import iss.workshop.adproject_team5_movieapp.Adapter.MovieRecyclerViewRec;
import iss.workshop.adproject_team5_movieapp.Adapter.MovieRecyclerViewSmall;
import iss.workshop.adproject_team5_movieapp.Adapter.OnMovieClickListener;
import iss.workshop.adproject_team5_movieapp.Model.Favourite;
import iss.workshop.adproject_team5_movieapp.Model.MachineLearningTest;
import iss.workshop.adproject_team5_movieapp.Model.Movie;
import iss.workshop.adproject_team5_movieapp.Model.MovieRec;
import iss.workshop.adproject_team5_movieapp.Model.ResultsRec;
import iss.workshop.adproject_team5_movieapp.Model.User;
import iss.workshop.adproject_team5_movieapp.Request.RetrofitService;
import iss.workshop.adproject_team5_movieapp.Response.MovieSearchResponse;
import iss.workshop.adproject_team5_movieapp.ViewModel.MovieViewModel;
import iss.workshop.adproject_team5_movieapp.retrofit.RetrofitServiceFav;
import iss.workshop.adproject_team5_movieapp.retrofit.RetrofitServiceML;
import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import iss.workshop.adproject_team5_movieapp.utils.TmdbApiCred;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsFragment extends Fragment implements OnMovieClickListener {

    private RecyclerView recyclerView,recyclerView2;

    MovieRecyclerView movieRecyclerView;
    MovieRecyclerViewRec movieRecyclerView2;
    private MovieViewModel movieViewModel = new MovieViewModel();
    private TextView popularMovietxtview,recMovietxtview;
    private SearchView searchView;
    private List<Integer> favMovieIdsList;
    private List<Movie> favMovieList;
    private  int last_favourite_movie_id;
    private String last_favourite_movie_name;
    private List<Movie> recMovieList;
    private List<ResultsRec> rrList;
    private List<String> titleList;

    boolean displayPopular = true;
    User user;
    public FilmsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //createSearchView();
        favMovieIdsList = new ArrayList<>();
        favMovieList = new ArrayList<>();
        movieViewModel.searchMoviePopular(1);
        recMovieList = new ArrayList<>();
        rrList = new ArrayList<>();
        titleList = new ArrayList<>();
        displayPopular = true;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_films, container, false);
        // createSearchView();

        // Inflate the layout for this fragment

        //add user
        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable("user");



        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //createSearchView();

        //searchView=view.findViewById(R.id.search_view);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView2= view.findViewById(R.id.recyclerViewRec);

        recMovietxtview=view.findViewById(R.id.recLabel);
        popularMovietxtview = view.findViewById(R.id.popularLabel);
        searchView = view.findViewById(R.id.search_view);



        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);


        createRecyclerView();
        ObserveChange();
        ObservePopular();
        createSearchView();
//        ObserveChange1();
//        ObservePopular1();


        RetrofitService retrofitService = new RetrofitService();
        MovieApi movieApi = retrofitService.getRetrofit().create(MovieApi.class);
        movieApi.findByUserId(user.getUserId())
                .enqueue(new Callback<List<Favourite>>() {
                    @Override
                    public void onResponse(Call<List<Favourite>> call, Response<List<Favourite>> response) {
                        try {
                            if (response.isSuccessful()) {
                                List<Favourite> favList = response.body();
                                last_favourite_movie_id=favList.get(favList.size()-1).getMovie_id();

                            }
                            else {
                                Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                            }
                        }

                        catch (Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        RetrofitServiceFav retrofitService2 = new RetrofitServiceFav();
                        MovieApi movieApi2 = retrofitService2.getRetrofit().create(MovieApi.class);
                        movieApi2.getMovie(last_favourite_movie_id,TmdbApiCred.API_KEY)
                                .enqueue(new Callback<Movie>() {
                                    @Override
                                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                                        try {
                                            if (response.isSuccessful()) {

                                                last_favourite_movie_name= response.body().getTitle();
                                                Log.v("last",last_favourite_movie_name);


                                            } else {
                                                Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                                            }
                                        } catch (Exception e) {
                                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                        }

                                        RetrofitServiceML retrofitService2 = new RetrofitServiceML();
                                        MovieApi movieApi2 = retrofitService2.getRetrofit().create(MovieApi.class);
                                        MachineLearningTest mlt = new MachineLearningTest();
                                        mlt.setTitle("Fight Club");
                                        movieApi2.getMovieRec(mlt)
                                                .enqueue(new Callback<MovieRec>() {
                                                    @Override
                                                    public void onResponse(Call<MovieRec> call, Response<MovieRec> response) {
                                                        try {
                                                            if (response.isSuccessful()) {
                                                                MovieRec mRec = response.body();
                                                                rrList = mRec.getResultsRec();
                                                                for(ResultsRec rr:rrList){
                                                                    String title = rr.getTitle();
                                                                    titleList.add(title);
                                                                    Log.v("help", title);
                                                                }
                                                            }
                                                            else {
                                                                //Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                                                            }
                                                        } catch (Exception e) {
                                                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                                        }


                                                        RetrofitServiceFav retrofitService3 = new RetrofitServiceFav();
                                                        MovieApi movieApi3 = retrofitService3.getRetrofit().create(MovieApi.class);
                                                        for (String title:titleList){
                                                            movieApi3.searchMovie(TmdbApiCred.API_KEY, title, 1)
                                                                    .enqueue(new Callback<MovieSearchResponse>() {
                                                                        @Override
                                                                        public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                                                                            try {
                                                                                if (response.isSuccessful()) {

                                                                                    MovieSearchResponse msr = response.body();
                                                                                    List<Movie> mList = msr.getMovies();
                                                                                    Movie m = mList.get(0);
                                                                                    recMovieList.add(m);

                                                                                } else {
                                                                                    Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                                                                                }
                                                                            } catch (Exception e) {
                                                                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                                                            }
                                                                            movieRecyclerView2.setMovieList(recMovieList);


                                                                        }

                                                                        @Override
                                                                        public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

                                                                        }
                                                                    });
                                                        }



                                                    }


                                                    @Override
                                                    public void onFailure(Call<MovieRec> call, Throwable t) {

                                                    }
                                                });


                                    }

                                    @Override
                                    public void onFailure(Call<Movie> call, Throwable t) {

                                    }
                                });




//                        for (int id:favMovieIdsList){
//                            movieApi1.getMovie(id, TmdbApiCred.API_KEY)
//                                    .enqueue(new Callback<Movie>() {
//                                        @Override
//                                        public void onResponse(Call<Movie> call, Response<Movie> response) {
//                                            try {
//                                                if (response.isSuccessful()) {
//
//                                                    Movie m = response.body();
//                                                    favMovieList.add(m);
//
//                                                } else {
//                                                    Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
//                                                }
//                                            } catch (Exception e) {
//                                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//                                            }
//                                            movieRecyclerView2.setMovieList(favMovieList);
//
//
//                                        }
//
//                                        @Override
//                                        public void onFailure(Call<Movie> call, Throwable t) {
//
//                                        }
//                                    });
//                        }
                    }


                    @Override
                    public void onFailure(Call<List<Favourite>> call, Throwable t) {

                    }
                });

    }

    @Override
    public void onMovieClick(View v, int position) {
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        ViewParent parent = v.getParent();
        switch (((RecyclerView) parent).getId()) {
            case R.id.recyclerView:
                Movie movie2=movieRecyclerView.getSelectedMovie(position);

                intent.putExtra("movie", movie2);
                intent.putExtra("user",user);
                startActivity(intent);
                break;
            case R.id.recyclerViewRec:

                Movie movie1=movieRecyclerView2.getSelectedMovie(position);
                passMovieDetails(intent, movie1, user);
                break;
        }

        //mm some attributes null

    }
    private void createSearchView(){



        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when search view is clicked
                displayPopular = false;
                popularMovietxtview.setVisibility(View.GONE);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //when search view is closed
                popularMovietxtview.setVisibility(View.VISIBLE);
                movieViewModel.searchMoviePopular(1);
                displayPopular = true;
                return false;
            }
        });

        // Make search query
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                // query is obtained from search view on submit
                movieViewModel.searchMovieApi(
                        query,
                        1
                );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                movieViewModel.searchMovieApi(
                        newText,
                        1
                );
                return false;
            }
        });
    }

    private void ObserveChange(){

        movieViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>(){
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies != null){
                    for (Movie movie:movies){
                        Log.v("Tagy", "onChanged " + movie.getTitle());
                        movieRecyclerView.setMovieList(movies);
                    }
                }

            }
        });
    }

    private void ObservePopular(){
        movieViewModel.getPopular().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies != null){
                    movieRecyclerView.setMovieList(movies);
                }
            }
        });


    }

    private void createRecyclerView(){
        //pass listener via constructor, cant pass Live Data this way


        //pass listener via constructor, cant pass Live Data this way
        movieRecyclerView = new MovieRecyclerView(this);
        movieRecyclerView2 = new MovieRecyclerViewRec(this);

        recyclerView2.setAdapter(movieRecyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        movieRecyclerView = new MovieRecyclerView(this);
        recyclerView.setAdapter(movieRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        // RecyclerView Pagination
        // Loading next page of api response
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)){
                    // Here we need to display the next search results on the next page of api
                    movieViewModel.searchNextpage();

                }

            }
        });


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


}