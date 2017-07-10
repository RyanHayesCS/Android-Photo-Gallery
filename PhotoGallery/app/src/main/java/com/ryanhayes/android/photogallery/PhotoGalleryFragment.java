package com.ryanhayes.android.photogallery;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan Hayes on 7/8/2017.
 * This class creates a recyclerview which is used to
 * hold photos which have been received from photo sharing sites.
 */

public class PhotoGalleryFragment extends VisibleFragment {

    private static final int COLUMNS = 3;
    private static final String TAG = "PhotoGalleryFragment";
    int mPage = 0;

    private RecyclerView mPhotoRecyclerView;
    private List<FlickrGalleryItem> mItems = new ArrayList<>();

    public static PhotoGalleryFragment newInstance(){
        return new PhotoGalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemsTask().execute();         //Run asynctask to receive photos

        Log.i(TAG, "Background thread started");
    }

    /*sets up recycler view*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){
        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);

        mPhotoRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_photo_gallery_recycler_view);
        mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), COLUMNS));

        setupAdapter(); //sets up adapter which is used to hold every photo

        return v;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "Background thread destroyed");
    }

    private void setupAdapter(){
        if(isAdded()){
            mPhotoRecyclerView.setAdapter(new PhotoAdapter(mItems));
        }
    }

    /* A photo holder is used within the recyclerview in order to hold each
       gallery item that is created after the retreival of the flickr resources.
     */
    private class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mItemImageView;
        private FlickrGalleryItem mGalleryItem;

        public PhotoHolder(View itemView){
            super(itemView);

            mItemImageView = (ImageView) itemView.findViewById(R.id.fragment_photo_gallery_image_view);
            itemView.setOnClickListener(this);
        }

        /* This function utilizes the Picasso library as an alternative to creating
         * a class which uses a handler to download each photo thumbnail. Picasso also
         * handles image chacheing and preloading.
         */
        public void bindGalleryItem(FlickrGalleryItem galleryItem){
            Picasso.with(getActivity()).load(galleryItem.getURL()).placeholder(R.drawable.timer_icon).into(mItemImageView);
            mGalleryItem = galleryItem;
        }

        @Override
        public void onClick(View v){
            Intent i = PhotoPageActivity.newIntent(getActivity(), mGalleryItem.getPhotoPageUri());
            startActivity(i);
        }
    }

    /*Creates a photoadapter which containers PhotoHolders*/
    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder>{
        private List<FlickrGalleryItem> mGalleryItems;

        public PhotoAdapter(List<FlickrGalleryItem> galleryItems){
            mGalleryItems = galleryItems;
        }

        /*Use gallery item layout to hold each photo*/
        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.gallery_item, viewGroup, false);
            return new PhotoHolder(view);
        }

        /* This method binds each gallery item to a photo holder*/
        @Override
        public void onBindViewHolder(PhotoHolder photoHolder, int position){
            FlickrGalleryItem galleryItem = mGalleryItems.get(position);
            photoHolder.bindGalleryItem(galleryItem);
        }

        @Override
        public int getItemCount(){
            return mGalleryItems.size();
        }
    }

    /*This asynctask is used to seatblish an httpconnect to Flickr by using the FlickrFetcher class*/
    private class FetchItemsTask extends AsyncTask<Void, Void, List<FlickrGalleryItem>>{
        @Override
        protected List<FlickrGalleryItem> doInBackground(Void... params){
            return new FlickrFetcher().fetchItems();
        }

        @Override
        protected void onPostExecute(List<FlickrGalleryItem> items){
            mItems = items;
            setupAdapter();
        }
    }
}
