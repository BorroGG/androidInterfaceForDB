package com.example.testproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testproject.db.ProgDatabase;
import com.example.testproject.db.entities.Log;
import com.example.testproject.db.entities.Accused;

import java.time.Instant;

public class AddAccusedActivity extends AppCompatActivity {

    EditText etBirthday, etPassport, etPhoneCommon, etSurname, etName, etMiddleName, etCitizenship, etSocial_status, etOfficial_position;
    Button btnAccept;
    Spinner genderSpinner;
    int gender;
    String[] genders = {"Мужчина", "Женщина"};
    TextView tvUserName, tvExit;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accused);

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
            Intent intent = new Intent(AddAccusedActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());

        btnAccept = findViewById(R.id.acceptReg);

        btnAccept.setOnClickListener(v -> {
            insertAccused();
            Intent intent = new Intent(AddAccusedActivity.this, AccusedActivity.class);
            startActivity(intent);
        });

        createGenderSpinner();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void insertAccused() {
        new Thread(() -> {
            Accused accused = new Accused();
            accused.lastname = etSurname.getText().toString();
            accused.firstname = etName.getText().toString();
            accused.middle_name = etMiddleName.getText().toString();
            accused.gender = String.valueOf(gender);
            accused.birthday = etBirthday.getText().toString();
            accused.passport = etPassport.getText().toString();
            accused.citizenship = etCitizenship.getText().toString();
            accused.social_status = etSocial_status.getText().toString();
            accused.official_position = etOfficial_position.getText().toString();
            accused.phone = etPhoneCommon.getText().toString();

            ProgDatabase.getInstance(getApplicationContext()).accusedDao().insertAll(accused);
            ProgDatabase.getInstance(getApplicationContext()).logDao().insertAll(new Log("INFO", UserData.getLogin(),  Instant.now().toString(), android.os.Build.MODEL, "Add accused in db"));
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
