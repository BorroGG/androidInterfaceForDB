package com.example.testproject;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.db.CursachDatabase;
import com.example.testproject.db.entities.Department;
import com.example.testproject.db.entities.Judge;
import com.example.testproject.db.entities.Log;
import com.example.testproject.db.entities.Organ;
import com.example.testproject.db.entities.Organ_employee;

import org.mindrot.jbcrypt.BCrypt;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    String[] roles = {"Регистрация судьи", "Регистрация сотрудника"};

    EditText etPosition, etRank, etPhone, etSurname, etName, etMiddleName, etLogin, etPass, etIdUser;
    Button btnAccept;
    Spinner spinnerOrgan, spinnerDepartment;

    int positionRole = 0;
    int idOrgan;
    int idDepartment;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        etPosition = findViewById(R.id.position);
        etRank = findViewById(R.id.rank);
        etPhone = findViewById(R.id.phone);
        etSurname = findViewById(R.id.surname);
        etName = findViewById(R.id.name);
        etMiddleName = findViewById(R.id.middleName);
        etLogin = findViewById(R.id.loginReg);
        etPass = findViewById(R.id.passwordReg);
        etIdUser = findViewById(R.id.id_user);
        spinnerOrgan = findViewById(R.id.spinnerIdOrgan);
        spinnerDepartment = findViewById(R.id.spinnerIdDepartment);

        btnAccept = findViewById(R.id.acceptReg);

        btnAccept.setOnClickListener(v -> {
            if (positionRole == 0) {
                insertJudge();
            } else {
                insertEmployee();
            }
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        });

        etPosition.setVisibility(View.GONE);
        etRank.setVisibility(View.GONE);
        etPhone.setVisibility(View.GONE);


        createRoleSpinner();
        registerEmployee();

        createOrganSpinner();
        createDepartmentSpinner();
    }

    private void createDepartmentSpinner() {
        new Thread(() -> {
            List<Department> departmentList = CursachDatabase.getInstance(getApplicationContext()).departmentDao().getAll();
            String[] departmentStrings = new String[departmentList.size()];
            for (int i = 0; i < departmentStrings.length; i++) {
                departmentStrings[i] = departmentList.get(i).id_department + ", " + departmentList.get(i).title_department;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departmentStrings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            spinnerDepartment.setAdapter(adapter);
            spinnerDepartment.setDropDownHorizontalOffset(10);
            spinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    idDepartment = Integer.parseInt(string[0]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createOrganSpinner() {
        new Thread(() -> {
            List<Organ> organList = CursachDatabase.getInstance(getApplicationContext()).organDao().getAll();
            String[] organStrings = new String[organList.size()];
            for (int i = 0; i < organStrings.length; i++) {
                organStrings[i] = organList.get(i).id_organ + ", " + organList.get(i).title_organ;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, organStrings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerOrgan.setAdapter(adapter);
            spinnerOrgan.setDropDownHorizontalOffset(10);
            spinnerOrgan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    idOrgan = Integer.parseInt(string[0]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createRoleSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setDropDownHorizontalOffset(10);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionRole = position;
                String item = (String) parent.getItemAtPosition(position);
                System.out.println(item);
                if (positionRole == 0) {
                    registerJudge();
                } else {
                    registerEmployee();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void insertEmployee() {
        Thread thread = new Thread(() -> {
            Organ_employee organ_employee = new Organ_employee();
            organ_employee.firstname = etName.getText().toString();
            organ_employee.lastname = etSurname.getText().toString();
            organ_employee.middle_name = etMiddleName.getText().toString();
            organ_employee.position = etPosition.getText().toString();
            if (!organ_employee.position.equals("Admin")) {
                organ_employee.rank = etRank.getText().toString();
                organ_employee.phone = etPhone.getText().toString();
                organ_employee.login = etLogin.getText().toString();
                organ_employee.password = BCrypt.hashpw(etPass.getText().toString(), BCrypt.gensalt());
                organ_employee.id_department = idDepartment;
                organ_employee.id_organ = idOrgan;
                organ_employee.id_organ_employee = etIdUser.getText().toString();
                CursachDatabase.getInstance(getApplicationContext()).organ_employeeDao().insert(organ_employee);
                CursachDatabase.getInstance(getApplicationContext()).logDao().insertAll(new Log("INFO", organ_employee.login, Instant.now().toString(), android.os.Build.MODEL, "Add new employee in db"));
            }
        });
        thread.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void insertJudge() {
        new Thread(() -> {
            Judge judge = new Judge();
            judge.firstname = etName.getText().toString();
            judge.lastname = etSurname.getText().toString();
            judge.middle_name = etMiddleName.getText().toString();
            judge.login = etLogin.getText().toString();
            judge.password = BCrypt.hashpw(etPass.getText().toString(), BCrypt.gensalt());
            judge.id_judge = etIdUser.getText().toString();
            CursachDatabase.getInstance(getApplicationContext()).judgeDao().insert(judge);
            CursachDatabase.getInstance(getApplicationContext()).logDao().insertAll(new Log("INFO", judge.login, Instant.now().toString(), android.os.Build.MODEL, "Add new judge in db"));
        }).start();
    }

    private void registerJudge() {
        etPosition.setVisibility(View.GONE);
        etRank.setVisibility(View.GONE);
        etPhone.setVisibility(View.GONE);
        spinnerDepartment.setVisibility(View.GONE);
        spinnerOrgan.setVisibility(View.GONE);
    }

    private void registerEmployee() {
        etPosition.setVisibility(View.VISIBLE);
        etRank.setVisibility(View.VISIBLE);
        etPhone.setVisibility(View.VISIBLE);
        spinnerDepartment.setVisibility(View.VISIBLE);
        spinnerOrgan.setVisibility(View.VISIBLE);
    }
}