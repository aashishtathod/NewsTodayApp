package com.example.newstoday.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.newstoday.fragments.BusinessFragment;
import com.example.newstoday.fragments.EntertainmentFragment;
import com.example.newstoday.fragments.HealthFragment;
import com.example.newstoday.fragments.HomeFragment;
import com.example.newstoday.fragments.ScienceFragment;
import com.example.newstoday.fragments.SportsFragment;
import com.example.newstoday.fragments.TechnologyFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 1: return new HealthFragment();

            case 2: return  new TechnologyFragment();

            case 3: return  new ScienceFragment();

            case 4: return  new SportsFragment();

            case 5: return new BusinessFragment();

            case 6: return new EntertainmentFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
