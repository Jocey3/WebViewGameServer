package dev.jocey.webviewgameserver.web;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dev.jocey.webviewgameserver.MainActivity;
import dev.jocey.webviewgameserver.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private WebViewPresenter webViewPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webViewPresenter = new WebViewPresenter(getSharedPreferences("settings", MODE_PRIVATE));
        initWeb(webViewPresenter.getLink());
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initWeb(String link) {
        webView = findViewById(R.id.web_view);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        } else {
            CookieManager.getInstance().setAcceptCookie(true);
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) webView.goBack();
    }

    @Override
    protected void onStop() {
        webViewPresenter.saveLink(webView.getUrl());
        webViewPresenter.saveSession("WEB");
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "First Start");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        webViewPresenter.cleanPreference();
        startActivity(new Intent(this, MainActivity.class));
        return super.onOptionsItemSelected(item);
    }
}
