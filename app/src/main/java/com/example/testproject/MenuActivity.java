package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testproject.db.entities.Judge;
import com.example.testproject.db.entities.Organ_employee;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    int chooseRoleId = 0;
    Organ_employee currentUserEmpl = null;
    Judge currentUserJudge = null;

    TextView tvUserName, tvExit;
    Button btnVictim, btnStatement, btnOrganEmployee, btnCriminalCase, btnAccused, btnSentence, btnPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle arguments = getIntent().getExtras();
        chooseRoleId = Integer.parseInt(arguments.get("roleId").toString());
        if (chooseRoleId == 2) {
            currentUserJudge = (Judge) arguments.getSerializable("currentUser");
        } else {
            currentUserEmpl = (Organ_employee) arguments.getSerializable("currentUser");
        }

        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);

        btnVictim = findViewById(R.id.victimButton);
        btnStatement = findViewById(R.id.statementButton);
        btnOrganEmployee = findViewById(R.id.organ_employee);
        btnCriminalCase = findViewById(R.id.criminal_case);
        btnAccused = findViewById(R.id.accused);
        btnSentence = findViewById(R.id.sentence);
        btnSentence.setVisibility(chooseRoleId == 1 ? View.GONE : View.VISIBLE);
        btnPeriod = findViewById(R.id.period);
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
        String userNameText = "Вы вошли как: " + (chooseRoleId == 2 ? currentUserJudge.login : currentUserEmpl.login);
        tvUserName.setText(userNameText);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.victimButton:
                Intent intent = new Intent(MenuActivity.this, VictimActivity.class);
                intent.putExtra("currentUser", (chooseRoleId == 2 ? currentUserJudge : currentUserEmpl));
                intent.putExtra("roleId", chooseRoleId);
                intent.putExtra("isDutyOfficer", isDutyOfficer());
                startActivity(intent);
                break;
            case R.id.statementButton:
                System.out.println("statementButton");
                break;
            case R.id.organ_employee:
                System.out.println("organ_employee");
                break;
            case R.id.criminal_case:
                System.out.println("criminal_case");
                break;
            case R.id.accused:
                System.out.println("accused");
                break;
            case R.id.sentence:
                System.out.println("sentence");
                break;
            case R.id.period:
                System.out.println("period");
                break;

        }
    }

    private boolean isDutyOfficer() {
        if (chooseRoleId == 2) {
            return false;
        } else return currentUserEmpl.position.equals("Дежурный в ДЧ") || currentUserEmpl.position.equals("Admin");
    }
}