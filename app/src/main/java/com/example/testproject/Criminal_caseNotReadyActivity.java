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
import com.example.testproject.db.entities.EntityForCriminal_caseAndJudge;

import java.util.ArrayList;
import java.util.List;

public class Criminal_caseNotReadyActivity extends AppCompatActivity {

    private volatile List<EntityForCriminal_caseAndJudge> customEntities = new ArrayList<>();
    Button[] buttons;
    Button addCriminal_case;
    private RelativeLayout relativeLayoutCriminal_caseForButtons;
    TextView tvUserName, tvExit;


    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal_case_not_ready);

        relativeLayoutCriminal_caseForButtons = findViewById(R.id.relativeLayoutCriminal_caseForButtons);
        addCriminal_case = findViewById(R.id.addCriminal_case);
        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(Criminal_caseNotReadyActivity.this, MainActivity.class);
            startActivity(intent);
        });

        tvUserName.setText(UserData.getYouLogAs());

        if (UserData.ROLE_ID == 2) {
            addCriminal_case.setVisibility(View.GONE);
        }

        addCriminal_case.setOnClickListener(v -> {
            Intent intent = new Intent(Criminal_caseNotReadyActivity.this, AddCriminal_caseActivity.class);
            startActivity(intent);
        });

    }

    @SuppressLint("ResourceType")
    private void setButtonsData() {

        relativeLayoutCriminal_caseForButtons.removeAllViews();

        try {
            getAllCriminal_case().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buttons = new Button[customEntities.size()];
        for (int i = 0; i < customEntities.size(); i++) {
            int buttonStyle = R.drawable.button;
            buttons[i] = new Button(getApplicationContext());
            String text = String.valueOf(customEntities.get(i).id_criminal_case);
            buttons[i].setText(text);
            buttons[i].setId(400 + i);
            buttons[i].setBackgroundResource(buttonStyle);
            buttons[i].setTextColor(getResources().getColor(R.color.white));
            buttons[i].setOnClickListener(v -> {
                Intent intent = new Intent(Criminal_caseNotReadyActivity.this, CurrentCriminal_caseNotReadyActivity.class);
                intent.putExtra("currentCustomEntity", customEntities.get(v.getId() - 400));
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
                buttonParams.addRule(RelativeLayout.BELOW, addCriminal_case.getId());
            }

            relativeLayoutCriminal_caseForButtons.addView(buttons[i], buttonParams);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setButtonsData();
    }

    private synchronized Thread getAllCriminal_case() {
        Thread thread = new Thread(() -> {
            customEntities = ProgDatabase.getInstance(getApplicationContext()).criminal_caseDao().getAllWithJudgeAndNotComplete();
            if (UserData.ROLE_ID == 2) {
                List<EntityForCriminal_caseAndJudge> customEntitiesTemp = new ArrayList<>();
                for (int i = 0; i < customEntities.size(); i++) {
                    if (customEntities.get(i).id_judge.equals(UserData.CURRENT_USER_JUDGE.id_judge)) {
                        customEntitiesTemp.add(customEntities.get(i));
                    }
                }
                customEntities = customEntitiesTemp;
            }
        });
        thread.start();
        return thread;
    }
}