package iss.workshop.adproject_team5_movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.adproject_team5_movieapp.Adapter.CastAdapter;
import iss.workshop.adproject_team5_movieapp.Adapter.WantToWatchAdapter;
import iss.workshop.adproject_team5_movieapp.Model.Cast;
import iss.workshop.adproject_team5_movieapp.Model.Favourite;
import iss.workshop.adproject_team5_movieapp.Model.Social;
import iss.workshop.adproject_team5_movieapp.Model.User;
import iss.workshop.adproject_team5_movieapp.Model.WatchLater;
import iss.workshop.adproject_team5_movieapp.Request.RetrofitService;
import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WantToWatch extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<User> users;
    private List<Social> socials;
    WantToWatchAdapter wantAdapter;
    List<Integer> idList;
    int movieId,userId;
    List<User> userList;
    WatchLater wl = new WatchLater();
    User u1;
    private  int i=0;
    int j=0;
    int k=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_watch);

        recyclerView = findViewById(R.id.wantToWatchList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        idList= new ArrayList<>();
        socials=new ArrayList<>();
        userList=new ArrayList<>();
        u1=new User();
        Intent intent= getIntent();
        movieId=intent.getIntExtra("MovieId",0);
        userId=intent.getIntExtra("UserId",0);

        loadWantToWatch(userId,movieId);


    }
    private void loadWantToWatch(int userId,int movieId){
        RetrofitService retrofitService = new RetrofitService();
        MovieApi movieApi = retrofitService.getRetrofit().create(MovieApi.class);


        movieApi.getSocialsById(userId)
                .enqueue(new Callback<List<Social>>() {
                    @Override
                    public void onResponse(Call<List<Social>> call, Response<List<Social>> response) {
                        socials=response.body();
                        j= socials.size();
                        for(Social s:socials){

                            j--;
                        movieApi.findWatchLaterByUserIdAndMovieId(s.getFollowingId(),movieId)
                                .enqueue(new Callback<WatchLater>() {
                                    @Override
                                    public void onResponse(Call<WatchLater> call, Response<WatchLater> response1) {
                                            wl = response1.body();


                                            idList.add(wl.getUser_id());
                                            if(j==0){
                                            for(int i:idList){

                                            movieApi.findUserById(i)
                                                    .enqueue(new Callback<User>() {

                                                        @Override
                                                        public void onResponse(Call<User> call, Response<User> response2) {

                                                            u1=response2.body();
//                                                            if(!userList.contains(u1))
                                                            userList.add(u1);
//                                                            addToList(u1);

                                                            if(userList.size()==idList.size()){
                                                            populateListView(userList);}



                                                        }




                                                        @Override
                                                        public void onFailure(Call<User> call, Throwable t) {

                                                        }

                                                    });}


                                        }
//
                                        }

                                    @Override
                                    public void onFailure(Call<WatchLater> call, Throwable t) {

                                    }

                                });

                        }






                        }


                        //populateListView();
                    @Override
                    public void onFailure(Call<List<Social>> call, Throwable t) {

                    }
                });

    }




//    private void addToList(User user){
//        userList.add(user);
//        populateListView(userList);
//    }


    private void populateListView(List<User> uList) {
        wantAdapter = new WantToWatchAdapter(uList);
        recyclerView.setAdapter(wantAdapter);

    }
}



















