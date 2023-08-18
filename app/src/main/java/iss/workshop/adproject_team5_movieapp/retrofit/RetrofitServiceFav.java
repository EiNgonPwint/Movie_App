package iss.workshop.adproject_team5_movieapp.retrofit;

import com.google.gson.Gson;

import iss.workshop.adproject_team5_movieapp.utils.TmdbApiCred;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceFav {
    private Retrofit retrofit;

    public RetrofitServiceFav(){
        initializeRetrofit();
    }
    private void initializeRetrofit(){
        retrofit= new Retrofit.Builder()
                .baseUrl(TmdbApiCred.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }
    public Retrofit getRetrofit(){
        return retrofit;
    }
}