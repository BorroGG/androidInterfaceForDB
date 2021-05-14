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

    private boolean isDutyOfficer;
    private volatile List<Victim> victimList = new ArrayList<>();
    Button[] buttons;
    Button addVictim;
    private RelativeLayout relativeLayout, relativeLayoutVictimForButtons;
    TextView tvUserName, tvExit;
    int chooseRoleId = 0;
    Organ_employee currentUserEmpl = null;
    Judge currentUserJudge = null;

    @SuppressLint("ResourceType")
    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victim);

        relativeLayout = findViewById(R.id.relativeLayoutVictim);
        relativeLayoutVictimForButtons = findViewById(R.id.relativeLayoutVictimForButtons);
        addVictim = findViewById(R.id.addVictim);
        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(VictimActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Bundle arguments = getIntent().getExtras();
        chooseRoleId = Integer.parseInt(arguments.get("roleId").toString());
        if (chooseRoleId == 2) {
            currentUserJudge = (Judge) arguments.getSerializable("currentUser");
        } else {
            currentUserEmpl = (Organ_employee) arguments.getSerializable("currentUser");
        }
        String userNameText = "Вы вошли как: " + (chooseRoleId == 2 ? currentUserJudge.login : currentUserEmpl.login);
        tvUserName.setText(userNameText);

        isDutyOfficer = (boolean) arguments.get("isDutyOfficer");

        if (!isDutyOfficer) {
            addVictim.setVisibility(View.GONE);
        }

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
                intent.putExtra("currentUser", (chooseRoleId == 2 ? currentUserJudge : currentUserEmpl));
                intent.putExtra("roleId", chooseRoleId);
                intent.putExtra("isDutyOfficer", isDutyOfficer);
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

    private synchronized Thread getAllVictim() {
        Thread thread = new Thread(() -> victimList = CursachDatabase.getInstance(getApplicationContext()).victimDao().getAll());
        thread.start();
        return thread;
    }
}