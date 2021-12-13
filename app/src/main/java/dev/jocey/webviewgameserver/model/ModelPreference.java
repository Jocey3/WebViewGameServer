package dev.jocey.webviewgameserver.model;

import android.content.SharedPreferences;
import android.util.Log;

public class ModelPreference {
    private SharedPreferences settings;
    private static final String AMOUNT = "amount_of_starts";
    private static final String LAST = "last_session";
    private static final String SCORE = "score";
    private static final String LINK = "link";


    public ModelPreference(SharedPreferences settings) {
        this.settings = settings;
    }

    public Integer getAmountOfAppStarts() {
        return settings.getInt(AMOUNT, 1);
    }

    public void increaseAmountOfStarts() {
        int amount = settings.getInt(AMOUNT, 1);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(AMOUNT, ++amount);
        editor.apply();
    }

    public void saveSession(String session) {
        settings.edit().putString(LAST, session).apply();
    }

    public String getLastSession() {
        return settings.getString(LAST, "some");
    }

    public void updateScore() {
        int amount = settings.getInt(SCORE, 0) + 5;
        Log.d("myLog", "score" + amount);

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(SCORE, amount);
        editor.apply();
        Log.d("myLog", "updated score");

    }

    public Integer getScore() {
        Log.d("myLog", " is " + settings.contains(SCORE));
        return settings.getInt(SCORE, 0);
    }

    public String getLink() {
        return settings.getString(LINK, "http://html5test.com/");
    }

    public void saveLink(String url) {
        settings.edit().putString(LINK, url).apply();
    }

    public void cleanPerfomance() {
        settings.edit().clear().apply();
    }
}
