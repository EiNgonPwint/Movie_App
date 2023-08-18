package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.adproject_team5_movieapp.Model.Review;
import iss.workshop.adproject_team5_movieapp.R;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewHolder> {
    private List<Review> reviewList = new ArrayList<>();

    public ReviewAdapter(List<Review> reviewList) {
        this.reviewList=reviewList;
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_review,parent,false);
        return new ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.name.setText(review.getUser_name());
        holder.content.setText(review.getContent());

    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }
}
