package dev.jocey.webviewgameserver.game;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import dev.jocey.webviewgameserver.MainActivity;
import dev.jocey.webviewgameserver.R;

public class GameActivity extends AppCompatActivity implements GameActivityContract {
    private TextView score;
    private TextView firstNum;
    private TextView secondNum;
    private EditText answer;
    private Button check;
    private GamePresenter gamePresenter;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gamePresenter = new GamePresenter(getSharedPreferences("settings", MODE_PRIVATE), this);
        init();
        updateScore();
        changeNums();
        check.setOnClickListener(view -> {
            if ((answer.getText().length() > 0) && (TextUtils.isDigitsOnly(answer.getText())))
                gamePresenter.checkAnswer(firstNum.getText(), secondNum.getText(), answer.getText());
            else {
                answer.getText().clear();
                Toast.makeText(this, "Please input correct answer", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void init() {
        score = findViewById(R.id.txt_score);
        firstNum = findViewById(R.id.first_num);
        secondNum = findViewById(R.id.second_num);
        answer = findViewById(R.id.answer);
        check = findViewById(R.id.btn_check);
    }

    @Override
    public void changeNums() {
        Random random = new Random();
        firstNum.setText(String.valueOf(random.nextInt(100)));
        secondNum.setText(String.valueOf(random.nextInt(100)));
        answer.getText().clear();
    }

    @Override
    public void updateScore() {
        score.setText(gamePresenter.getScore());
    }

    @Override
    public void showItsCorrect() {
        Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showItsWrong() {
        Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onStop() {
        gamePresenter.saveSession("GAME");
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "First Start");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        gamePresenter.cleanPreference();
        startActivity(new Intent(this, MainActivity.class));
        return super.onOptionsItemSelected(item);
    }
}
