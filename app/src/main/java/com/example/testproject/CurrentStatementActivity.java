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
import com.example.testproject.db.entities.Statement;
import com.example.testproject.db.entities.Victim;

public class CurrentStatementActivity extends AppCompatActivity {

    Statement currentStatement;
    TextView tvUserName, tvExit, tvCurrentName;
    LinearLayout[] textLayouts = new LinearLayout[5];
    RelativeLayout relativeLayoutCurrentStatementEditTexts;
    Button editStatement, deleteStatement;
    EditText[] editTexts;
    TextView[] textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_statement);

        Bundle arguments = getIntent().getExtras();

        currentStatement = (Statement) arguments.get("currentStatement");

        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(CurrentStatementActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());

        textLayouts[0] = findViewById(R.id.lineText1);
        textLayouts[1] = findViewById(R.id.lineText2);
        textLayouts[2] = findViewById(R.id.lineText3);
        textLayouts[3] = findViewById(R.id.lineText4);
        textLayouts[4] = findViewById(R.id.lineText5);
        tvCurrentName = findViewById(R.id.currentName);
        relativeLayoutCurrentStatementEditTexts = findViewById(R.id.relativeLayoutCurrentStatementEditTexts);
        editStatement = findViewById(R.id.editStatement);
        deleteStatement = findViewById(R.id.deleteStatement);

        String currentNameText = currentStatement.id_statement + " ";
        tvCurrentName.setText(currentNameText);

        if (!UserData.isDutyOfficer()) {
            editStatement.setVisibility(View.GONE);
            deleteStatement.setVisibility(View.GONE);
        }

        editStatement.setOnClickListener(v -> {
            if (editStatement.getText().toString().equals("Редактировать")) {
                editStatement.setText("Принять");
                for (int i = 0; i < editTexts.length; i++) {
                    if (i != 0 ) {
                        editTexts[i].setEnabled(true);
                    }
                }
            } else {
                Thread thread = new Thread(() -> {
                    Statement statement = new Statement();
                    statement.id_statement = Integer.parseInt(editTexts[0].getText().toString());
                    statement.registration_date = editTexts[1].getText().toString();
                    statement.additional_information = editTexts[2].getText().toString();
                    statement.id_victim = Integer.parseInt( editTexts[3].getText().toString());
                    statement.id_organ_employee = editTexts[4].getText().toString();

                    currentStatement = statement;

                    CursachDatabase.getInstance(getApplicationContext()).statementDao().update(statement);
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setVictimDataText();
                editStatement.setText("Редактировать");
            }

        });
        deleteStatement.setOnClickListener(v -> new Thread(() -> {
            CursachDatabase.getInstance(getApplicationContext()).statementDao().delete(currentStatement);
            Intent intent = new Intent(CurrentStatementActivity.this, VictimActivity.class);
            startActivity(intent);
        }).start());
        setVictimDataText();
    }

    private void setVictimDataText() {
        String[] statementData = new String[5];
        statementData[0] = String.valueOf(currentStatement.id_statement);
        statementData[1] = currentStatement.registration_date;
        statementData[2] = currentStatement.additional_information;
        statementData[3] = String.valueOf(currentStatement.id_victim);
        statementData[4] = currentStatement.id_organ_employee;

        String[] statementSupportData = new String[5];
        statementSupportData[0] = "Номер заявления: ";
        statementSupportData[1] = "Дата регистрации: ";
        statementSupportData[2] = "Дополнительные сведения: ";
        statementSupportData[3] = "Код потерпевшего: ";
        statementSupportData[4] = "Табельный номер сотрудника органа: ";

        editTexts = new EditText[5];
        textViews = new TextView[5];
        for (int i = 0; i < editTexts.length; i++) {
            textLayouts[i].removeAllViews();

            editTexts[i] = new EditText(getApplicationContext());
            textViews[i] = new TextView(getApplicationContext());

            editTexts[i].setText(statementData[i]);
            textViews[i].setText(statementSupportData[i]);

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