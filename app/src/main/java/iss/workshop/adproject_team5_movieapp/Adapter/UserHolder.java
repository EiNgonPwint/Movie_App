package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import iss.workshop.adproject_team5_movieapp.R;

public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView name,email;
    Button followbtn, viewbtn;
    OnFollowClickListener onFollowClickListener;

//    public MovieViewHolder(@NonNull View itemView, OnMovieClickListener onMovieClickListener) {
//        super(itemView);
//
//        this.onMovieClickListener = onMovieClickListener;
//
//        imageView = itemView.findViewById(R.id.movie_img);
//
//        itemView.setOnClickListener(this);
//
//    }
    public UserHolder(@NotNull View itemView, OnFollowClickListener onFollowClickListener)
    {
        super(itemView);
        this.onFollowClickListener=onFollowClickListener;
        name = itemView.findViewById(R.id.userListItem_name);
        email=itemView.findViewById(R.id.userListItem_email);
        followbtn=itemView.findViewById(R.id.followBtn);
        followbtn.setOnClickListener(this);
        viewbtn=itemView.findViewById(R.id.viewBtn);
        viewbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
       onFollowClickListener.onFollowClick(v,getAdapterPosition());
    }
}
