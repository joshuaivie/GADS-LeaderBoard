package com.example.gadsleaderboard.ui.apiUtils;

import com.example.gadsleaderboard.ui.data.SkillsDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface_Skill {
    @GET("api/skilliq")
    Call<List<SkillsDataModel>> getSkillData();

}
