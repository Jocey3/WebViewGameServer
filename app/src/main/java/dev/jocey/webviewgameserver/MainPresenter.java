package dev.jocey.webviewgameserver;

import android.content.SharedPreferences;
import android.util.Log;

import dev.jocey.webviewgameserver.model.ModelPreference;
import dev.jocey.webviewgameserver.model.ModelServer;
import dev.jocey.webviewgameserver.util.MainActivityContract;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresenter {
    ModelPreference model;
    CompositeDisposable disposable;
    MainActivityContract view;


    public MainPresenter(SharedPreferences settings, MainActivityContract view) {
        this.view = view;
        model = new ModelPreference(settings);
        disposable = new CompositeDisposable();
    }

    public void getServerAnswer() {
        ModelServer modelServer = new ModelServer();
        disposable.add(modelServer.getAnswer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.d("myLog", "String " + s);
                    if (s.equals("true")) {
                        view.startWebView();
                    } else {
                        view.startGame();
                    }
                }));
    }

    public boolean isFirstStart() {
        return model.getAmountOfAppStarts() < 2;
    }

    public void increaseAmountOfStarts() {
        model.increaseAmountOfStarts();
    }

    public String getLastSession() {
        return model.getLastSession();
    }
}
