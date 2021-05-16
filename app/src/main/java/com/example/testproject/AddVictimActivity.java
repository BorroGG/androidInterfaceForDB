package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testproject.db.CursachDatabase;
import com.example.testproject.db.entities.Judge;
import com.example.testproject.db.entities.Organ_employee;
import com.example.testproject.db.entities.Victim;

import org.mindrot.jbcrypt.BCrypt;

public class AddVictimActivity extends AppCompatActivity {

    EditText etBirthday, etPassport, etPhoneCommon, etSurname, etName, etMiddleName, etCitizenship, etSocial_status, etOfficial_position;
    Button btnAccept;
    Spinner genderSpinner;
    int gender;
    String[] genders = {"Мужчина", "Женщина"};
    TextView tvUserName, tvExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_victim);

        etBirthday = findViewById(R.id.birthday);
        etPassport = findViewById(R.id.passport);
        etPhoneCommon = findViewById(R.id.phoneCommon);
        etSurname = findViewById(R.id.surname);
        etName = findViewById(R.id.name);
        etMiddleName = findViewById(R.id.middleName);
        etCitizenship = findViewById(R.id.citizenship);
        etSocial_status = findViewById(R.id.social_status);
        etOfficial_position = findViewById(R.id.official_position);
        genderSpinner = findViewById(R.id.genderSpinner);

        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(AddVictimActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());

        btnAccept = findViewById(R.id.acceptReg);

        btnAccept.setOnClickListener(v -> {
            insertVictim();
            Intent intent = new Intent(AddVictimActivity.this, VictimActivity.class);
            startActivity(intent);
        });

        createGenderSpinner();
    }

    private void insertVictim() {
        new Thread(() -> {
            Victim victim = new Victim();
            victim.lastname = etSurname.getText().toString();
            victim.firstname = etName.getText().toString();
            victim.middle_name = etMiddleName.getText().toString();
            victim.gender = String.valueOf(gender);
            victim.birthday = etBirthday.getText().toString();
            victim.passport = etPassport.getText().toString();
            victim.citizenship = etCitizenship.getText().toString();
            victim.social_status = etSocial_status.getText().toString();
            victim.official_position = etOfficial_position.getText().toString();
            victim.phone = etPhoneCommon.getText().toString();

            CursachDatabase.getInstance(getApplicationContext()).victimDao().insertAll(victim);
        }).start();
    }

    private void createGenderSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.genderSpinner);
        spinner.setAdapter(adapter);
        spinner.setDropDownHorizontalOffset(10);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}