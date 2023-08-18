package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import iss.workshop.adproject_team5_movieapp.R;

public class WatchProviderHolder extends RecyclerView.ViewHolder  {
    ImageView wpImage;
    TextView wpName, wpCountry, wpType;

    public WatchProviderHolder(@NotNull View itemView)
    {
        super(itemView);
        wpName = itemView.findViewById(R.id.wpName);
        wpImage = itemView.findViewById(R.id.wpImage);
        wpCountry = itemView.findViewById(R.id.wpCountry);
        wpType = itemView.findViewById(R.id.wpType);

    }
}
