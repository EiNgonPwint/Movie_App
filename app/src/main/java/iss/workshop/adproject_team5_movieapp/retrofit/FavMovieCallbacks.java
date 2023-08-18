package iss.workshop.adproject_team5_movieapp.retrofit;

import androidx.annotation.NonNull;

import java.util.List;

import iss.workshop.adproject_team5_movieapp.Model.Movie;

public interface    FavMovieCallbacks {

    public void onSuccess(@NonNull List<Movie> value);

    public void onError(@NonNull Throwable throwable);
}
