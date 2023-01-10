package com.example.cookinghub.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.cookinghub.fragments.AppetizersFragment;
import com.example.cookinghub.fragments.ColdPlatesFragment;
import com.example.cookinghub.fragments.DessertsFragment;
import com.example.cookinghub.fragments.HotPlatesFragment;

public class MainPageAdapter extends FragmentPagerAdapter{
    private static final int TAB_COUNT = 4;
    private String[] tabTitles = {"Appetizers", "Hot Plates", "Cold Plates", "Desserts"};

    public MainPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;
        switch (position) {
            case 0:
                frag = AppetizersFragment.newInstance(tabTitles[position]);
                break;
            case 1:
                frag = HotPlatesFragment.newInstance(tabTitles[position]);
                break;
            case 2:
                frag = ColdPlatesFragment.newInstance(tabTitles[position]);
                break;
            case 3:
                frag = DessertsFragment.newInstance(tabTitles[position]);
                break;

        }
        return frag;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
