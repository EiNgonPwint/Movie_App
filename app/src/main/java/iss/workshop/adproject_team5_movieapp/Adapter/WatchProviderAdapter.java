package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import iss.workshop.adproject_team5_movieapp.Model.Flatrate__2;
import iss.workshop.adproject_team5_movieapp.R;

public class WatchProviderAdapter extends RecyclerView.Adapter<WatchProviderHolder>  {
    private List<Flatrate__2> wpList;

    public WatchProviderAdapter(List<Flatrate__2> wpList)
    {
        this.wpList = wpList;
    }
    @NonNull
    @Override
    public WatchProviderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_watch_provider_list, parent, false);
        return new WatchProviderHolder(view);

    }
    public void filterList(List<Flatrate__2> filterList)
    {
        wpList = filterList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull WatchProviderHolder holder, int position) {
        Flatrate__2 wp = wpList.get(position);
        holder.wpName.setText(wp.getProviderName());
        if(wp.getLogoPath()!=null){
            Glide.with(holder.wpName.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"
                            +wp.getLogoPath())
                    .into(holder.wpImage);}
        else{
            holder.wpImage.setImageResource(R.drawable.profile);
        }
    }

    @Override
    public int getItemCount() {
        return wpList.size();
    }


}
