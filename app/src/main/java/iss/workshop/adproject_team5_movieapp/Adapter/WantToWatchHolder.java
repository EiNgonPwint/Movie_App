package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import iss.workshop.adproject_team5_movieapp.R;

public class WantToWatchHolder extends RecyclerView.ViewHolder  {
    TextView name;
    public WantToWatchHolder(@NotNull View itemView)
    {
        super(itemView);
        name = itemView.findViewById(R.id.wantToWatchName);

    }
}
