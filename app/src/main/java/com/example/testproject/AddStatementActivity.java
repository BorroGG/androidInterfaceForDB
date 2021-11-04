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
import com.example.testproject.db.entities.Statement;
import com.example.testproject.db.entities.Victim;

import java.time.Instant;
import java.util.List;

public class AddStatementActivity extends AppCompatActivity {


    EditText etId_statement, etRegistration_date, etAdditional_information;
    Button btnAccept;
    Spinner id_victimSpinner;
    TextView tvUserName, tvExit;
    int id_victim;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_statement);

        etId_statement = findViewById(R.id.etId_statement);
        etRegistration_date = findViewById(R.id.etRegistration_date);
        etAdditional_information = findViewById(R.id.etAdditional_information);
        id_victimSpinner = findViewById(R.id.id_victimSpinner);

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

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void insertVictim() {
        new Thread(() -> {
            Statement statement = new Statement();
            statement.id_statement = Integer.parseInt(etId_statement.getText().toString());
            statement.registration_date = etRegistration_date.getText().toString();
            statement.additional_information = etAdditional_information.getText().toString();
            statement.id_victim = id_victim;
            statement.id_organ_employee = UserData.CURRENT_USER_EMPL.id_organ_employee + "";

            ProgDatabase.getInstance(getApplicationContext()).statementDao().insertAll(statement);
            ProgDatabase.getInstance(getApplicationContext()).logDao().insertAll(new Log("INFO", UserData.getLogin(), Instant.now().toString(), android.os.Build.MODEL, "Add statement in db"));
        }).start();
    }

    private void createVictimSpinner() {
        new Thread(() -> {
            List<Victim> victims = ProgDatabase.getInstance(getApplicationContext()).victimDao().getAll();
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
}