package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testproject.db.entities.Judge;
import com.example.testproject.db.entities.Organ_employee;
import com.example.testproject.db.entities.Sentence;

import java.sql.Statement;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvUserName, tvExit;
    Button btnVictim, btnStatement, btnOrganEmployee, btnCriminalCase, btnAccused, btnSentence, btnPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);

        btnVictim = findViewById(R.id.victimButton);
        btnStatement = findViewById(R.id.statementButton);
        btnOrganEmployee = findViewById(R.id.organ_employee);
        btnCriminalCase = findViewById(R.id.criminal_case);
        btnCriminalCase.setVisibility(UserData.isDutyOfficer() ? View.GONE : View.VISIBLE);
        btnAccused = findViewById(R.id.accused);
        btnAccused.setVisibility(UserData.isDutyOfficer() ? View.GONE : View.VISIBLE);
        btnSentence = findViewById(R.id.sentence);
        btnSentence.setVisibility(UserData.ROLE_ID == 1 ? View.GONE : View.VISIBLE);
        btnPeriod = findViewById(R.id.period);
        btnPeriod.setVisibility(UserData.ROLE_ID == 1 ? View.GONE : View.VISIBLE);

        btnVictim.setOnClickListener(this);
        btnStatement.setOnClickListener(this);
        btnOrganEmployee.setOnClickListener(this);
        btnCriminalCase.setOnClickListener(this);
        btnAccused.setOnClickListener(this);
        btnSentence.setOnClickListener(this);
        btnPeriod.setOnClickListener(this);

        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
        });

        tvUserName.setText(UserData.getYouLogAs());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.victimButton:
                Intent intentVictim = new Intent(MenuActivity.this, VictimActivity.class);
                startActivity(intentVictim);
                break;
            case R.id.statementButton:
                Intent intentStatement = new Intent(MenuActivity.this, StatementActivity.class);
                startActivity(intentStatement);
                break;
            case R.id.organ_employee:
                Intent intentOrgan_Employee = new Intent(MenuActivity.this, Organ_EmployeeActivity.class);
                startActivity(intentOrgan_Employee);
                break;
            case R.id.criminal_case:
                Intent intentCriminal_case = new Intent(MenuActivity.this, ChooseCriminal_caseActivity.class);
                startActivity(intentCriminal_case);
                break;
            case R.id.accused:
                Intent intentAccused = new Intent(MenuActivity.this, AccusedActivity.class);
                startActivity(intentAccused);
                break;
            case R.id.sentence:
                Intent intentSentence = new Intent(MenuActivity.this, SentenceActivity.class);
                startActivity(intentSentence);
                break;
            case R.id.period:
                Intent intentPeriodSentence = new Intent(MenuActivity.this, PeriodSentenceActivity.class);
                startActivity(intentPeriodSentence);
                break;

        }
    }
}