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

import com.example.testproject.db.ProgDatabase;
import com.example.testproject.db.entities.Accused;

public class CurrentAccusedActivity extends AppCompatActivity {

    TextView tvUserName, tvExit, tvCurrentName;
    Button editAccused, deleteAccused;
    Accused currentAccused;
    EditText[] editTexts;
    TextView[] textViews;
    RelativeLayout relativeLayoutCurrentAccusedEditTexts;
    LinearLayout[] textLayouts = new LinearLayout[11];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_accused);

        Bundle arguments = getIntent().getExtras();

        currentAccused = (Accused) arguments.get("currentAccused");

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
        editAccused = findViewById(R.id.editAccused);
        relativeLayoutCurrentAccusedEditTexts = findViewById(R.id.relativeLayoutCurrentAccusedEditTexts);
        deleteAccused = findViewById(R.id.deleteAccused);
        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(CurrentAccusedActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());
        String currentNameText = currentAccused.lastname + " " + currentAccused.firstname + " " + currentAccused.middle_name;
        tvCurrentName.setText(currentNameText);

        if (UserData.ROLE_ID == 2 || UserData.isDutyOfficer()) {
            editAccused.setVisibility(View.GONE);
            deleteAccused.setVisibility(View.GONE);
        }

        editAccused.setOnClickListener(v -> {
            if (editAccused.getText().toString().equals("Редактировать")) {
                editAccused.setText("Принять");
                for (int i = 0; i < editTexts.length; i++) {
                    if (i != 0 ) {
                        editTexts[i].setEnabled(true);
                    }
                }
            } else {
                Thread thread = new Thread(() -> {
                    Accused accused = new Accused();
                    accused.id_accused = Integer.parseInt(editTexts[0].getText().toString());
                    accused.lastname = editTexts[1].getText().toString();
                    accused.firstname = editTexts[2].getText().toString();
                    accused.middle_name = editTexts[3].getText().toString();
                    accused.gender = editTexts[4].getText().toString();
                    accused.birthday = editTexts[5].getText().toString();
                    accused.passport = editTexts[6].getText().toString();
                    accused.citizenship = editTexts[7].getText().toString();
                    accused.social_status = editTexts[8].getText().toString();
                    accused.official_position = editTexts[9].getText().toString();
                    accused.phone = editTexts[10].getText().toString();

                    currentAccused = accused;

                    ProgDatabase.getInstance(getApplicationContext()).accusedDao().update(accused);
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setAccusedDataText();
                editAccused.setText("Редактировать");
            }

        });
        deleteAccused.setOnClickListener(v -> new Thread(() -> {
            ProgDatabase.getInstance(getApplicationContext()).accusedDao().delete(currentAccused);
            Intent intent = new Intent(CurrentAccusedActivity.this, AccusedActivity.class);
            startActivity(intent);
        }).start());

        setAccusedDataText();

    }

    private void setAccusedDataText() {
        String[] accusedData = new String[11];
        accusedData[0] = String.valueOf(currentAccused.id_accused);
        accusedData[1] = currentAccused.lastname;
        accusedData[2] = currentAccused.firstname;
        accusedData[3] = currentAccused.middle_name;
        accusedData[4] = currentAccused.gender;
        accusedData[5] = currentAccused.birthday;
        accusedData[6] = currentAccused.passport;
        accusedData[7] = currentAccused.citizenship;
        accusedData[8] = currentAccused.social_status;
        accusedData[9] = currentAccused.official_position;
        accusedData[10] = currentAccused.phone;

        String[] accusedSupportData = new String[11];
        accusedSupportData[0] = "ID: ";
        accusedSupportData[1] = "Фамилия: ";
        accusedSupportData[2] = "Имя: ";
        accusedSupportData[3] = "Отчество: ";
        accusedSupportData[4] = "Пол: ";
        accusedSupportData[5] = "Дата рождения: ";
        accusedSupportData[6] = "Пасспорт: ";
        accusedSupportData[7] = "Гражданство: ";
        accusedSupportData[8] = "Социальное положение: ";
        accusedSupportData[9] = "Должностное положение: ";
        accusedSupportData[10] = "Телефон: ";

        editTexts = new EditText[11];
        textViews = new TextView[11];
        for (int i = 0; i < editTexts.length; i++) {
            textLayouts[i].removeAllViews();

            editTexts[i] = new EditText(getApplicationContext());
            textViews[i] = new TextView(getApplicationContext());

            editTexts[i].setText(accusedData[i]);
            textViews[i].setText(accusedSupportData[i]);

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