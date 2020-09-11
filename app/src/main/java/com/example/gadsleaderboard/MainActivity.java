package com.example.gadsleaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gadsleaderboard.ui.main.LearningLeaders;
import com.example.gadsleaderboard.ui.main.SkillLeaders;
import com.example.gadsleaderboard.ui.main.Submit;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gadsleaderboard.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);

        //Link elements to Layout
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager );
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        Button button = (Button) findViewById(R.id.submitButton);

        //Instantiate the Fragments
        SkillLeaders skillLeaders = new SkillLeaders();
        LearningLeaders learningLeaders = new LearningLeaders();

        //Instantiate PagerAdapter
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        //Add the Fragments to the PagerAdapter
        sectionsPagerAdapter.addFragment(learningLeaders, "Learning Leaders");
        sectionsPagerAdapter.addFragment(skillLeaders, "Skill IQ Leaders");

        // Configure ViewPager and TabLayout to Adapter
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //Setup Submit Button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Submit.class));
            }
        });

    }

}