package com.ryanhayes.android.photogallery;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by Ryan Hayes on 7/9/2017.
 * This class is used to display a photo's webpage
 * it utilizes a webview to do so
 */

public class PhotoPageFragment extends VisibleFragment {
    private static final String ARG_URI = "photo_page_url";

    private Uri mUri;
    private WebView mWebView;
    private ProgressBar mProgressBar;

    public static PhotoPageFragment newInstance(Uri uri){
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI, uri);

        PhotoPageFragment fragment = new PhotoPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUri = getArguments().getParcelable(ARG_URI);
    }

    /* The meat of the class, onCreateView does two things:
     * It creates a webview and uses it to connect to a photo's url.
     * It creates a progress bar which is used to show how far along the page's
     * loading has come.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_photo_page, container, false);

        mProgressBar = (ProgressBar)v.findViewById(R.id.fragment_photo_page_progress_bar);
        mProgressBar.setMax(100);

        mWebView = (WebView) v.findViewById(R.id.fragment_photo_page_web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient(){  //a fancier view that allows more ui
            public void onProgressChanged(WebView webView, int newProgress){ //editing
                if(newProgress == 100){                     //if the page has loaded
                    mProgressBar.setVisibility(View.GONE);  //remove the progress bar
                }else{
                    mProgressBar.setVisibility(View.VISIBLE);  //otherwise keep updating the progress
                    mProgressBar.setProgress(newProgress);
                }
            }

            public void onReceivedTitle(WebView webView, String title){    //used to display
                AppCompatActivity activity = (AppCompatActivity) getActivity(); //the title and
                activity.getSupportActionBar().setSubtitle(title);              //description of the photo
            }                                                                   //in the toolbar
        });
        mWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
                return false;
            }
        });
        mWebView.loadUrl(mUri.toString());  //connect to url
        return v;
    }
}
