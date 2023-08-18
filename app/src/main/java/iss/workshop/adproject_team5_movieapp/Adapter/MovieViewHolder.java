package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iss.workshop.adproject_team5_movieapp.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView imageView;

    OnMovieClickListener onMovieClickListener;

    public MovieViewHolder(@NonNull View itemView, OnMovieClickListener onMovieClickListener) {
        super(itemView);

        this.onMovieClickListener = onMovieClickListener;

        imageView = itemView.findViewById(R.id.movie_img);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        onMovieClickListener.onMovieClick(v, getAdapterPosition());
    }
}
