package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import iss.workshop.adproject_team5_movieapp.R;

public class CrewHolder extends RecyclerView.ViewHolder  {
    ImageView crewImage;
    TextView name;
    TextView job;
    public CrewHolder(@NotNull View itemView)
    {
        super(itemView);
        name = itemView.findViewById(R.id.crewName);
        crewImage = itemView.findViewById(R.id.crewImage);
        job=itemView.findViewById(R.id.crewJob);
    }
}
