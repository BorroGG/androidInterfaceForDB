package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testproject.db.CursachDatabase;
import com.example.testproject.db.entities.Sentence;
import com.example.testproject.db.entities.Statement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentPeriodSentenceActivity extends AppCompatActivity {

    Sentence currentSentence;
    TextView tvUserName, tvExit, tvCurrentName, tvDate;
    RelativeLayout relativeLayoutCurrentPeriodStatementEditTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_period_sentence);

        Bundle arguments = getIntent().getExtras();

        currentSentence = (Sentence) arguments.get("currentSentence");

        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(CurrentPeriodSentenceActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());

        tvCurrentName = findViewById(R.id.currentName);
        tvDate = findViewById(R.id.tvDate);
        relativeLayoutCurrentPeriodStatementEditTexts = findViewById(R.id.relativeLayoutCurrentStatementEditTexts);

        String currentNameText = currentSentence.id_sentence + " ";
        tvCurrentName.setText(currentNameText);
        setDate();
    }

    private void setDate() {
        new Thread(() -> {
            String s1 = CursachDatabase.getInstance(getApplicationContext()).sentenceDao().getDate_Of_Issue(currentSentence.id_sentence);
            String s2 = CursachDatabase.getInstance(getApplicationContext()).statementDao().getRegistration_date(currentSentence.id_sentence);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = null;
            Date date2 = null;
            try {
                date1 = dateFormat.parse(s1);
                date2 = dateFormat.parse(s2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long milliseconds = date1.getTime() - date2.getTime();
            int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
            String s3 = days + " дня";
            tvDate.setText(s3);
        }).start();
    }
}