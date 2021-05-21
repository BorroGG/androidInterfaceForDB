package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.testproject.db.CursachDatabase;
import com.example.testproject.db.entities.Accused;
import com.example.testproject.db.entities.Judge;
import com.example.testproject.db.entities.Organ_employee;

import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnEnter, btnReg;
    EditText etLogin, etPass;
    CursachDatabase db;
    String[] roles = {"Войти как администратор", "Войти как сотрудник", "Войти как судья"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        btnEnter = findViewById(R.id.enter);
        btnEnter.setOnClickListener(this);

        btnReg = findViewById(R.id.reg);
        btnReg.setOnClickListener(this);

        etLogin = findViewById(R.id.login);
        etPass = findViewById(R.id.pass);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setDropDownHorizontalOffset(10);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserData.ROLE_ID = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        db = CursachDatabase.getInstance(getApplicationContext());

    }

    @Override
    public void onClick(View v) {

        String login = etLogin.getText().toString();
        String pass = etPass.getText().toString();

        switch (v.getId()) {
            case R.id.enter:
                checkData(login, pass);
                break;
            case R.id.reg:
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                break;
        }
    }

    private void checkData(String login, String pass) {
        Thread thread = new Thread(() -> {
            if (UserData.ROLE_ID == 0 || UserData.ROLE_ID == 1) {
                List<Organ_employee> organ_employees = CursachDatabase.getInstance(getApplicationContext()).organ_employeeDao().getAll();
                for (Organ_employee organ_employee : organ_employees) {
                    if (login.equals(organ_employee.login) && BCrypt.checkpw(pass, organ_employee.password)) {
                        if ((UserData.ROLE_ID == 0 && organ_employee.position.equals("Администратор")) || UserData.ROLE_ID == 1) {
                            UserData.CURRENT_USER_EMPL = organ_employee;
                            Intent menuIntent = new Intent(MainActivity.this, MenuActivity.class);
                            startActivity(menuIntent);
                            return;
                        }
                    }
                }

            } else {
                List<Judge> listSingle = CursachDatabase.getInstance(getApplicationContext()).judgeDao().getAll();
                for (Judge judge : listSingle) {
                    if (login.equals(judge.login) && BCrypt.checkpw(pass, judge.password)) {
                        UserData.CURRENT_USER_JUDGE = judge;
                        Intent menuIntent = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(menuIntent);
                        return;
                    }
                }
            }


        });
        thread.start();

    }
}