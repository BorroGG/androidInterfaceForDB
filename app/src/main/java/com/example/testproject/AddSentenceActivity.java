package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testproject.db.CursachDatabase;
import com.example.testproject.db.entities.Sentence;

public class AddSentenceActivity extends AppCompatActivity {

    EditText date_of_issue, content;
    Button btnAccept;
    TextView tvUserName, tvExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sentence);

        date_of_issue = findViewById(R.id.etDate_of_issue);
        content = findViewById(R.id.etContent);

        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(AddSentenceActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());

        btnAccept = findViewById(R.id.acceptReg);

        btnAccept.setOnClickListener(v -> {
            insertVictim();
            Intent intent = new Intent(AddSentenceActivity.this, SentenceActivity.class);
            startActivity(intent);
        });
    }

    private void insertVictim() {
        new Thread(() -> {
            Sentence sentence = new Sentence();
            sentence.date_of_issue = date_of_issue.getText().toString();
            sentence.content = content.getText().toString();
            sentence.id_judge = (UserData.ROLE_ID == 2 ? UserData.CURRENT_USER_JUDGE.id_judge : "0");

            CursachDatabase.getInstance(getApplicationContext()).sentenceDao().insertAll(sentence);
        }).start();
    }
}