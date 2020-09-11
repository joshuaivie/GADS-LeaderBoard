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
import com.example.gadsleaderboard.ui.apiUtils.RetrofitInterface_Skill;
import com.example.gadsleaderboard.ui.apiUtils.ServiceBuilder;
import com.example.gadsleaderboard.ui.data.SkillsDataModel;
import com.example.gadsleaderboard.ui.utils.SkillsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillLeaders extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private ProgressBar progressBar;
    private RetrofitInterface_Skill skillIQInterface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_skill_leaders, container, false);
        //Configure RecyclerView
        recyclerView = (RecyclerView) v.findViewById(R.id.skillRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        //Get Progress Bar
        progressBar = (ProgressBar) v.findViewById(R.id.progressSkill);
        //Get Learning Leaders
        getSkillLeaders();
        //Return View
        return v;
    }

    private void getSkillLeaders() {
        progressBar.setVisibility(View.VISIBLE);

        skillIQInterface = ServiceBuilder.buildGetService(RetrofitInterface_Skill.class);
        Call<List<SkillsDataModel>> listCall = skillIQInterface.getSkillData();

        listCall.enqueue(new Callback<List<SkillsDataModel>>() {
            @Override
            public void onResponse(Call<List<SkillsDataModel>> call, Response<List<SkillsDataModel>> response) {
                progressBar.setVisibility(View.GONE);
                getData(response.body());
            }

            @Override
            public void onFailure(Call<List<SkillsDataModel>> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getData(List<SkillsDataModel> body) {
        adapter = new SkillsAdapter(body, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }
}