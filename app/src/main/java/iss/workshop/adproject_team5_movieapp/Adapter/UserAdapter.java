package iss.workshop.adproject_team5_movieapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import iss.workshop.adproject_team5_movieapp.Model.Movie;
import iss.workshop.adproject_team5_movieapp.Model.Social;
import iss.workshop.adproject_team5_movieapp.Model.User;
import iss.workshop.adproject_team5_movieapp.R;
import iss.workshop.adproject_team5_movieapp.Request.RetrofitService;
import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    // Intent intent = ((Activity) ).getIntent();
    private List<User> userList;
    private OnFollowClickListener onFollowClickListener;
    private int userId;
    private Social social;

    public  UserAdapter(OnFollowClickListener onFollowClickListener){
        this.onFollowClickListener=onFollowClickListener;
    }
    public UserAdapter(List<User> userList, OnFollowClickListener onFollowClickListener, int userId)
    {
        this.userList = userList;
        this.onFollowClickListener=onFollowClickListener;
        this.userId=userId;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_user_list, parent, false);

        return new UserHolder(view,onFollowClickListener);

    }


    public void filterList(List<User> filterList)
    {
        userList = filterList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = userList.get(position);
         social= new Social();
        RetrofitService retrofitService = new RetrofitService();
        MovieApi movieApi = retrofitService.getRetrofit().create(MovieApi.class);
        movieApi.findSocialByUserIdAndFollowingId(userId,user.getUserId())
                .enqueue(new Callback<Social>() {
                    @Override
                    public void onResponse(Call<Social> call, Response<Social> response) {
                        social=response.body();
                        if(social!=null){
                            ((UserHolder)holder).followbtn.setSelected(true);
                            ((UserHolder)holder).followbtn.setText("Unfollow");

                        }
                    }

                    @Override
                    public void onFailure(Call<Social> call, Throwable t) {

                    }
                });

//        User user1 = (User) intent.getSerializableExtra("user");
        if(user.getUserId()==userId){

            ((UserHolder)holder).followbtn.setVisibility(View.INVISIBLE);

        }
        if(user.getUserId()==1){
            ((UserHolder)holder).followbtn.setVisibility(View.INVISIBLE);
        }

        ((UserHolder)holder).name.setText(user.getName());
        ((UserHolder)holder).email.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public User getSelectedUser(int position){
        if (userList != null){
            if (userList.size() > 0){
                return userList.get(position);
            }
        }
        return null;
    }

}
