package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import iss.workshop.adproject_team5_movieapp.R;


import java.util.List;

import iss.workshop.adproject_team5_movieapp.Model.User;
import iss.workshop.adproject_team5_movieapp.R;
import iss.workshop.adproject_team5_movieapp.WantToWatch;

public class WantToWatchAdapter extends RecyclerView.Adapter<WantToWatchHolder>  {
    private List<User> userList;

    public WantToWatchAdapter(List<User> userList)
    {
        this.userList = userList;
    }
    @NonNull
    @Override
    public WantToWatchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_wanttowatch_list, parent, false);
        return new WantToWatchHolder(view);

    }
    public void filterList(List<User> filterList)
    {
        userList = filterList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull WantToWatchHolder holder, int position) {
        User user = userList.get(position);
        holder.name.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


}
