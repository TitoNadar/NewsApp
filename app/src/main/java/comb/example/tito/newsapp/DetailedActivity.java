package comb.example.tito.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import dmax.dialog.SpotsDialog;

public class DetailedActivity extends AppCompatActivity {
WebView webView;
    SpotsDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        dialog=new SpotsDialog(this);
        dialog.show();
       //
      webView=(WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                  dialog.dismiss();
           }
        });
        if(getIntent()!=null)
          {
              if(!getIntent().getStringExtra("webURL").isEmpty())
              {
                  webView.loadUrl(getIntent().getStringExtra("webURL"));
              }
          }

    }
}
