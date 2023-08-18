package iss.workshop.adproject_team5_movieapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import iss.workshop.adproject_team5_movieapp.Actor_List;
import iss.workshop.adproject_team5_movieapp.MainActivity;
import iss.workshop.adproject_team5_movieapp.Model.Cast;
import iss.workshop.adproject_team5_movieapp.Profile;
import iss.workshop.adproject_team5_movieapp.R;

public class CastAdapter extends RecyclerView.Adapter<CastHolder> {
    private List<Cast> castList;
    private Context context;
    private OnProfileClickListener onProfileClickListener;
    public CastAdapter(Context context,OnProfileClickListener onProfileClickListener)
    {

        this.context=context;
        this.onProfileClickListener = onProfileClickListener;

    }
    public CastAdapter(List<Cast> castList)
    {
        this.castList = castList;
    }
    @NonNull
    @Override
    public CastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_actor_list, parent, false);
        return new CastHolder(view);

    }
    public void filterList(List<Cast> filterList)
    {
        castList = filterList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CastHolder holder, int position) {
        Cast cast = castList.get(position);
        holder.name.setText(cast.getName());
        holder.character.setText(cast.getCharacter());
        if(cast.getProfilePath()!=null){
            Glide.with(holder.name.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"
                            +cast.getProfilePath())
                    .into(holder.castImage);}
        else{
            holder.castImage.setImageResource(R.drawable.profile);
        }
        holder.profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), Profile.class);
                int actorId=cast.getId();
                intent.putExtra("actorId", actorId);
                view.getContext().startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return castList.size();
    }

}
