package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.testproject.db.entities.Statement;
import com.example.testproject.db.entities.Victim;

public class CurrentStatementActivity extends AppCompatActivity {

    Statement currentStatement;
    TextView tvUserName, tvExit, tvCurrentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_statement);

        Bundle arguments = getIntent().getExtras();

        currentStatement = (Statement) arguments.get("currentStatement");

        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(CurrentStatementActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());
    }
}