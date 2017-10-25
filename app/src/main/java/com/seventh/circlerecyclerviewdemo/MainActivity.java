package com.seventh.circlerecyclerviewdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MultiModeFragment fragment = MultiModeFragment.newInstance(ModeType.TYPE_HORIZONTAL_CIRCLE_BTT);
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
