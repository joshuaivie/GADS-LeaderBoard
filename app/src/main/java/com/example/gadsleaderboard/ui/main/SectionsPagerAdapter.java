package com.example.gadsleaderboard.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentArray = new ArrayList<>();
    private ArrayList<String> titleArray = new ArrayList<>();
    public SectionsPagerAdapter(
            @NonNull FragmentManager fm, int behavior
    ) {super(fm, behavior);}

    public void addFragment(Fragment fragment, String title){
        fragmentArray.add(fragment);
        titleArray.add(title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArray.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArray.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleArray.get(position);
    }

}