package com.mit.persona;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class homePage extends Fragment {

    private TabLayout tabLayout;


    public homePage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_id);
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Art"));
        tabLayout.addTab(tabLayout.newTab().setText("Architecture"));
        tabLayout.addTab(tabLayout.newTab().setText("Navel Show & Design"));
        tabLayout.addTab(tabLayout.newTab().setText("Vedic Science"));
        tabLayout.addTab(tabLayout.newTab().setText("Management"));
        tabLayout.addTab(tabLayout.newTab().setText("Technology"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager_id);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager(), tabLayout.getTabCount());


        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }

}
