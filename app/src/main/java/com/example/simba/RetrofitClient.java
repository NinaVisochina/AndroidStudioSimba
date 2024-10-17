package com.example.simba;

import com.example.simba.network.CategoriesApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://Simba.somee.com/";
    // Ваш базовий URL API

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static CategoriesApi getApiService() {
        return getRetrofitInstance().create(CategoriesApi.class);
    }
}

