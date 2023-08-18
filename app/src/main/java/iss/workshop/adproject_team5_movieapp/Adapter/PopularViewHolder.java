package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iss.workshop.adproject_team5_movieapp.R;

public class PopularViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView imageView_pop;
    //RatingBar ratingBar_pop;
    OnMovieClickListener listener;

    public PopularViewHolder(@NonNull View itemView, OnMovieClickListener listener) {
        super(itemView);

        this.listener = listener;
        imageView_pop = itemView.findViewById(R.id.movie_img_popular);
        //ratingBar_pop = itemView.findViewById(R.id.rating_bar_pop);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
