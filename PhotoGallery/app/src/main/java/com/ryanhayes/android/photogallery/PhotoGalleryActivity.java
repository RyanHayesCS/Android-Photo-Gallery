package com.ryanhayes.android.photogallery;

import android.support.v4.app.Fragment;

/**
 * Created by Ryan Hayes on 7/8/2017.
 * As the entrypoint to the application, the purpose of
 * this activity is to create the instance of the Fragment
 * which will display the application's content
 */

public class PhotoGalleryActivity extends SingleFragmentActivity{
    @Override
    public Fragment createFragment(){
        return PhotoGalleryFragment.newInstance();
    }
}