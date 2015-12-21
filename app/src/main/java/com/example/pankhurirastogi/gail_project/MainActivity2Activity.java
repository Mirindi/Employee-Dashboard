package com.example.pankhurirastogi.gail_project;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;


public class MainActivity2Activity extends AppCompatActivity implements android.support.v7.app.ActionBar.TabListener {
    ViewPager vw;
    android.support.v7.app.ActionBar action;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       try {  super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        vw = (ViewPager) findViewById(R.id.pager);
        vw.setAdapter(new MyAdapter(getSupportFragmentManager()));
        vw.setOffscreenPageLimit(2);
        action = getSupportActionBar();

           vw.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
               action.setSelectedNavigationItem(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });



        action.setDisplayShowHomeEnabled(false);
        action.setDisplayShowTitleEnabled(false);
           //noinspection deprecation,deprecation
           action.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);
        android.support.v7.app.ActionBar.Tab tab1 = action.newTab();
        tab1.setText("Charts");
        tab1.setTabListener(this);

        android.support.v7.app.ActionBar.Tab tab2 = action.newTab();
        tab2.setText("Tabular REpresentation");
        tab2.setTabListener(this);

        android.support.v7.app.ActionBar.Tab tab3 = action.newTab();
        tab3.setText("other details");
        tab3.setTabListener(this);

        action.addTab(tab1);
        action.addTab(tab2);
        action.addTab(tab3);}
       catch (Exception e)
       {
           e.printStackTrace();
       }


    }






    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        vw.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

                switch (position) {
                    case 0: {    // Bundle bundle = new Bundle();
                        // String myMessage = "Stackoverflow is cool!";
                        // bundle.putString("nameof",nameof );
                        //Bundle data=
                        FragmentA fragInfo = new FragmentA();
                        //fragInfo.setArguments(data);
                        return fragInfo;
                    }
                    case 1:
                        return new FragmentB();
                    case 2:
                        return new FragmentC();
                }
                Log.d("hsdbcjasn", "NULL NULL NULL NULL NULL NULL NULL NULL ");

            return null;
        }

        /**  @Override
        public Fragment getItem(int arg0) {
            switch (arg0) {
                case 0: {    // Bundle bundle = new Bundle();
                    // String myMessage = "Stackoverflow is cool!";
                    // bundle.putString("nameof",nameof );
                    //Bundle data=
                    FragmentA fragInfo = new FragmentA();
                    //fragInfo.setArguments(data);
                    return fragInfo;
                }
                case 1:
                    return new FragmentB();
                case 2:
                    return new FragmentC();
            }
            Log.d("hsdbcjasn", "NULL NULL NULL NULL NULL NULL NULL NULL ");
            return null;


        }**/
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 3;
        }

    }
}