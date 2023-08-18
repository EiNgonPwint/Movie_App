package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iss.workshop.adproject_team5_movieapp.R;


public class ReviewHolder extends RecyclerView.ViewHolder {
    TextView name,content;
    public ReviewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.userReview_name);
        content = itemView.findViewById(R.id.reviewListItem);
    }
}
