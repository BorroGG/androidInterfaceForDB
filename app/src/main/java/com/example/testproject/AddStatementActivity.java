package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testproject.db.CursachDatabase;
import com.example.testproject.db.entities.Department;
import com.example.testproject.db.entities.Organ_employee;
import com.example.testproject.db.entities.Statement;
import com.example.testproject.db.entities.Victim;

import java.util.List;

public class AddStatementActivity extends AppCompatActivity {


    EditText etId_statement, etRegistration_date, etAdditional_information;
    Button btnAccept;
    Spinner id_victimSpinner, id_organ_employeeSpinner;
    TextView tvUserName, tvExit;
    int id_victim;
    int id_organ_employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_statement);

        etId_statement = findViewById(R.id.etId_statement);
        etRegistration_date = findViewById(R.id.etRegistration_date);
        etAdditional_information = findViewById(R.id.etAdditional_information);
        id_victimSpinner = findViewById(R.id.id_victimSpinner);
        id_organ_employeeSpinner = findViewById(R.id.id_organ_employeeSpinner);

        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(AddStatementActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());

        btnAccept = findViewById(R.id.acceptReg);

        btnAccept.setOnClickListener(v -> {
            insertVictim();
            Intent intent = new Intent(AddStatementActivity.this, StatementActivity.class);
            startActivity(intent);
        });

        createVictimSpinner();
        createOrgan_EmployeeSpinner();

    }

    private void insertVictim() {
        new Thread(() -> {
            Statement statement = new Statement();
            statement.registration_date = etRegistration_date.getText().toString();
            statement.additional_information = etAdditional_information.getText().toString();
            statement.id_victim = id_victim;
            statement.id_organ_employee = id_organ_employee + "";

            CursachDatabase.getInstance(getApplicationContext()).statementDao().insertAll(statement);
        }).start();
    }

    private void createVictimSpinner() {
        new Thread(() -> {
            List<Victim> victims = CursachDatabase.getInstance(getApplicationContext()).victimDao().getAll();
            String[] victimStrings = new String[victims.size()];
            for (int i = 0; i < victimStrings.length; i++) {
                victimStrings[i] = victims.get(i).id_victim + ", " + victims.get(i).lastname + " " + victims.get(i).firstname + " " + victims.get(i).middle_name;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, victimStrings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            id_victimSpinner.setAdapter(adapter);
            id_victimSpinner.setDropDownHorizontalOffset(10);
            id_victimSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    id_victim = Integer.parseInt(string[0]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createOrgan_EmployeeSpinner() {
        new Thread(() -> {
            List<Organ_employee> organEmployees = CursachDatabase.getInstance(getApplicationContext()).organ_employeeDao().getAll();
            String[] organEmployeesStrings = new String[organEmployees.size()];
            for (int i = 0; i < organEmployeesStrings.length; i++) {
                organEmployeesStrings[i] = organEmployees.get(i).id_organ_employee + ", " + organEmployees.get(i).lastname + " " + organEmployees.get(i).firstname + " " + organEmployees.get(i).middle_name;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, organEmployeesStrings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            id_organ_employeeSpinner.setAdapter(adapter);
            id_organ_employeeSpinner.setDropDownHorizontalOffset(10);
            id_organ_employeeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    id_organ_employee = Integer.parseInt(string[0]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }
}