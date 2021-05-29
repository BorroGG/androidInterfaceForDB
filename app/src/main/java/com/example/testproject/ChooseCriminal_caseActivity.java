package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseCriminal_caseActivity extends AppCompatActivity {

    TextView tvUserName, tvExit;
    Button addCriminal_case, completeCriminal_case, notCompleteCriminal_case;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_criminal_case);

        addCriminal_case = findViewById(R.id.addCriminal_case);
        completeCriminal_case = findViewById(R.id.CompleteCriminal_case);
        notCompleteCriminal_case = findViewById(R.id.NotCompleteCriminal_case);
        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(ChooseCriminal_caseActivity.this, MainActivity.class);
            startActivity(intent);
        });

        tvUserName.setText(UserData.getYouLogAs());

        if (UserData.ROLE_ID == 2) {
            addCriminal_case.setVisibility(View.GONE);
        }

        addCriminal_case.setOnClickListener(v -> {
            Intent intent = new Intent(ChooseCriminal_caseActivity.this, AddCriminal_caseActivity.class);
            startActivity(intent);
        });

        completeCriminal_case.setOnClickListener(v -> {
            Intent intent = new Intent(ChooseCriminal_caseActivity.this, Criminal_caseActivity.class);
            startActivity(intent);
        });

        notCompleteCriminal_case.setOnClickListener(v -> {
            Intent intent = new Intent(ChooseCriminal_caseActivity.this, Criminal_caseNotReadyActivity.class);
            startActivity(intent);
        });
    }
}