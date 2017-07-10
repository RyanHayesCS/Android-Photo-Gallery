package com.ryanhayes.android.photogallery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 * Created by Ryan Hayes on 7/9/2017.
 * This activity is created upon a photo thumbnail being
 * clicked. It is used to create a new instance of a photopagefragment
 * which is used to display a photo's webpage
 */

public class PhotoPageActivity extends SingleFragmentActivity {
    public static Intent newIntent(Context context, Uri photoPageUri){
        Intent i = new Intent(context, PhotoPageActivity.class);
        i.setData(photoPageUri);
        return i;
    }

    @Override
    protected Fragment createFragment(){
        return PhotoPageFragment.newInstance(getIntent().getData());
    }
}
