package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testproject.db.entities.CustomEntityForCriminal_case;
import com.example.testproject.db.entities.EntityForCriminal_caseAndJudge;

public class CurrentCriminal_caseNotReadyActivity extends AppCompatActivity {

    TextView tvUserName, tvExit, tvCurrentName;
    EntityForCriminal_caseAndJudge currentCustomEntity;
    EditText[] editTexts;
    TextView[] textViews;
    RelativeLayout relativeLayoutCurrentCriminal_caseEditTexts;
    LinearLayout[] textLayouts = new LinearLayout[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_criminal_case);

        Bundle arguments = getIntent().getExtras();

        currentCustomEntity = (EntityForCriminal_caseAndJudge) arguments.get("currentCustomEntity");

        textLayouts[0] = findViewById(R.id.lineText1);
        textLayouts[1] = findViewById(R.id.lineText2);
        textLayouts[2] = findViewById(R.id.lineText3);
        textLayouts[3] = findViewById(R.id.lineText4);
        textLayouts[4] = findViewById(R.id.lineText5);
        textLayouts[5] = findViewById(R.id.lineText6);
        textLayouts[6] = findViewById(R.id.lineText7);

        tvCurrentName = findViewById(R.id.currentName);
        relativeLayoutCurrentCriminal_caseEditTexts = findViewById(R.id.relativeLayoutCurrentCriminal_caseEditTexts);
        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(CurrentCriminal_caseNotReadyActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());
        String currentNameText = String.valueOf(currentCustomEntity.id_criminal_case);
        tvCurrentName.setText(currentNameText);

        setCriminal_caseDataText();

    }

    private void setCriminal_caseDataText() {
        String[] criminal_caseData = new String[7];
        criminal_caseData[0] = String.valueOf(currentCustomEntity.id_criminal_case);
        criminal_caseData[1] = currentCustomEntity.date_of_excitement;
        criminal_caseData[2] = currentCustomEntity.date_of_filing;
        criminal_caseData[3] = String.valueOf(currentCustomEntity.id_statement);
        criminal_caseData[4] = currentCustomEntity.id_organ_employee;
        criminal_caseData[5] = String.valueOf(currentCustomEntity.id_court);
        criminal_caseData[6] = currentCustomEntity.id_judge;

        String[] criminal_caseSupportData = new String[7];
        criminal_caseSupportData[0] = "Номер уголовного дела: ";
        criminal_caseSupportData[1] = "Дата возбуждения: ";
        criminal_caseSupportData[2] = "Дата сдачи в архив: ";
        criminal_caseSupportData[3] = "Номер заявления: ";
        criminal_caseSupportData[4] = "Номер сотрудника органов: ";
        criminal_caseSupportData[5] = "Номер суда: ";
        criminal_caseSupportData[6] = "Номер судьи: ";

        editTexts = new EditText[7];
        textViews = new TextView[7];
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