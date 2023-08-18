package iss.workshop.adproject_team5_movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.adproject_team5_movieapp.Adapter.CastAdapter;
import iss.workshop.adproject_team5_movieapp.Adapter.OnProfileClickListener;
import iss.workshop.adproject_team5_movieapp.Model.Cast;
import iss.workshop.adproject_team5_movieapp.Model.Credits;
import iss.workshop.adproject_team5_movieapp.retrofit.RetrofitServiceFav;
import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import iss.workshop.adproject_team5_movieapp.utils.TmdbApiCred;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Actor_List extends AppCompatActivity implements OnProfileClickListener{
    private RecyclerView recyclerView;

    private List<Cast> casts;
    CastAdapter castAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_list);

        recyclerView = findViewById(R.id.actorList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        CastAdapter castAdapter= new CastAdapter();
        casts= new ArrayList<>();
        Intent intent= getIntent();
        int mId;
        mId=intent.getIntExtra("MovieIdCast",0);
        loadCasts(mId);
    }
    private void loadCasts(int movieId){
        RetrofitServiceFav retrofitService = new RetrofitServiceFav();
        MovieApi movieApi= retrofitService.getRetrofit().create(MovieApi.class);
        movieApi.getMovieCredits(movieId, TmdbApiCred.API_KEY)
                .enqueue(new Callback<Credits>() {
                    @Override
                    public void onResponse(Call<Credits> call, Response<Credits> response) {
                        casts=response.body().getCast();
                        populateListView(casts);

                    }

                    @Override
                    public void onFailure(Call<Credits> call, Throwable t) {

                    }
                });

    }
    private void populateListView(List<Cast> castList) {
        castAdapter = new CastAdapter(castList);
        recyclerView.setAdapter(castAdapter);
    }

    @Override
    public void onProfileClick(View v, int position) {

    }
}