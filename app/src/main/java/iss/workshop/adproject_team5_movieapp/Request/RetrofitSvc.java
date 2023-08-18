package iss.workshop.adproject_team5_movieapp.Request;

import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import iss.workshop.adproject_team5_movieapp.utils.TmdbApiCred;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSvc {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(TmdbApiCred.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    public static MovieApi getMovieApi(){
        return movieApi;
    }
}
