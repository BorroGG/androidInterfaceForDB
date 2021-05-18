package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testproject.db.CursachDatabase;
import com.example.testproject.db.entities.Sentence;

import java.util.ArrayList;
import java.util.List;

public class PeriodSentenceActivity extends AppCompatActivity {

    private volatile List<Sentence> sentenceList = new ArrayList<>();
    Button[] buttons;
    private RelativeLayout relativeLayoutPeriodSentenceForButtons;
    TextView tvUserName, tvExit, tvSentenceText;


    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_sentence);

        relativeLayoutPeriodSentenceForButtons = findViewById(R.id.relativeLayoutPeriodSentenceForButtons);
        tvUserName = findViewById(R.id.userNameText);
        tvSentenceText = findViewById(R.id.SentenceText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(PeriodSentenceActivity.this, MainActivity.class);
            startActivity(intent);
        });

        tvUserName.setText(UserData.getYouLogAs());

    }

    @SuppressLint("ResourceType")
    private void setButtonsData() {

        relativeLayoutPeriodSentenceForButtons.removeAllViews();

        try {
            getAllSentence().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buttons = new Button[sentenceList.size()];
        for (int i = 0; i < sentenceList.size(); i++) {
            int buttonStyle = R.drawable.button;
            buttons[i] = new Button(getApplicationContext());
            String text = sentenceList.get(i).id_sentence + "";
            buttons[i].setText(text);
            buttons[i].setId(400 + i);
            buttons[i].setBackgroundResource(buttonStyle);
            buttons[i].setTextColor(getResources().getColor(R.color.white));
            buttons[i].setOnClickListener(v -> {
                Intent intent = new Intent(PeriodSentenceActivity.this, CurrentPeriodSentenceActivity.class);
                intent.putExtra("currentSentence", sentenceList.get(v.getId() - 400));
                startActivity(intent);
            });

            DisplayMetrics dm = getResources().getDisplayMetrics();
            int dpInPx300 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, dm);
            int dpInPx20 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, dm);

            RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                    dpInPx300,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            buttonParams.topMargin = dpInPx20;

            if (i != 0) {
                buttonParams.addRule(RelativeLayout.BELOW, buttons[i - 1].getId());
            } else {
                buttonParams.addRule(RelativeLayout.BELOW, tvSentenceText.getId());
            }

            relativeLayoutPeriodSentenceForButtons.addView(buttons[i], buttonParams);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setButtonsData();
    }

    private synchronized Thread getAllSentence() {
        Thread thread = new Thread(() -> {
            sentenceList = CursachDatabase.getInstance(getApplicationContext()).sentenceDao().getAll();
            if (UserData.ROLE_ID == 2) {
                List<Sentence> sentences = new ArrayList<>();
                for (int i = 0; i < sentenceList.size(); i++) {
                    if (sentenceList.get(i).id_judge.equals(UserData.CURRENT_USER_JUDGE.id_judge)) {
                        sentences.add(sentenceList.get(i));
                    }
                }
                sentenceList = sentences;
            }
        });
        thread.start();
        return thread;
    }
}