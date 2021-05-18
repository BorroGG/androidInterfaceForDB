package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testproject.db.CursachDatabase;
import com.example.testproject.db.entities.Sentence;
import com.example.testproject.db.entities.Victim;

public class CurrentSentenceActivity extends AppCompatActivity {

    TextView tvUserName, tvExit, tvCurrentName;
    Button editSentence, deleteSentence;
    Sentence currentSentence;
    EditText[] editTexts;
    TextView[] textViews;
    RelativeLayout relativeLayoutCurrentSentenceEditTexts;
    LinearLayout[] textLayouts = new LinearLayout[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_sentence);

        Bundle arguments = getIntent().getExtras();

        currentSentence = (Sentence) arguments.get("currentSentence");

        textLayouts[0] = findViewById(R.id.lineText1);
        textLayouts[1] = findViewById(R.id.lineText2);
        textLayouts[2] = findViewById(R.id.lineText3);
        textLayouts[3] = findViewById(R.id.lineText4);

        tvCurrentName = findViewById(R.id.currentName);
        editSentence = findViewById(R.id.editSentence);
        relativeLayoutCurrentSentenceEditTexts = findViewById(R.id.relativeLayoutCurrentSentenceEditTexts);
        deleteSentence = findViewById(R.id.deleteSentence);
        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(CurrentSentenceActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());
        String currentNameText = currentSentence.id_sentence + "";
        tvCurrentName.setText(currentNameText);

        editSentence.setOnClickListener(v -> {
            if (editSentence.getText().toString().equals("Редактировать")) {
                editSentence.setText("Принять");
                for (int i = 0; i < editTexts.length; i++) {
                    if (i != 0 ) {
                        editTexts[i].setEnabled(true);
                    }
                }
            } else {
                Thread thread = new Thread(() -> {
                    Sentence sentence = new Sentence();
                    sentence.id_sentence = Integer.parseInt(editTexts[0].getText().toString());
                    sentence.date_of_issue = editTexts[1].getText().toString();
                    sentence.content = editTexts[2].getText().toString();
                    sentence.id_judge = editTexts[3].getText().toString();

                    currentSentence = sentence;

                    CursachDatabase.getInstance(getApplicationContext()).sentenceDao().update(sentence);
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setVictimDataText();
                editSentence.setText("Редактировать");
            }

        });
        deleteSentence.setOnClickListener(v -> new Thread(() -> {
            CursachDatabase.getInstance(getApplicationContext()).sentenceDao().delete(currentSentence);
            Intent intent = new Intent(CurrentSentenceActivity.this, VictimActivity.class);
            startActivity(intent);
        }).start());

        setVictimDataText();

    }

    private void setVictimDataText() {
        String[] sentenceData = new String[4];
        sentenceData[0] = String.valueOf(currentSentence.id_sentence);
        sentenceData[1] = currentSentence.date_of_issue;
        sentenceData[2] = currentSentence.content;
        sentenceData[3] = currentSentence.id_judge;

        String[] sentenceSupportData = new String[4];
        sentenceSupportData[0] = "ID приговора: ";
        sentenceSupportData[1] = "Дата вынесения: ";
        sentenceSupportData[2] = "Содержание: ";
        sentenceSupportData[3] = "ID судьи: ";

        editTexts = new EditText[4];
        textViews = new TextView[4];
        for (int i = 0; i < editTexts.length; i++) {
            textLayouts[i].removeAllViews();

            editTexts[i] = new EditText(getApplicationContext());
            textViews[i] = new TextView(getApplicationContext());

            editTexts[i].setText(sentenceData[i]);
            textViews[i].setText(sentenceSupportData[i]);

            editTexts[i].setTextColor(getResources().getColor(R.color.black));
            textViews[i].setTextColor(getResources().getColor(R.color.black));

            editTexts[i].setEnabled(false);


            DisplayMetrics dm = getResources().getDisplayMetrics();
            int dpInPx20 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, dm);
            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            textViewParams.topMargin = dpInPx20;
            textViewParams.leftMargin = dpInPx20;
            textLayouts[i].addView(textViews[i], textViewParams);
            textLayouts[i].addView(editTexts[i], textViewParams);
        }
    }
}