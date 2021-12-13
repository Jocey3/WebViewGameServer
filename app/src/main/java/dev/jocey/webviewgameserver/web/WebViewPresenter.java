package dev.jocey.webviewgameserver.web;

import android.content.SharedPreferences;

import dev.jocey.webviewgameserver.model.ModelPreference;

public class WebViewPresenter {
    private ModelPreference modelPreference;

    public WebViewPresenter(SharedPreferences settings) {
        modelPreference = new ModelPreference(settings);
    }

    public String getLink() {
        return modelPreference.getLink();
    }

    public void saveSession(String web) {
        modelPreference.saveSession(web);
    }

    public void saveLink(String url) {
        modelPreference.saveLink(url);
    }

    public void cleanPreference() {
        modelPreference.cleanPerfomance();
    }
}
