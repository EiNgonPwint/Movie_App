package iss.workshop.adproject_team5_movieapp.utils;

import java.util.List;

import iss.workshop.adproject_team5_movieapp.Model.Actor;
import iss.workshop.adproject_team5_movieapp.Model.Credits;
import iss.workshop.adproject_team5_movieapp.Model.Favourite;
import iss.workshop.adproject_team5_movieapp.Model.MachineLearningTest;
import iss.workshop.adproject_team5_movieapp.Model.Movie;
import iss.workshop.adproject_team5_movieapp.Model.MovieRec;
import iss.workshop.adproject_team5_movieapp.Model.Rating;
import iss.workshop.adproject_team5_movieapp.Model.Review;
import iss.workshop.adproject_team5_movieapp.Model.Social;
import iss.workshop.adproject_team5_movieapp.Model.Trailer;
import iss.workshop.adproject_team5_movieapp.Model.User;
import iss.workshop.adproject_team5_movieapp.Model.WatchLater;
import iss.workshop.adproject_team5_movieapp.Model.WatchProviders;
import iss.workshop.adproject_team5_movieapp.Model.Watched;
import iss.workshop.adproject_team5_movieapp.Response.MovieSearchResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher&page=1

    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );


    @GET("/3/movie/{movie_id}/videos")
    Call<Trailer> getMovieTrailer(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );

    @GET("/3/person/{person_id}")
    Call<Actor> getActor(
            @Path("person_id") int person_id,
            @Query("api_key") String api_key
    );
    @GET("/3/movie/{movie_id}")
    Call<Movie> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );

    @GET("/3/movie/{movie_id}/credits")
    Call<Credits> getMovieCredits(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );

    @GET("/3/movie/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key") String key,
            @Query("page") int page
    );

    @GET("/movie/get-all")
    Call<List<Movie>> getAllMovies();

    @POST("/movie/favourite")
    Call<Favourite> save(@Body Favourite favourite);

    @DELETE("movie/favourite/{user_id}/{movie_id}")
    Call<Favourite> deleteFavourite(@Path("user_id") int user_id, @Path("movie_id") int movie_id);

    @GET("/movie/isfavourite/{user_id}/{movie_id}")
    Call<Boolean> isFavourite(@Path("user_id") int user_id, @Path("movie_id") int movie_id);

    @POST("/movie/watched")
    Call<Watched> save(@Body Watched watched);

    @DELETE("movie/watched/{user_id}/{movie_id}")
    Call<Watched> deleteWatched(@Path("user_id") int user_id, @Path("movie_id") int movie_id);

    @GET("movie/favourite/{user_id}/{movie_id}")
    Call<Favourite> findFavouriteByUserIdAndMovieId(@Path("user_id") int user_id, @Path("movie_id") int movie_id);

    @GET("movie/watched/{user_id}/{movie_id}")
    Call<Watched> findWatchedByUserIdAndMovieId(@Path("user_id") int user_id, @Path("movie_id") int movie_id);

    @GET("movie/favouritelist/{user_id}")
    Call<List<Favourite>> findByUserId(@Path("user_id") int user_id);

    @GET("movie/watchlater/{user_id}")
    Call<List<WatchLater>> findWLByUserId(@Path("user_id") int user_id);

    @GET("movie/watched/{user_id}")
    Call<List<Watched>> findWatchedByUserId(@Path("user_id") int user_id);

    @POST("movie/review")
    Call<Review> saveReview(@Body Review review1);


    //login(Because i added @RequestMapping ("api") in server
    @GET("/api/login/{name}/{password}")
    Call<User> login(@Path("name") String name, @Path("password") String password);

    //register
    @POST("/api/save")
    Call<User> save(@Body User user);

    @POST("/social/save")
    Call<Social> save(@Body Social social);

    @GET("/api/user/id/{id}")
    Call<User> findUserById(@Path("id") int id);
    //reset password
    @POST("/api/forget_password/{email}")
    Call<User> reset (@Path("email") String email);


    //find user by name
    @GET("/api/user/{name}")
    Call<List<User>> getAllUserByName(@Path("name") String name);

    @GET("social/sociallist/{user_id}")
    Call<List<Social>> getSocialsById(@Path("user_id") int user_id);

    @POST("/movie/watchlater")
    Call<WatchLater> saveWatchLater(@Body WatchLater watchLater);

    @POST("/movie/rating")
    Call<Rating> save(@Body Rating rating);

    @DELETE("movie/watchlater/{user_id}/{movie_id}")
    Call<WatchLater> deleteWatchLater(@Path("user_id") int user_id, @Path("movie_id") int movie_id);

    @DELETE("social/delete/{user_id}/{following_id}")
    Call<Social> deleteSocial(@Path("user_id") int user_id,@Path("following_id") int following_id);

    @GET("social/find/{user_id}/{following_id}")
    Call<Social> findSocialByUserIdAndFollowingId(@Path("user_id") int user_id,@Path("following_id") int following_id);

    @GET("movie/watchlater/{user_id}/{movie_id}")
    Call<WatchLater> findWatchLaterByUserIdAndMovieId(@Path("user_id") int user_id, @Path("movie_id") int movie_id);

    @DELETE("movie/rating/{user_id}/{movie_id}")
    Call<Rating> deleteRating(@Path("user_id") int user_id, @Path("movie_id") int movie_id);

    @GET("movie/rating/{user_id}/{movie_id}")
    Call<Rating> findRatingByUserIdAndMovieId(@Path("user_id") int user_id, @Path("movie_id") int movie_id);

    @GET("movie/ratinglist/{movie_id}")
    Call <List<Rating>> findRatingByMovieId(@Path("movie_id") int movie_id);

    @GET("/movie/review/{movie_id}")
    Call<List<Review>> getReviewByMovieId(@Path("movie_id")int movie_id);
    @GET("/3/movie/{movie_id}/watch/providers")
    Call<WatchProviders> getWatchProviders(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );

    @GET("/provider/popcornscraper/{movieTitle}")
    Call<String> getShowtimes(
            @Path("movieTitle") String movieTitle
    );

    @POST("/recommend")
    Call<MovieRec> getMovieRec(
            @Body MachineLearningTest machineLearningTest
    );
    
}
