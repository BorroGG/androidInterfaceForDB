package com.example.testproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testproject.db.ProgDatabase;
import com.example.testproject.db.entities.Criminal_case;
import com.example.testproject.db.entities.Log;
import com.example.testproject.db.entities.Sentence;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class AddSentenceActivity extends AppCompatActivity {

    EditText date_of_issue, content;
    Button btnAccept;
    TextView tvUserName, tvExit;
    Spinner spinnerIdSentenceAndIdCases;
    int idSentence;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sentence);

        date_of_issue = findViewById(R.id.etDate_of_issue);
        content = findViewById(R.id.etContent);
        spinnerIdSentenceAndIdCases = findViewById(R.id.spinnerIdSentenceAndIdCases);

        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(AddSentenceActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());

        btnAccept = findViewById(R.id.acceptReg);

        btnAccept.setOnClickListener(v -> {
            insertSentence();
            Intent intent = new Intent(AddSentenceActivity.this, SentenceActivity.class);
            startActivity(intent);
        });

        createIdSpinner();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void insertSentence() {
        new Thread(() -> {
            Sentence sentence = new Sentence();
            sentence.id_sentence = idSentence;
            sentence.date_of_issue = date_of_issue.getText().toString();
            sentence.content = content.getText().toString();
            sentence.id_judge = (UserData.ROLE_ID == 2 ? UserData.CURRENT_USER_JUDGE.id_judge : "0");

            ProgDatabase.getInstance(getApplicationContext()).sentenceDao().insertAll(sentence);
            ProgDatabase.getInstance(getApplicationContext()).logDao().insertAll(new Log("INFO", UserData.getLogin(), Instant.now().toString(), android.os.Build.MODEL, "Add sentence in db"));
        }).start();
    }

    private void createIdSpinner() {
        new Thread(() -> {
            List<Criminal_case> criminal_cases = ProgDatabase.getInstance(getApplicationContext()).criminal_caseDao().getAll();
            List<Integer> sentencesIds = ProgDatabase.getInstance(getApplicationContext()).sentenceDao().getIds();

            List<String> tempStrings = new ArrayList<>();
            for (int i = 0; i < criminal_cases.size(); i++) {
                if (!sentencesIds.contains(criminal_cases.get(i).id_criminal_case)) {
                    tempStrings.add(criminal_cases.get(i).id_criminal_case + "");
                }
            }
            String[] criminal_casesStrings = new String[tempStrings.size()];
            for (int i = 0; i < tempStrings.size(); i++) {
                criminal_casesStrings[i] = tempStrings.get(i);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, criminal_casesStrings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            spinnerIdSentenceAndIdCases.setAdapter(adapter);
            spinnerIdSentenceAndIdCases.setDropDownHorizontalOffset(10);
            spinnerIdSentenceAndIdCases.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    idSentence = Integer.parseInt(string[0]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }
}