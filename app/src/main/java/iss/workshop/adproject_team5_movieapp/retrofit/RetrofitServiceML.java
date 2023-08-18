package iss.workshop.adproject_team5_movieapp.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceML {
    private Retrofit retrofit;

    public RetrofitServiceML(){
        initializeRetrofit();
    }
    private void initializeRetrofit(){
        retrofit= new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }
    public Retrofit getRetrofit(){
        return retrofit;
    }
}
