package com.example.gadsleaderboard.ui.apiUtils;

import com.example.gadsleaderboard.ui.data.LearningDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface_Learning {
    @GET("api/hours")
    Call<List<LearningDataModel>> getLearningData();

}
