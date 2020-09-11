package com.example.gadsleaderboard.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.ui.apiUtils.RetrofitInterface_Learning;
import com.example.gadsleaderboard.ui.apiUtils.ServiceBuilder;
import com.example.gadsleaderboard.ui.data.LearningDataModel;
import com.example.gadsleaderboard.ui.utils.LearningAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LearningLeaders extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private RetrofitInterface_Learning learningInterface;
    private ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_learnering_leaders, container, false);
        //Configure RecyclerView
        recyclerView = (RecyclerView) v.findViewById(R.id.learningRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        //Get Progress Bar
        progressBar = (ProgressBar) v.findViewById(R.id.progressLearning);
        //Get Learning Leaders
        getLearningLeaders();
        //Return View
        return v;
    }

    private void getLearningLeaders() {
        progressBar.setVisibility(View.VISIBLE);

        learningInterface = ServiceBuilder.buildGetService(RetrofitInterface_Learning.class);
        Call<List<LearningDataModel>> listCall = learningInterface.getLearningData();

        listCall.enqueue(new Callback<List<LearningDataModel>>() {
            @Override
            public void onResponse(Call<List<LearningDataModel>> call, Response<List<LearningDataModel>> response) {
                progressBar.setVisibility(View.GONE);
                getData(response.body());
            }

            @Override
            public void onFailure(Call<List<LearningDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getData(List<LearningDataModel> body) {
        adapter = new LearningAdapter(body, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }
}