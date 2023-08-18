package iss.workshop.adproject_team5_movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.adproject_team5_movieapp.Adapter.WatchProviderAdapter;
import iss.workshop.adproject_team5_movieapp.Model.Flatrate__2;
import iss.workshop.adproject_team5_movieapp.Model.Results;
import iss.workshop.adproject_team5_movieapp.Model.Sg;
import iss.workshop.adproject_team5_movieapp.Model.WatchProviders;
import iss.workshop.adproject_team5_movieapp.retrofit.RetrofitServiceFav;
import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import iss.workshop.adproject_team5_movieapp.utils.TmdbApiCred;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WatchProviderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    WatchProviderAdapter watchProviderAdapter;

    private List<Flatrate__2> frList = new ArrayList<>();
    Results r = new Results();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_provider_list);

        recyclerView = findViewById(R.id.wpList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent= getIntent();
        int mId;
        mId=intent.getIntExtra("MovieIdWatchProvider",0);
        loadWatchProvider(mId);

    }
    private void loadWatchProvider(int movieId){

        RetrofitServiceFav retrofitService = new RetrofitServiceFav();
        MovieApi movieApi = retrofitService.getRetrofit().create(MovieApi.class);
        movieApi.getWatchProviders(movieId, TmdbApiCred.API_KEY)
                .enqueue(new Callback<WatchProviders>() {
                    @Override
                    public void onResponse(Call<WatchProviders> call, Response<WatchProviders> response) {

                        r = response.body().getResults();
                        if(r.getSg()!=null) {
                            Sg sgProviders = r.getSg();
                            frList = sgProviders.getFlatrate();
                            populateListView(frList);
                            String s = frList.get(0).getProviderName();
                            Log.v("wp", s);
                        }
                        else{
                            setContentView(R.layout.watch_provider_unavailable);
                        }

                    }

                    @Override
                    public void onFailure(Call<WatchProviders> call, Throwable t) {

                    }
                });

    }
    private void populateListView(List<Flatrate__2> wpList) {
        watchProviderAdapter = new WatchProviderAdapter(wpList);
        recyclerView.setAdapter(watchProviderAdapter);
    }

}
