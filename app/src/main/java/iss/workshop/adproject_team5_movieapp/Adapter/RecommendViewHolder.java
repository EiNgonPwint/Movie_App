package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iss.workshop.adproject_team5_movieapp.R;

public class RecommendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView imageView_rec;
    //RatingBar ratingBar_pop;
    OnMovieClickListener listener;

    public RecommendViewHolder(@NonNull View itemView, OnMovieClickListener listener) {
        super(itemView);

        this.listener = listener;
        imageView_rec = itemView.findViewById(R.id.movie_img_recommend);
        //ratingBar_pop = itemView.findViewById(R.id.rating_bar_pop);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
