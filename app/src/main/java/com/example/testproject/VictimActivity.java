package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testproject.db.CursachDatabase;
import com.example.testproject.db.entities.Judge;
import com.example.testproject.db.entities.Organ_employee;
import com.example.testproject.db.entities.Victim;

import java.util.ArrayList;
import java.util.List;

public class VictimActivity extends AppCompatActivity {

    private volatile List<Victim> victimList = new ArrayList<>();
    Button[] buttons;
    Button addVictim;
    private RelativeLayout relativeLayoutVictimForButtons;
    TextView tvUserName, tvExit;


    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victim);

        relativeLayoutVictimForButtons = findViewById(R.id.relativeLayoutVictimForButtons);
        addVictim = findViewById(R.id.addVictim);
        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(VictimActivity.this, MainActivity.class);
            startActivity(intent);
        });

        tvUserName.setText(UserData.getYouLogAs());

        if (!UserData.isDutyOfficer()) {
            if (UserData.ROLE_ID != 0) {
                addVictim.setVisibility(View.GONE);
            }
        }

        addVictim.setOnClickListener(v -> {
            Intent intent = new Intent(VictimActivity.this, AddVictimActivity.class);
            startActivity(intent);
        });

    }

    @SuppressLint("ResourceType")
    private void setButtonsData() {

        relativeLayoutVictimForButtons.removeAllViews();

        try {
            getAllVictim().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buttons = new Button[victimList.size()];
        for (int i = 0; i < victimList.size(); i++) {
            int buttonStyle = R.drawable.button;
            buttons[i] = new Button(getApplicationContext());
            String text = victimList.get(i).lastname + " " + victimList.get(i).firstname + " " + victimList.get(i).middle_name;
            buttons[i].setText(text);
            buttons[i].setId(900 + i);
            buttons[i].setBackgroundResource(buttonStyle);
            buttons[i].setTextColor(getResources().getColor(R.color.white));
            buttons[i].setOnClickListener(v -> {
                Intent intent = new Intent(VictimActivity.this, CurrentVictimActivity.class);
                intent.putExtra("currentVictim", victimList.get(v.getId() - 900));
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
                buttonParams.addRule(RelativeLayout.BELOW, addVictim.getId());
            }

            relativeLayoutVictimForButtons.addView(buttons[i], buttonParams);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setButtonsData();
    }

    private synchronized Thread getAllVictim() {
        Thread thread = new Thread(() -> victimList = CursachDatabase.getInstance(getApplicationContext()).victimDao().getAll());
        thread.start();
        return thread;
    }
}