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
import com.example.testproject.db.entities.CustomEntity;
import com.example.testproject.db.entities.Criminal_case;

public class CurrentCriminal_caseActivity extends AppCompatActivity {

    TextView tvUserName, tvExit, tvCurrentName;
    CustomEntity currentCustomEntity;
    EditText[] editTexts;
    TextView[] textViews;
    RelativeLayout relativeLayoutCurrentCriminal_caseEditTexts;
    LinearLayout[] textLayouts = new LinearLayout[11];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_criminal_case);

        Bundle arguments = getIntent().getExtras();

        currentCustomEntity = (CustomEntity) arguments.get("currentCustomEntity");

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
        relativeLayoutCurrentCriminal_caseEditTexts = findViewById(R.id.relativeLayoutCurrentCriminal_caseEditTexts);
        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(CurrentCriminal_caseActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());
        String currentNameText = String.valueOf(currentCustomEntity.cc_id_criminal_case);
        tvCurrentName.setText(currentNameText);

        setCriminal_caseDataText();

    }

    private void setCriminal_caseDataText() {
        String[] criminal_caseData = new String[11];
        criminal_caseData[0] = String.valueOf(currentCustomEntity.cc_id_criminal_case);
        criminal_caseData[1] = currentCustomEntity.cc_date_of_excitement;
        criminal_caseData[2] = currentCustomEntity.cc_date_of_filing;
        criminal_caseData[3] = currentCustomEntity.oe_lastname + " " + currentCustomEntity.oe_firstname + " " + currentCustomEntity.oe_middle_name;
        criminal_caseData[4] = currentCustomEntity.oe_rank;
        criminal_caseData[5] = currentCustomEntity.c_date_and_time_of_crime;
        criminal_caseData[6] = "Ст. " + currentCustomEntity.qoc_article
                + "." + currentCustomEntity.qoc_sign
                + " Ч." + currentCustomEntity.qoc_part
                + " П." + currentCustomEntity.qoc_item;
        criminal_caseData[7] = currentCustomEntity.co_title;
        criminal_caseData[8] = currentCustomEntity.j_lastname + " " + currentCustomEntity.j_firstname + " " + currentCustomEntity.j_middle_name;
        criminal_caseData[9] = currentCustomEntity.s_date_of_issue;
        criminal_caseData[10] = currentCustomEntity.s_content;

        String[] criminal_caseSupportData = new String[11];
        criminal_caseSupportData[0] = "Номер уголовного дела: ";
        criminal_caseSupportData[1] = "Дата возбуждения: ";
        criminal_caseSupportData[2] = "Дата сдачи в архив: ";
        criminal_caseSupportData[3] = "ФИО сотрудника органов: ";
        criminal_caseSupportData[4] = "Должность сотрудника: ";
        criminal_caseSupportData[5] = "Дата и время совершения преступления: ";
        criminal_caseSupportData[6] = "Квалификация преступления: ";
        criminal_caseSupportData[7] = "Название суда: ";
        criminal_caseSupportData[8] = "ФИО судьи: ";
        criminal_caseSupportData[9] = "Дата вынесения приговора: ";
        criminal_caseSupportData[10] = "Содержание приговора: ";

        editTexts = new EditText[11];
        textViews = new TextView[11];
        for (int i = 0; i < editTexts.length; i++) {
            textLayouts[i].removeAllViews();

            editTexts[i] = new EditText(getApplicationContext());
            textViews[i] = new TextView(getApplicationContext());

            editTexts[i].setText(criminal_caseData[i]);
            textViews[i].setText(criminal_caseSupportData[i]);

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