package com.example.testproject;

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
import androidx.appcompat.app.AppCompatActivity;
import com.example.testproject.db.CursachDatabase;
import com.example.testproject.db.entities.Organ_employee;

public class CurrentOrganEmployeeActivity extends AppCompatActivity {

    TextView tvUserName, tvExit, tvCurrentName;
    Organ_employee currentOrganEmployee;
    EditText[] editTexts;
    TextView[] textViews;
    RelativeLayout relativeLayoutCurrentOrgan_EmployeeEditTexts;
    LinearLayout[] textLayouts = new LinearLayout[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_organ_employee);

        Bundle arguments = getIntent().getExtras();

        currentOrganEmployee = (Organ_employee) arguments.get("currentOrganEmployee");

        textLayouts[0] = findViewById(R.id.lineText1);
        textLayouts[1] = findViewById(R.id.lineText2);
        textLayouts[2] = findViewById(R.id.lineText3);
        textLayouts[3] = findViewById(R.id.lineText4);
        textLayouts[4] = findViewById(R.id.lineText5);
        textLayouts[5] = findViewById(R.id.lineText6);
        textLayouts[6] = findViewById(R.id.lineText7);
        textLayouts[7] = findViewById(R.id.lineText8);
        textLayouts[8] = findViewById(R.id.lineText9);

        tvCurrentName = findViewById(R.id.currentName);
        relativeLayoutCurrentOrgan_EmployeeEditTexts = findViewById(R.id.relativeLayoutCurrentOrgan_EmployeeEditTexts);
        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(CurrentOrganEmployeeActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());
        String currentNameText = currentOrganEmployee.lastname + " " + currentOrganEmployee.firstname + " " + currentOrganEmployee.middle_name;
        tvCurrentName.setText(currentNameText);

        setOrganEmployeeDataText();

    }

    private void setOrganEmployeeDataText() {
        String[] organEmployeeData = new String[9];
        organEmployeeData[0] = currentOrganEmployee.id_organ_employee;
        organEmployeeData[1] = currentOrganEmployee.lastname;
        organEmployeeData[2] = currentOrganEmployee.firstname;
        organEmployeeData[3] = currentOrganEmployee.middle_name;
        organEmployeeData[4] = currentOrganEmployee.position;
        organEmployeeData[5] = currentOrganEmployee.rank;
        organEmployeeData[6] = currentOrganEmployee.phone;
        organEmployeeData[7] = String.valueOf(currentOrganEmployee.id_organ);
        organEmployeeData[8] = String.valueOf(currentOrganEmployee.id_department);

        String[] organEmployeeSupportData = new String[9];
        organEmployeeSupportData[0] = "ID: ";
        organEmployeeSupportData[1] = "Фамилия: ";
        organEmployeeSupportData[2] = "Имя: ";
        organEmployeeSupportData[3] = "Отчество: ";
        organEmployeeSupportData[4] = "Должность: ";
        organEmployeeSupportData[5] = "Звание: ";
        organEmployeeSupportData[6] = "Служебный телефон: ";
        organEmployeeSupportData[7] = "Код органа: ";
        organEmployeeSupportData[8] = "Код отдела: ";

        editTexts = new EditText[9];
        textViews = new TextView[9];
        for (int i = 0; i < editTexts.length; i++) {
            textLayouts[i].removeAllViews();

            editTexts[i] = new EditText(getApplicationContext());
            textViews[i] = new TextView(getApplicationContext());

            editTexts[i].setText(organEmployeeData[i]);
            textViews[i].setText(organEmployeeSupportData[i]);

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