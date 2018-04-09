package org.cjkriesesf.opentablecoding;

import android.graphics.Picture;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import timber.log.Timber;

public class ReviewDetailActivity extends AppCompatActivity {

    public static final String REVIEW_URL = "review_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reivew_detail);
        final View progressBar = findViewById(R.id.progressBar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String url = extras.getString(REVIEW_URL);
            Timber.e("loading url: %s", url);
            final WebView myWebView = findViewById(R.id.webView);
            myWebView.setWebViewClient(new MyBrowser());
            myWebView.getSettings().setLoadsImagesAutomatically(true);
            myWebView.getSettings().setJavaScriptEnabled(true);
            myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            myWebView.loadUrl(url);

            myWebView.setPictureListener(new WebView.PictureListener() {
                @Override
                public void onNewPicture(WebView view, @Nullable Picture picture) {

                }
            });
            myWebView.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView view, String url) {
                    myWebView.setVisibility(View.VISIBLE);
                }
            });
            myWebView.loadUrl(url);
        } else {
            Timber.e("No url passed in");
        }
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
