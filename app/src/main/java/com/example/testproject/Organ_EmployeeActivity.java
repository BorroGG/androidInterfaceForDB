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

import com.example.testproject.db.ProgDatabase;
import com.example.testproject.db.entities.Organ_employee;

import java.util.ArrayList;
import java.util.List;

public class Organ_EmployeeActivity extends AppCompatActivity {

    private volatile List<Organ_employee> organ_employees = new ArrayList<>();
    Button[] buttons;
    private RelativeLayout relativeLayoutOrganEmployeeForButtons;
    TextView tvUserName, tvExit, listName;


    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organ__employee);

        relativeLayoutOrganEmployeeForButtons = findViewById(R.id.relativeLayoutOrgan_EmployeeForButtons);
        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        listName = findViewById(R.id.mainMenuText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(Organ_EmployeeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        tvUserName.setText(UserData.getYouLogAs());

    }

    @SuppressLint("ResourceType")
    private void setButtonsData() {

        relativeLayoutOrganEmployeeForButtons.removeAllViews();

        try {
            getAllOrganEmployee().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buttons = new Button[organ_employees.size()];
        for (int i = 0; i < organ_employees.size(); i++) {
            int buttonStyle = R.drawable.button;
            buttons[i] = new Button(getApplicationContext());
            String text = organ_employees.get(i).lastname + " " + organ_employees.get(i).firstname + " " + organ_employees.get(i).middle_name;
            buttons[i].setText(text);
            buttons[i].setId(900 + i);
            buttons[i].setBackgroundResource(buttonStyle);
            buttons[i].setTextColor(getResources().getColor(R.color.white));
            buttons[i].setOnClickListener(v -> {
                Intent intent = new Intent(Organ_EmployeeActivity.this, CurrentOrganEmployeeActivity.class);
                intent.putExtra("currentOrganEmployee", organ_employees.get(v.getId() - 900));
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
                buttonParams.addRule(RelativeLayout.BELOW, listName.getId());
            }

            relativeLayoutOrganEmployeeForButtons.addView(buttons[i], buttonParams);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setButtonsData();
    }

    private synchronized Thread getAllOrganEmployee() {
        Thread thread = new Thread(() -> organ_employees = ProgDatabase.getInstance(getApplicationContext()).organ_employeeDao().getAll());
        thread.start();
        return thread;
    }
}