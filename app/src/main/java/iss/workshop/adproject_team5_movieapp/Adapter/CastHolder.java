package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import iss.workshop.adproject_team5_movieapp.R;

public class CastHolder extends RecyclerView.ViewHolder  {
    ImageView castImage;
    TextView name;
    TextView character;
    Button profileBtn;
    public CastHolder(@NotNull View itemView)
    {
        super(itemView);
        name = itemView.findViewById(R.id.castName);
        castImage = itemView.findViewById(R.id.castImage);
        character = itemView.findViewById(R.id.castCharacter);
        profileBtn=itemView.findViewById(R.id.profileBtn);

    }
}
