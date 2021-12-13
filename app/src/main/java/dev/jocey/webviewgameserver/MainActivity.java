package dev.jocey.webviewgameserver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import dev.jocey.webviewgameserver.game.GameActivity;
import dev.jocey.webviewgameserver.util.MainActivityContract;
import dev.jocey.webviewgameserver.web.WebViewActivity;

public class MainActivity extends AppCompatActivity implements MainActivityContract {
    MainPresenter presenter;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(getSharedPreferences("settings", MODE_PRIVATE), this);
        if (presenter.isFirstStart()) presenter.getServerAnswer();
        else {
            if (presenter.getLastSession().equals("WEB")) startWebView();
            else startGame();
        }
        presenter.increaseAmountOfStarts();

    }

    @Override
    public void startWebView() {
        startActivity(new Intent(this, WebViewActivity.class));
    }

    @Override
    public void startGame() {
        startActivity(new Intent(this, GameActivity.class));
    }

}