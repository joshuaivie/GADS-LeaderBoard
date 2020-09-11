package com.example.gadsleaderboard.ui.apiUtils;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitInterface_Submit {
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Void> postProjectData(
            @Field("entry.1824927963") String email,
            @Field("entry.1877115667") String fistName,
            @Field("entry.2006916086") String lastName,
            @Field("entry.284483984") String link
    );
}