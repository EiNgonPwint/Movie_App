package iss.workshop.adproject_team5_movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import iss.workshop.adproject_team5_movieapp.Adapter.ReviewAdapter;
import iss.workshop.adproject_team5_movieapp.Model.Review;
import iss.workshop.adproject_team5_movieapp.retrofit.RetrofitService;
import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    RetrofitService retrofitService = new RetrofitService();
    MovieApi movieApi = retrofitService.getRetrofit().create(MovieApi.class);
    int movie_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);

        recyclerView = findViewById(R.id.reviewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.println(movie_id);
        Intent intent = getIntent();
        movie_id = intent.getIntExtra("MovieId",0);

        //movie_id =2;

        loadReview(movie_id);

    }
    private void loadReview(int movie_id)
    {

        movieApi.getReviewByMovieId(movie_id)
                .enqueue(new Callback<List<Review>>() {
                    @Override
                    public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                        //list users from Users
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Review>> call, Throwable t) {
                        Toast.makeText(ReviewDetailActivity.this, "Failed to load employees", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void populateListView(List<Review> reviewList) {
        ReviewAdapter reviewAdapter = new ReviewAdapter(reviewList);
        recyclerView.setAdapter(reviewAdapter);
    }
    @Override
    protected void onResume() {

        super.onResume();
        loadReview(movie_id);
    }


}