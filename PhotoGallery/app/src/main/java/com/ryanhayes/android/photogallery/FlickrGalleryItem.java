package com.ryanhayes.android.photogallery;

import android.net.Uri;

/**
 * Created by Ryan Hayes on 7/8/2017.
 * This class is used to create objects for each photo received from flickr
 */

public class FlickrGalleryItem {

    private String mCaption;
    private String mId;
    private String mURL;
    private String mOwner;

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String mOwner) {
        this.mOwner = mOwner;
    }

    /* Creates a uri with the given information
     * this uri can be used to connect to the upload page
     */
    public Uri getPhotoPageUri(){
        return Uri.parse("http://www.flickr.com/photos/")
                .buildUpon()
                .appendPath(mOwner)
                .appendPath(mId)
                .build();
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String mCaption) {
        this.mCaption = mCaption;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getURL() {
        return mURL;
    }

    public void setURL(String mURL) {
        this.mURL = mURL;
    }

    @Override
    public String toString(){
        return mCaption;
    }
}
