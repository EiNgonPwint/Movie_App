package iss.workshop.adproject_team5_movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import iss.workshop.adproject_team5_movieapp.Model.Actor;
import iss.workshop.adproject_team5_movieapp.retrofit.RetrofitServiceFav;
import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import iss.workshop.adproject_team5_movieapp.utils.TmdbApiCred;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {

    ImageView profileImg;
    TextView bio;
    int id;
    Actor actor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileImg=findViewById(R.id.profileImg);
        actor= new Actor();
        bio=findViewById(R.id.bio);
        Intent intent=getIntent();
        id=intent.getIntExtra("actorId",0);
        int j=0;
        RetrofitServiceFav retrofitService1 = new RetrofitServiceFav();
        MovieApi movieApi1 = retrofitService1.getRetrofit().create(MovieApi.class);
        movieApi1.getActor(id, TmdbApiCred.API_KEY)
                .enqueue(new Callback<Actor>() {
                    @Override
                    public void onResponse(Call<Actor> call, Response<Actor> response) {
                        actor=response.body();
                        if(actor.getProfilePath()!=null){
                            Glide.with(Profile.this)
                                    .load("https://image.tmdb.org/t/p/w500/"
                                            +actor.getProfilePath())
                                    .into(profileImg);}
                        else{
                            profileImg.setImageResource(R.drawable.profile);
                        }
                        if(actor.getBiography().length()>1){
                        bio.setText(actor.getBiography());}
                        else{
                            bio.setText("No Bio Available!");
                        }
                    }

                    @Override
                    public void onFailure(Call<Actor> call, Throwable t) {

                    }
                });
    }
}