package com.ryanhayes.android.photogallery;

/**
 * Created by Ryan Hayes on 7/8/2017.
 * The purpose of this class is to be able to instantiate
 * fragments from an activity
 * Taken from the "Big-Nerd-Ranch" Github
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

    protected int getLayoutResId() {        //find layout of fragment
        return R.layout.activity_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {   //
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            manager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
