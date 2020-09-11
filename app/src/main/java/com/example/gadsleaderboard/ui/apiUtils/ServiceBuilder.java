package com.example.gadsleaderboard.ui.apiUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    // Base URLs
    private static final String baseURL = "https://gadsapi.herokuapp.com/";
    private static final String submitBaseURL = "https://docs.google.com/forms/d/e/";

    // Get Heroku JSON Data
    private static Retrofit.Builder fetchBuilder = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit getRetrofit = fetchBuilder.build();

    // Submit Google Form
    private static Retrofit.Builder pushBuilder = new Retrofit.Builder()
            .baseUrl(submitBaseURL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit postRetrofit = pushBuilder.build();


    public static <S> S buildGetService(Class<S> serviceType) {
        return getRetrofit.create(serviceType);
    }

    public static <S> S buildPostService(Class<S> serviceType) {
        return getRetrofit.create(serviceType);
    }


}
