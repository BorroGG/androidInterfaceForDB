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

import com.example.testproject.db.ProgDatabase;
import com.example.testproject.db.entities.Accused;

import java.util.ArrayList;
import java.util.List;

public class AccusedActivity extends AppCompatActivity {

    private volatile List<Accused> accusedsList = new ArrayList<>();
    Button[] buttons;
    Button addAccused;
    private RelativeLayout relativeLayoutAccusedForButtons;
    TextView tvUserName, tvExit;


    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accused);

        relativeLayoutAccusedForButtons = findViewById(R.id.relativeLayoutAccusedForButtons);
        addAccused = findViewById(R.id.addAccused);
        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(AccusedActivity.this, MainActivity.class);
            startActivity(intent);
        });

        tvUserName.setText(UserData.getYouLogAs());

        if (UserData.isDutyOfficer() || UserData.ROLE_ID == 2) {
            addAccused.setVisibility(View.GONE);
        }

        addAccused.setOnClickListener(v -> {
            Intent intent = new Intent(AccusedActivity.this, AddAccusedActivity.class);
            startActivity(intent);
        });

    }

    @SuppressLint("ResourceType")
    private void setButtonsData() {

        relativeLayoutAccusedForButtons.removeAllViews();

        try {
            getAllAccused().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buttons = new Button[accusedsList.size()];
        for (int i = 0; i < accusedsList.size(); i++) {
            int buttonStyle = R.drawable.button;
            buttons[i] = new Button(getApplicationContext());
            String text = accusedsList.get(i).lastname + " " + accusedsList.get(i).firstname + " " + accusedsList.get(i).middle_name;
            buttons[i].setText(text);
            buttons[i].setId(700 + i);
            buttons[i].setBackgroundResource(buttonStyle);
            buttons[i].setTextColor(getResources().getColor(R.color.white));
            buttons[i].setOnClickListener(v -> {
                Intent intent = new Intent(AccusedActivity.this, CurrentAccusedActivity.class);
                intent.putExtra("currentAccused", accusedsList.get(v.getId() - 700));
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
                buttonParams.addRule(RelativeLayout.BELOW, addAccused.getId());
            }

            relativeLayoutAccusedForButtons.addView(buttons[i], buttonParams);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setButtonsData();
    }

    private synchronized Thread getAllAccused() {
        Thread thread = new Thread(() -> accusedsList = ProgDatabase.getInstance(getApplicationContext()).accusedDao().getAll());
        thread.start();
        return thread;
    }
}