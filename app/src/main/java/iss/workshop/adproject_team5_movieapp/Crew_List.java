package iss.workshop.adproject_team5_movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.adproject_team5_movieapp.Adapter.CrewAdapter;
import iss.workshop.adproject_team5_movieapp.Model.Credits;
import iss.workshop.adproject_team5_movieapp.Model.Crew;
import iss.workshop.adproject_team5_movieapp.retrofit.RetrofitServiceFav;
import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import iss.workshop.adproject_team5_movieapp.utils.TmdbApiCred;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Crew_List extends AppCompatActivity {
    private RecyclerView recyclerView;

    private List<Crew> crew;
    CrewAdapter crewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crew_list);

        recyclerView = findViewById(R.id.crewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        CastAdapter castAdapter= new CastAdapter();
        crew= new ArrayList<>();
        Intent intent= getIntent();
        int mId;
        mId=intent.getIntExtra("MovieIdCrew",0);
        loadCrew(mId);
    }
    private void loadCrew(int movieId){
        RetrofitServiceFav retrofitService = new RetrofitServiceFav();
        MovieApi movieApi= retrofitService.getRetrofit().create(MovieApi.class);
        movieApi.getMovieCredits(movieId, TmdbApiCred.API_KEY)
                .enqueue(new Callback<Credits>() {
                    @Override
                    public void onResponse(Call<Credits> call, Response<Credits> response) {
                        crew=response.body().getCrew();
                        populateListView(crew);

                    }

                    @Override
                    public void onFailure(Call<Credits> call, Throwable t) {

                    }
                });

    }
    private void populateListView(List<Crew> crewList) {
        crewAdapter = new CrewAdapter(crewList);
        recyclerView.setAdapter(crewAdapter);
    }

}