package dev.jocey.webviewgameserver.model;

import android.content.SharedPreferences;
import android.util.Log;

public class ModelPreference {
    private SharedPreferences settings;
    private static final String AMOUNT = "amount_of_starts";
    private static final String LAST = "last_session";
    private static final String SCORE = "score";


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

    public String getLastSession() {
        return settings.getString(LAST, "some");
    }

    public void updateScore() {
        int amount = settings.getInt(SCORE, 0) + 5;
        Log.d("myLog", "score" + amount);

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(AMOUNT, amount);
        editor.apply();
        Log.d("myLog", "updated score");

    }

    public Integer getScore() {
        return settings.getInt(SCORE, 0);
    }
}
