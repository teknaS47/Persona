package com.mit.persona;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.numOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)   {
            case 0:
                allFragment allfragment = new allFragment();
                return allfragment;
            case 1:
                artFragment artfragment = new artFragment();
                return artfragment;
            case 2:
                architectureFragment architecturefragment = new architectureFragment();
                return architecturefragment;
            case 3:
                navelShowDesignFragment navelshowdesignfragment = new navelShowDesignFragment();
                return navelshowdesignfragment;
            case 4:
                vedicScienceFragment vedicSciencefragment = new vedicScienceFragment();
                return vedicSciencefragment;
            case 5:
                managementFragment managementfragment = new managementFragment();
                return managementfragment;
            case 6 :
                techFragment techfragment = new techFragment();
                return techfragment;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return numOfTabs;
    }
}
