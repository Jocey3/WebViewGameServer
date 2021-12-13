package dev.jocey.webviewgameserver.game;

import android.content.SharedPreferences;
import android.text.Editable;

import dev.jocey.webviewgameserver.model.ModelPreference;

public class GamePresenter {
    private GameActivityContract view;
    private ModelPreference model;

    public GamePresenter(SharedPreferences preferences, GameActivityContract view) {
        model = new ModelPreference(preferences);
        this.view = view;
    }

    public void checkAnswer(CharSequence firstNum, CharSequence secondNum, Editable answerNum) {
        int first = Integer.parseInt((String) firstNum);
        int second = Integer.parseInt((String) secondNum);
        int answer = Integer.parseInt(String.valueOf(answerNum));

        if ((first + second) == answer) {
            updateScore();
            view.showItsCorrect();
            view.updateScore();
            view.changeNums();
        } else {
            view.showItsWrong();
            view.changeNums();
        }

    }

    private void updateScore() {
        model.updateScore();
    }

    public String getScore() {
        return String.valueOf(model.getScore());
    }

    public void saveSession(String game) {
        model.saveSession(game);
    }

    public void cleanPreference() {
        model.cleanPerfomance();
    }
}
