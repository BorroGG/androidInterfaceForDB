package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testproject.db.CursachDatabase;
import com.example.testproject.db.entities.Judge;
import com.example.testproject.db.entities.Organ_employee;
import com.example.testproject.db.entities.Victim;

import org.mindrot.jbcrypt.BCrypt;

public class CurrentVictimActivity extends AppCompatActivity {

    TextView tvUserName, tvExit, tvCurrentName;
    Button editVictim, deleteVictim;
    Victim currentVictim;
    EditText[] editTexts;
    TextView[] textViews;
    RelativeLayout relativeLayoutCurrentVictimEditTexts;
    LinearLayout[] textLayouts = new LinearLayout[11];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_victim);

        Bundle arguments = getIntent().getExtras();

        currentVictim = (Victim) arguments.get("currentVictim");

        textLayouts[0] = findViewById(R.id.lineText1);
        textLayouts[1] = findViewById(R.id.lineText2);
        textLayouts[2] = findViewById(R.id.lineText3);
        textLayouts[3] = findViewById(R.id.lineText4);
        textLayouts[4] = findViewById(R.id.lineText5);
        textLayouts[5] = findViewById(R.id.lineText6);
        textLayouts[6] = findViewById(R.id.lineText7);
        textLayouts[7] = findViewById(R.id.lineText8);
        textLayouts[8] = findViewById(R.id.lineText9);
        textLayouts[9] = findViewById(R.id.lineText10);
        textLayouts[10] = findViewById(R.id.lineText11);

        tvCurrentName = findViewById(R.id.currentName);
        editVictim = findViewById(R.id.editVictim);
        relativeLayoutCurrentVictimEditTexts = findViewById(R.id.relativeLayoutCurrentVictimEditTexts);
        deleteVictim = findViewById(R.id.deleteVictim);
        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(CurrentVictimActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());
        String currentNameText = currentVictim.lastname + " " + currentVictim.firstname + " " + currentVictim.middle_name;
        tvCurrentName.setText(currentNameText);

        if (!UserData.isDutyOfficer()) {
            editVictim.setVisibility(View.GONE);
            deleteVictim.setVisibility(View.GONE);
        }

        editVictim.setOnClickListener(v -> {
            if (editVictim.getText().toString().equals("Редактировать")) {
                editVictim.setText("Принять");
                for (int i = 0; i < editTexts.length; i++) {
                    if (i != 0 ) {
                        editTexts[i].setEnabled(true);
                    }
                }
            } else {
                Thread thread = new Thread(() -> {
                    Victim victim = new Victim();
                    victim.id_victim = Integer.parseInt(editTexts[0].getText().toString());
                    victim.lastname = editTexts[1].getText().toString();
                    victim.firstname = editTexts[2].getText().toString();
                    victim.middle_name = editTexts[3].getText().toString();
                    victim.gender = editTexts[4].getText().toString();
                    victim.birthday = editTexts[5].getText().toString();
                    victim.passport = editTexts[6].getText().toString();
                    victim.citizenship = editTexts[7].getText().toString();
                    victim.social_status = editTexts[8].getText().toString();
                    victim.official_position = editTexts[9].getText().toString();
                    victim.phone = editTexts[10].getText().toString();

                    currentVictim = victim;

                    CursachDatabase.getInstance(getApplicationContext()).victimDao().update(victim);
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setVictimDataText();
                editVictim.setText("Редактировать");
            }

        });
        deleteVictim.setOnClickListener(v -> new Thread(() -> {
            CursachDatabase.getInstance(getApplicationContext()).victimDao().delete(currentVictim);
            Intent intent = new Intent(CurrentVictimActivity.this, VictimActivity.class);
            startActivity(intent);
        }).start());

        setVictimDataText();

    }

    private void setVictimDataText() {
        String[] victimData = new String[11];
        victimData[0] = String.valueOf(currentVictim.id_victim);
        victimData[1] = currentVictim.lastname;
        victimData[2] = currentVictim.firstname;
        victimData[3] = currentVictim.middle_name;
        victimData[4] = currentVictim.gender;
        victimData[5] = currentVictim.birthday;
        victimData[6] = currentVictim.passport;
        victimData[7] = currentVictim.citizenship;
        victimData[8] = currentVictim.social_status;
        victimData[9] = currentVictim.official_position;
        victimData[10] = currentVictim.phone;

        String[] victimSupportData = new String[11];
        victimSupportData[0] = "ID: ";
        victimSupportData[1] = "Фамилия: ";
        victimSupportData[2] = "Имя: ";
        victimSupportData[3] = "Отчество: ";
        victimSupportData[4] = "Пол: ";
        victimSupportData[5] = "Дата рождения: ";
        victimSupportData[6] = "Пасспорт: ";
        victimSupportData[7] = "Гражданство: ";
        victimSupportData[8] = "Социальное положение: ";
        victimSupportData[9] = "Должностное положение: ";
        victimSupportData[10] = "Телефон: ";

        editTexts = new EditText[11];
        textViews = new TextView[11];
        for (int i = 0; i < editTexts.length; i++) {
            textLayouts[i].removeAllViews();

            editTexts[i] = new EditText(getApplicationContext());
            textViews[i] = new TextView(getApplicationContext());

            editTexts[i].setText(victimData[i]);
            textViews[i].setText(victimSupportData[i]);

            editTexts[i].setTextColor(getResources().getColor(R.color.black));
            textViews[i].setTextColor(getResources().getColor(R.color.black));

            editTexts[i].setEnabled(false);


            DisplayMetrics dm = getResources().getDisplayMetrics();
            int dpInPx20 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, dm);
            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            textViewParams.topMargin = dpInPx20;
            textViewParams.leftMargin = dpInPx20;
            textLayouts[i].addView(textViews[i], textViewParams);
            textLayouts[i].addView(editTexts[i], textViewParams);
        }
    }
}