package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testproject.db.CursachDatabase;
import com.example.testproject.db.entities.Judge;
import com.example.testproject.db.entities.Organ_employee;
import com.example.testproject.db.entities.Statement;
import com.example.testproject.db.entities.Victim;

import java.util.ArrayList;
import java.util.List;

public class StatementActivity extends AppCompatActivity {

    private volatile List<Statement> statementList = new ArrayList<>();
    Button[] buttons;
    Button addStatement;
    private RelativeLayout relativeLayout, relativeLayoutStatementForButtons;
    TextView tvUserName, tvExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);

        relativeLayout = findViewById(R.id.relativeLayoutVictim);
        relativeLayoutStatementForButtons = findViewById(R.id.relativeLayoutStatementForButtons);
        addStatement = findViewById(R.id.addStatement);
        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(StatementActivity.this, MainActivity.class);
            startActivity(intent);
        });

        tvUserName.setText(UserData.getYouLogAs());

        if (!UserData.isDutyOfficer()) {
            addStatement.setVisibility(View.GONE);
        }
    }

    @SuppressLint("ResourceType")
    private void setButtonsData() {

        relativeLayoutStatementForButtons.removeAllViews();

        try {
            getAllStatements().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buttons = new Button[statementList.size()];
        for (int i = 0; i < statementList.size(); i++) {
            int buttonStyle = R.drawable.button;
            buttons[i] = new Button(getApplicationContext());
            String text = statementList.get(i).id_statement + "";
            buttons[i].setText(text);
            buttons[i].setId(800 + i);
            buttons[i].setBackgroundResource(buttonStyle);
            buttons[i].setTextColor(getResources().getColor(R.color.white));
            buttons[i].setOnClickListener(v -> {
                Intent intent = new Intent(StatementActivity.this, CurrentStatementActivity.class);
                intent.putExtra("currentStatement", statementList.get(v.getId() - 800));
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
                buttonParams.addRule(RelativeLayout.BELOW, addStatement.getId());
            }

            relativeLayoutStatementForButtons.addView(buttons[i], buttonParams);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setButtonsData();
    }

    private synchronized Thread getAllStatements() {
        Thread thread = new Thread(() -> statementList = CursachDatabase.getInstance(getApplicationContext()).statementDao().getAll());
        thread.start();
        return thread;
    }
}