package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import iss.workshop.adproject_team5_movieapp.Model.Crew;
import iss.workshop.adproject_team5_movieapp.R;

public class CrewAdapter extends RecyclerView.Adapter<CrewHolder>  {
    private List<Crew> crewList;

    public CrewAdapter(List<Crew> crewList)
    {
        this.crewList = crewList;
    }
    @NonNull
    @Override
    public CrewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_crew_list, parent, false);
        return new CrewHolder(view);

    }
    public void filterList(List<Crew> filterList)
    {
        crewList = filterList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CrewHolder holder, int position) {
        Crew crew = crewList.get(position);
        holder.name.setText(crew.getName());
        holder.job.setText(crew.getJob());
        if(crew.getProfilePath()!=null){
            Glide.with(holder.name.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"
                            +crew.getProfilePath())
                    .into(holder.crewImage);}
        else{
            holder.crewImage.setImageResource(R.drawable.profile);
        }
    }

    @Override
    public int getItemCount() {
        return crewList.size();
    }


}
