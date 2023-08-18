package iss.workshop.adproject_team5_movieapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

import iss.workshop.adproject_team5_movieapp.Model.Movie;
import iss.workshop.adproject_team5_movieapp.R;
import iss.workshop.adproject_team5_movieapp.utils.TmdbApiCred;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> movieList;
    private OnMovieClickListener onMovieClickListener;


    private static final int DISPLAY_POPULAR = 1;
    private static final int DISPLAY_SEARCH = 2;

    public MovieRecyclerView(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        if (viewType == DISPLAY_SEARCH) {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,
                    parent, false);
            return new MovieViewHolder(view, onMovieClickListener);
        }

        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_movie_item,
                    parent, false);
            return new PopularViewHolder(view, onMovieClickListener);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        int itemViewType = getItemViewType(i);
        if (itemViewType == DISPLAY_SEARCH){

            // tmdb api's vote average is out of 10, rating bar has 5 stars
            //((MovieViewHolder)holder).ratingBar.setRating((movieList.get(i).getVote_average())/2);

            // Updating movie poster using Glide
            Glide.with(holder.itemView.getContext())
                    .load( "https://image.tmdb.org/t/p/w500/"
                            +movieList.get(i).getPosterPath())
                    .into(((MovieViewHolder)holder).imageView);

        }else{
            //((PopularViewHolder)holder).ratingBar_pop.setRating(movieList.get(i).getVote_average());

            Glide.with(holder.itemView.getContext())
                    .load( "https://image.tmdb.org/t/p/w500/"
                            +movieList.get(i).getPosterPath())
                    .into(((PopularViewHolder)holder).imageView_pop);

        }

    }

    @Override
    public int getItemCount() {
        if (movieList != null){
            return movieList.size();
        }
        return 0;
    }


    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }


    // Getting the id of the movie clicked
    public Movie getSelectedMovie(int position){
        if (movieList != null){
            if (movieList.size() > 0){
                return movieList.get(position);
            }
        }
        return null;
    }


    @Override
    public int getItemViewType(int position) {

        if (TmdbApiCred.displayPopularMovies){
            return DISPLAY_POPULAR;
        }
        else
            return DISPLAY_SEARCH;
    }

}