package com.ryanhayes.android.photogallery;

/**
 * Created by Ryan Hayes on 7/8/2017.
 */

public class FlickerGalleryItem {

    private String mCaption;
    private String mId;
    private String mURL;


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
