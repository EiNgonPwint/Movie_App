package iss.workshop.adproject_team5_movieapp;

import static iss.workshop.adproject_team5_movieapp.R.id.reviewDetailBtn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import iss.workshop.adproject_team5_movieapp.Model.Favourite;
import iss.workshop.adproject_team5_movieapp.Model.Movie;
import iss.workshop.adproject_team5_movieapp.Model.Rating;
import iss.workshop.adproject_team5_movieapp.Model.Result;
import iss.workshop.adproject_team5_movieapp.Model.Review;
import iss.workshop.adproject_team5_movieapp.Model.Trailer;
import iss.workshop.adproject_team5_movieapp.Model.User;
import iss.workshop.adproject_team5_movieapp.Model.WatchLater;
import iss.workshop.adproject_team5_movieapp.Model.Watched;
import iss.workshop.adproject_team5_movieapp.Request.RetrofitService;
import iss.workshop.adproject_team5_movieapp.Request.RetrofitServiceString;
import iss.workshop.adproject_team5_movieapp.retrofit.RetrofitServiceFav;
import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import iss.workshop.adproject_team5_movieapp.utils.TmdbApiCred;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView movieBackdrop, moviePoster;
    private TextView titleDetails, descDetails,ratingDigit,runtime, releaseDate, favLabel, watchLabel;
    private RatingBar ratingBar;
    private Button reviewBtn, button1,castBtn,crewBtn, whereToWatch, trailer, cinema,wantToWatchBtn;
    private ImageView fav, watchlater, watch;
    private EditText reviewText;
    private List<Rating> ratingByMId;
    String text;
    private  int totalRating;
    User user;
    Movie movie;
    List<Result> rlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        castBtn = findViewById(R.id.castBtn);
        crewBtn= findViewById(R.id.crewBtn);
        wantToWatchBtn=findViewById(R.id.wantToWatch);
        whereToWatch = findViewById(R.id.whereToWatch);
        trailer = findViewById(R.id.trailerBtn);
        cinema = findViewById(R.id.cinemaBtn);
        runtime = findViewById(R.id.runtime);
        releaseDate = findViewById(R.id.releaseDate);
        movie = new Movie();
        Intent intent= getIntent();
        if (getIntent().hasExtra("movie")){
            movie = getIntent().getParcelableExtra("movie");

            RetrofitServiceFav retrofitService = new RetrofitServiceFav();
            MovieApi movieApi1 = retrofitService.getRetrofit().create(MovieApi.class);
            movieApi1.getMovie(movie.getId(), TmdbApiCred.API_KEY)
                    .enqueue(new Callback<Movie>() {
                        @Override
                        public void onResponse(Call<Movie> call, Response<Movie> response) {
                            try {
                                if (response.isSuccessful()) {

                                    movie = response.body();
                                    updateMRuntime(movie);

                                } else {
                                    Log.v("error", response.errorBody().string());
                                }
                            } catch (Exception e) {
                                try {
                                    Log.v("error", response.errorBody().string());
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Movie> call, Throwable t) {

                        }
                    });
        }
        else {
            movie.setId(intent.getIntExtra("mId", 0));
            movie.setTitle(intent.getStringExtra("mTitle"));
            movie.setBackdropPath(intent.getStringExtra("mBdPath"));
            movie.setPosterPath(intent.getStringExtra("mPPath"));
            movie.setOverview(intent.getStringExtra("mOverview"));
            movie.setVoteCount(intent.getIntExtra("mVoteCount", 0));
            movie.setVoteAverage(intent.getDoubleExtra("mVoteAvg", 0.0));
            movie.setRuntime(intent.getIntExtra("mRuntime",0));
            movie.setReleaseDate(intent.getStringExtra("mReleaseDate"));
        }
        user = (User) intent.getSerializableExtra("user");
        //Movie movie = getIntent().getParcelableExtra("movie");
        //add user from filmsFragment
        //doesnt hit this for list frag but will hit for film frag
        //user = (User)getIntent().getSerializableExtra("user");

        movieBackdrop = findViewById(R.id.movieBackdrop);
        moviePoster = findViewById(R.id.moviePoster);
        titleDetails = findViewById(R.id.textView_title_details);
        descDetails = findViewById(R.id.textView_desc_details);
        ratingBar = findViewById(R.id.ratingBar_details);
        ratingDigit=findViewById(R.id.ratingDigit);


        fav=findViewById(R.id.favBtn);
        favLabel = findViewById(R.id.favLabel);
        watch=findViewById(R.id.watchBtn);
        watchLabel = findViewById(R.id.watchLabel);
        watchlater=findViewById(R.id.watchLaterBtn);

        reviewText = findViewById(R.id.reviewText);
        reviewBtn = findViewById(R.id.reviewBtn);
        ratingByMId=new ArrayList<>();
        totalRating=0;

        RetrofitService retrofitService = new RetrofitService();
        MovieApi movieApi = retrofitService.getRetrofit().create(MovieApi.class);
        button1 = findViewById(reviewDetailBtn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailsActivity.this,ReviewDetailActivity.class);
                intent.putExtra("MovieId",movie.getId());
                startActivity(intent);
            }
        });


        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = reviewText.getText().toString();
                System.out.println(text);
                //User u2 = new User(2,"Yatanar");
                Review review1 = new Review(user.getUserId(),movie.getId(),user.getName(),text);
                System.out.println(review1);
                movieApi.saveReview(review1)
                        .enqueue(new Callback<Review>() {
                            @Override
                            public void onResponse(Call<Review> call, Response<Review> response) {
                                if(response.isSuccessful()) {
                                    reviewText.setText("");
                                    Toast.makeText(MovieDetailsActivity.this, "save successfully", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<Review> call, Throwable t) {
                                Toast.makeText(MovieDetailsActivity.this, "failed to save ", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });

        System.out.println(text);




        //User u1 = new User(1, "Amanda"); //Need to get from session
        Favourite f1 = new Favourite(user.getUserId(), movie.getId(), LocalDate.now());

        Watched w1= new Watched(user.getUserId(), movie.getId());
        Favourite isFav= new Favourite();
        Watched isWatched= new Watched();
        movieApi.findFavouriteByUserIdAndMovieId(user.getUserId(),movie.getId())
                .enqueue(new Callback<Favourite>() {
                    @Override
                    public void onResponse(Call<Favourite> call, Response<Favourite> response) {
                        isFav.setMovie_id(response.body().getMovie_id());
                        if(isFav.getMovie_id()==movie.getId()){
                            fav.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                            fav.setSelected(true);
                            favLabel.setText("Liked");
                        }
                    }

                    @Override
                    public void onFailure(Call<Favourite> call, Throwable t) {

                    }
                });
        movieApi.findWatchedByUserIdAndMovieId(user.getUserId(),movie.getId())
                .enqueue(new Callback<Watched>() {
                    @Override
                    public void onResponse(Call<Watched> call, Response<Watched> response) {
                        isWatched.setMovie_id(response.body().getMovie_id());
                        if(isWatched.getMovie_id()==movie.getId()){
                            watch.setBackgroundResource(R.drawable.ic_baseline_watched_24);
                            watch.setSelected(true);
                            watchLabel.setText("Watched");
                        }
                    }

                    @Override
                    public void onFailure(Call<Watched> call, Throwable t) {

                    }
                });

        WatchLater isWatchLater= new WatchLater();
        WatchLater wl1 = new WatchLater(user.getUserId(), movie.getId());
        movieApi.findWatchLaterByUserIdAndMovieId(user.getUserId(),movie.getId())
                .enqueue(new Callback<WatchLater>() {
                    @Override
                    public void onResponse(Call<WatchLater> call, Response<WatchLater> response) {
                        isWatchLater.setMovie_id(response.body().getMovie_id());
                        if(isWatchLater.getMovie_id()==movie.getId()){
                            watchlater.setBackgroundResource(R.drawable.ic_baseline_watch_later_24);
                            watchlater.setSelected(true);
                        }
                    }

                    @Override
                    public void onFailure(Call<WatchLater> call, Throwable t) {

                    }
                });




        fav.setOnClickListener(view -> {

            if(fav.isSelected()){
                fav.setSelected(false);
                favLabel.setText("Like");

                movieApi.deleteFavourite(user.getUserId(),movie.getId())
                        .enqueue(new Callback<Favourite>() {
                            @Override
                            public void onResponse(Call<Favourite> call, Response<Favourite> response) {
                                fav.setBackgroundResource(R.drawable.ic_outline_favorite_24);

                                Toast.makeText(MovieDetailsActivity.this, "Removed from Favourites", Toast.LENGTH_SHORT)
                                        .show();

                            }

                            @Override
                            public void onFailure(Call<Favourite> call, Throwable t) {
                                Toast.makeText(MovieDetailsActivity.this, "Failed to remove favourite", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });

            }
            else{
                fav.setSelected(true);
                favLabel.setText("Liked");
                movieApi.save(f1)
                        .enqueue(new Callback<Favourite>() {
                            @Override
                            public void onResponse(Call<Favourite> call, Response<Favourite> response) {
                                fav.setBackgroundResource(R.drawable.ic_baseline_favorite_24);

                                Toast.makeText(MovieDetailsActivity.this, "Added to Favourites", Toast.LENGTH_SHORT)
                                        .show();
                            }
                            @Override
                            public void onFailure(Call<Favourite> call, Throwable t) {
                                Toast.makeText(MovieDetailsActivity.this, "Failed to add to Favourites", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });}

        });

        watch.setOnClickListener(view -> {

            if (watch.isSelected() && ratingBar.getRating()!=0){
                Toast.makeText(MovieDetailsActivity.this, "Unable to remove from Watched list, please set rating to 0", Toast.LENGTH_SHORT)
                        .show();
            }

            else if(watch.isSelected() && ratingBar.getRating()==0){
                watch.setSelected(false);
                watchLabel.setText("Watch");

                movieApi.deleteWatched(user.getUserId(),movie.getId())
                        .enqueue(new Callback<Watched>() {
                            @Override
                            public void onResponse(Call<Watched> call, Response<Watched> response) {
                                watch.setBackgroundResource(R.drawable.ic_outline_watched_24);

                                Toast.makeText(MovieDetailsActivity.this, "Removed from Watched list", Toast.LENGTH_SHORT)
                                        .show();

                            }

                            @Override
                            public void onFailure(Call<Watched> call, Throwable t) {
                                Toast.makeText(MovieDetailsActivity.this, "Failed to delete from Watched list", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });

            }
            else if (!watch.isSelected()){
                //saving Watched Movie
                watch.setSelected(true);
                watchLabel.setText("Watched");
                Watched w4= new Watched(user.getUserId(), movie.getId());
                movieApi.save(w4)
                        .enqueue(new Callback<Watched>() {
                            @Override
                            public void onResponse(Call<Watched> call, Response<Watched> response) {
                                watch.setBackgroundResource(R.drawable.ic_baseline_watched_24);

                                Toast.makeText(MovieDetailsActivity.this, "Added to Watched list", Toast.LENGTH_SHORT)
                                        .show();
                            }
                            @Override
                            public void onFailure(Call<Watched> call, Throwable t) {
                                Toast.makeText(MovieDetailsActivity.this, "Failed to add to Watched list", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
                //deleting from Favourites
                if(watchlater.isSelected()){
                    watchlater.setSelected(false);

                    movieApi.deleteWatchLater(user.getUserId(),movie.getId())
                            .enqueue(new Callback<WatchLater>() {
                                @Override
                                public void onResponse(Call<WatchLater> call, Response<WatchLater> response) {
                                    watchlater.setBackgroundResource(R.drawable.ic_outline_watch_later_24);

                                    Toast.makeText(MovieDetailsActivity.this, "Removed from Watch Later", Toast.LENGTH_SHORT)
                                            .show();

                                }

                                @Override
                                public void onFailure(Call<WatchLater> call, Throwable t) {
                                    Toast.makeText(MovieDetailsActivity.this, "Failed to remove from Watch Later", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            });

                }


            }

        });

        watchlater.setOnClickListener(view -> {


            if(watchlater.isSelected()){
                watchlater.setSelected(false);

                movieApi.deleteWatchLater(user.getUserId(),movie.getId())
                        .enqueue(new Callback<WatchLater>() {
                            @Override
                            public void onResponse(Call<WatchLater> call, Response<WatchLater> response) {
                                watchlater.setBackgroundResource(R.drawable.ic_outline_watch_later_24);

                                Toast.makeText(MovieDetailsActivity.this, "Removed from Watch Later", Toast.LENGTH_SHORT)
                                        .show();

                            }

                            @Override
                            public void onFailure(Call<WatchLater> call, Throwable t) {
                                Toast.makeText(MovieDetailsActivity.this, "Failed to remove from Watch Later", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });

            }
            else{
                watchlater.setSelected(true);
                movieApi.saveWatchLater(wl1)
                        .enqueue(new Callback<WatchLater>() {
                            @Override
                            public void onResponse(Call<WatchLater> call, Response<WatchLater> response) {
                                watchlater.setBackgroundResource(R.drawable.ic_baseline_watch_later_24);

                                Toast.makeText(MovieDetailsActivity.this, "Added to Watch Later", Toast.LENGTH_SHORT)
                                        .show();
                            }
                            @Override
                            public void onFailure(Call<WatchLater> call, Throwable t) {
                                Toast.makeText(MovieDetailsActivity.this, "Failed to add to Watch Later", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });}

        });

        Rating isRated=new Rating();
        movieApi.findRatingByUserIdAndMovieId(user.getUserId(),movie.getId())
                .enqueue(new Callback<Rating>() {
                    @Override
                    public void onResponse(Call<Rating> call, Response<Rating> response) {
                        isRated.setMovieId(response.body().getMovieId());
                        isRated.setUserId((response.body().getUserId()));
                        isRated.setRate(response.body().getRate());
                        ratingBar.setRating(response.body().getRate());
                        Toast.makeText(MovieDetailsActivity.this, "Rating loaded", Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onFailure(Call<Rating> call, Throwable t) {
                    }
                });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Rating r1 = new Rating();
                if(v!=0) {
                    r1.setUserId(user.getUserId());
                    r1.setMovieId(movie.getId());
                    r1.setRate(v);
                }

                if(isRated.getRate()==0){
                    if (!watch.isSelected()) {

                        watch.setBackgroundResource(R.drawable.ic_baseline_watched_24);
                        watch.setSelected(true);
                        watchLabel.setText("Watched");
                        movieApi.save(w1)
                                .enqueue(new Callback<Watched>() {
                                    @Override
                                    public void onResponse(Call<Watched> call, Response<Watched> response) {
                                        watch.setBackgroundResource(R.drawable.ic_baseline_watched_24);

                                        Toast.makeText(MovieDetailsActivity.this, "Added to Watched list", Toast.LENGTH_SHORT)
                                                .show();
                                    }

                                    @Override
                                    public void onFailure(Call<Watched> call, Throwable t) {
                                        Toast.makeText(MovieDetailsActivity.this, "Failed to add to Watched list", Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                });
                    }
                    if(watchlater.isSelected()){
                        watchlater.setSelected(false);

                        movieApi.deleteWatchLater(user.getUserId(),movie.getId())
                                .enqueue(new Callback<WatchLater>() {
                                    @Override
                                    public void onResponse(Call<WatchLater> call, Response<WatchLater> response) {
                                        watchlater.setBackgroundResource(R.drawable.ic_outline_watch_later_24);

                                        Toast.makeText(MovieDetailsActivity.this, "Removed from Watch Later", Toast.LENGTH_SHORT)
                                                .show();

                                    }

                                    @Override
                                    public void onFailure(Call<WatchLater> call, Throwable t) {
                                        Toast.makeText(MovieDetailsActivity.this, "Failed to remove from Watch Later", Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                });

                    }
                }

                if(isRated.getRate()!=0 ){
                    if (r1.getRate()==0){
                        movieApi.deleteRating(isRated.getUserId(), isRated.getMovieId()).enqueue(new Callback<Rating>() {
                            @Override
                            public void onResponse(Call<Rating> call, Response<Rating> response) {

                            }

                            @Override
                            public void onFailure(Call<Rating> call, Throwable t) {

                            }
                        });
                    }
                    movieApi.deleteRating(r1.getUserId(),r1.getMovieId()).enqueue(new Callback<Rating>() {
                        @Override
                        public void onResponse(Call<Rating> call, Response<Rating> response) {

                        }

                        @Override
                        public void onFailure(Call<Rating> call, Throwable t) {

                        }
                    });}

                if (r1.getRate() != 0){
                    movieApi.save(r1)
                            .enqueue(new Callback<Rating>() {
                                @Override
                                public void onResponse(Call<Rating> call, Response<Rating> response) {


                                    Toast.makeText(MovieDetailsActivity.this, "Rating saved", Toast.LENGTH_SHORT)
                                            .show();
                                }
                                @Override
                                public void onFailure(Call<Rating> call, Throwable t) {
                                    Toast.makeText(MovieDetailsActivity.this, "Failed to save rating", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            });}
            }
        });

        movieApi.findRatingByMovieId(movie.getId())
                .enqueue(new Callback<List<Rating>>() {
                    @Override
                    public void onResponse(Call<List<Rating>> call, Response<List<Rating>> response) {
                        ratingByMId=response.body();

                        for(Rating r:ratingByMId){
                            totalRating+=r.getRate();
                        }

                        releaseDate.setText(movie.getReleaseDate().substring(0,4));

                        if (movie.getRuntime()!=null){
                            updateMRuntime(movie);
                        }
                        DecimalFormat df = new DecimalFormat();
                        df.setMaximumFractionDigits(2);
                        if(ratingByMId.size()!=0){
                            double tmdbRate=(movie.getVoteAverage()*movie.getVoteCount()/2)+totalRating;
                            int totalCount=movie.getVoteCount()+ratingByMId.size();


                            double avg=(tmdbRate/totalCount);
                            ratingDigit.setText("Average Rating: "+String.valueOf(df.format(avg))+"/5");}
                        else{
                            ratingDigit.setText("Average Rating: "+String.valueOf(df.format(movie.getVoteAverage()/2))+"/5");
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Rating>> call, Throwable t) {

                    }
                });

        castBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailsActivity.this,Actor_List.class);
                intent.putExtra("MovieIdCast",movie.getId());
                startActivity(intent);

            }
        });
        crewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailsActivity.this,Crew_List.class);
                intent.putExtra("MovieIdCrew",movie.getId());
                startActivity(intent);

            }
        });

        whereToWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailsActivity.this, WatchProviderActivity.class);
                intent.putExtra("MovieIdWatchProvider",movie.getId());
                startActivity(intent);

            }
        });
        wantToWatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MovieDetailsActivity.this,WantToWatch.class);
                intent.putExtra("MovieId",movie.getId());
                intent.putExtra("UserId",user.getUserId());
                startActivity(intent);
            }
        });

        RetrofitServiceFav rSvc = new RetrofitServiceFav();
        MovieApi movieApi2 = rSvc.getRetrofit().create(MovieApi.class);
        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieApi2.getMovieTrailer(movie.getId(), TmdbApiCred.API_KEY)
                        .enqueue(new Callback<Trailer>() {
                            @Override
                            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                                Trailer trailer = response.body();
                                rlist= trailer.getResults();
                                Result result = new Result();
                                int count = 0;
                                for (Result r:rlist){
                                    if (r.getType().equals("Trailer")){
                                        result = rlist.get(count);

                                    }
                                    count++;

                                }
                                //Result result = rlist.get(0);
                                if(result == null){

                                    setContentView(R.layout.showtime_unavailable);

                                }
                                else{
                                    String trailerUrl = TmdbApiCred.YOUTUBE_BASE_URL+result.getKey();
                                    Log.v("cine", trailerUrl);
                                    Intent intent = new Intent(MovieDetailsActivity.this, CinemaShowtimeActivity.class);
                                    intent.putExtra("trailerUrl",trailerUrl);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<Trailer> call, Throwable t) {

                                Log.v("cine", "Fail");

                            }
                        });

            }
        });

        RetrofitServiceString retrofitSvcStr = new RetrofitServiceString();
        MovieApi movieApiStr = retrofitSvcStr.getRetrofit().create(MovieApi.class);
        cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieApiStr.getShowtimes(movie.getTitle())
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String url = response.body();
                                if(url.equals("Movie not found")){
                                    setContentView(R.layout.showtime_unavailable);

                                }
                                else{

                                    Log.v("cine", url);
                                    Intent intent = new Intent(MovieDetailsActivity.this, CinemaShowtimeActivity.class);
                                    intent.putExtra("popcornUrl",url);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                                Log.v("cine", "Fail");

                            }
                        });

            }
        });

        updateViews();

    }

    private void updateMRuntime(Movie movie){
        String s = movie.getRuntime()/60+"h "+movie.getRuntime()%60+"min";
        runtime.setText(s);

    }

    private void updateViews() {

        titleDetails.setText(movie.getTitle());
        descDetails.setText(movie.getOverview());

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"
                        +movie.getBackdropPath())
                .override(1240,180)
                .into(movieBackdrop);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"
                        +movie.getPosterPath())
                .override(225,450)
                .into(moviePoster);

    }
}