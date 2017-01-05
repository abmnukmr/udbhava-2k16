package com.udbhava;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Web extends AppCompatActivity {
    //private ProgressBar progressBar;
    private ProgressDialog mProgressDialog;
    ProgressBar mprogressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        //showProgressDialog();
        ProgressBar progressBar=(ProgressBar)findViewById(R.id.webprog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        WebView web =(WebView)findViewById(R.id.webview);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new Myweb());
        String url=getIntent().getExtras().getString("facebook");
        web.loadUrl(url);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public class Myweb extends WebViewClient{

        private ProgressDialog mProgressDialog;
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            hideProgressDialog();
                    }


    }
    /*public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }*/

    public void hideProgressDialog() {
        ProgressBar progressBar=(ProgressBar)findViewById(R.id.webprog);
       progressBar.setVisibility(View.GONE);
        /*if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }*/

    }

}
