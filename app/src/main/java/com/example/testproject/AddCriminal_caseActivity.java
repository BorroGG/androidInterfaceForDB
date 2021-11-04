package com.example.testproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Transaction;

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
import com.example.testproject.db.entities.Accused;
import com.example.testproject.db.entities.Category_of_crimes;
import com.example.testproject.db.entities.Condition_of_committing;
import com.example.testproject.db.entities.Court;
import com.example.testproject.db.entities.Crime;
import com.example.testproject.db.entities.Damage_category;
import com.example.testproject.db.entities.Focus_category;
import com.example.testproject.db.entities.Group_of_persons_directory;
import com.example.testproject.db.entities.Judge;
import com.example.testproject.db.entities.Criminal_case;
import com.example.testproject.db.entities.Log;
import com.example.testproject.db.entities.Organ_employee;
import com.example.testproject.db.entities.Participants_in_crime;
import com.example.testproject.db.entities.Qualification_of_crime;
import com.example.testproject.db.entities.Reference_previously_convicted;
import com.example.testproject.db.entities.Statement;

import java.time.Instant;
import java.util.List;

public class AddCriminal_caseActivity extends AppCompatActivity {

    EditText etDate_of_excitement, etDate_of_filing,
            etSurname, etName, etMiddleName, etBirthday, etPassport, etCitizenship, etSocial_status, etOfficial_position, etPhoneCommon,
            etNumber_of_criminal_case, etDate_and_time_of_crime,
            etArticle, etSign, etPart, etItem;
    Button btnAccept;
    TextView tvUserName, tvExit;
    Spinner spinnerIdStatement, spinnerIdOrgan_Employee, spinnerIdCourt, spinnerIdJudge,
            genderSpinner,
            id_type_of_groupSpinner, id_category_of_crimesSpinner, id_focus_categorySpinner, id_damage_categorySpinner,
            id_convictionSpinner, id_condition_of_committingSpinner;

    String[] genders = {"Мужчина", "Женщина"};
    int gender, idStatement, idCourt;
    String idOrgan_employee, idJudge, id_type_of_group, id_category_of_crimes, id_focus_category, id_damage_category, id_conviction, id_condition_of_committing;

    int id_criminal_case, id_qualification_of_crime, id_crime, id_accused;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_criminal_case);

        setDataFromXml();

        tvUserName = findViewById(R.id.userNameText);
        tvExit = findViewById(R.id.exitText);
        tvExit.setOnClickListener(v -> {
            Intent intent = new Intent(AddCriminal_caseActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tvUserName.setText(UserData.getYouLogAs());

        btnAccept = findViewById(R.id.acceptReg);

        btnAccept.setOnClickListener(v -> {
            insertCriminal_case();
            Intent intent = new Intent(AddCriminal_caseActivity.this, Criminal_caseActivity.class);
            startActivity(intent);
        });

        createGenderSpinner();
        createStatementSpinner();
        createOrgan_EmployeeSpinner();
        createCourtSpinner();
        createJudgeSpinner();
        createType_of_groupSpinner();
        createCategory_of_crimesSpinner();
        createFocus_categorySpinner();
        createDamage_categorySpinner();
        createConvictionSpinner();
        createCondition_of_committingSpinner();

    }

    private void setDataFromXml() {
        etDate_of_excitement = findViewById(R.id.date_of_excitement);
        etDate_of_filing = findViewById(R.id.date_of_filing);

        etSurname = findViewById(R.id.surname);
        etName = findViewById(R.id.name);
        etMiddleName = findViewById(R.id.middleName);
        etBirthday = findViewById(R.id.birthday);
        etPassport = findViewById(R.id.passport);
        etCitizenship = findViewById(R.id.citizenship);
        etSocial_status = findViewById(R.id.social_status);
        etOfficial_position = findViewById(R.id.official_position);
        etPhoneCommon = findViewById(R.id.phoneCommon);

        etNumber_of_criminal_case = findViewById(R.id.number_of_criminal_case);
        etDate_and_time_of_crime = findViewById(R.id.date_and_time_of_crime);

        etArticle = findViewById(R.id.article);
        etSign = findViewById(R.id.sign);
        etPart = findViewById(R.id.part);
        etItem = findViewById(R.id.item);

        spinnerIdStatement = findViewById(R.id.spinnerIdStatement);
        spinnerIdOrgan_Employee = findViewById(R.id.spinnerIdOrgan_Employee);
        spinnerIdCourt = findViewById(R.id.spinnerIdCourt);
        spinnerIdJudge = findViewById(R.id.spinnerIdJudge);

        genderSpinner = findViewById(R.id.genderSpinner);

        id_type_of_groupSpinner = findViewById(R.id.id_type_of_group);
        id_category_of_crimesSpinner = findViewById(R.id.id_category_of_crimes);
        id_focus_categorySpinner = findViewById(R.id.id_focus_category);
        id_damage_categorySpinner = findViewById(R.id.id_damage_category);

        id_convictionSpinner = findViewById(R.id.id_conviction);
        id_condition_of_committingSpinner = findViewById(R.id.id_condition_of_committing);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Transaction
    private void insertCriminal_case() {
        new Thread(() -> {
            Criminal_case criminal_case = new Criminal_case();
            criminal_case.date_of_excitement = etDate_and_time_of_crime.getText().toString();
            criminal_case.date_of_filing = etDate_of_filing.getText().toString();
            criminal_case.id_statement = idStatement;
            criminal_case.id_organ_employee = idOrgan_employee;
            criminal_case.id_court = idCourt;
            criminal_case.id_judge = idJudge;

            id_criminal_case = (int) ProgDatabase.getInstance(getApplicationContext()).criminal_caseDao().insertAll(criminal_case)[0];
            ProgDatabase.getInstance(getApplicationContext()).logDao().insertAll(new Log("INFO", UserData.getLogin(), Instant.now().toString(), android.os.Build.MODEL, "Add criminal case in db"));

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

            id_accused = (int) ProgDatabase.getInstance(getApplicationContext()).accusedDao().insertAll(accused)[0];

            Qualification_of_crime qualification_of_crime = new Qualification_of_crime();
            qualification_of_crime.article = etArticle.getText().toString();
            qualification_of_crime.sign = etSign.getText().toString();
            qualification_of_crime.part = etPart.getText().toString();
            qualification_of_crime.item = etItem.getText().toString();

            id_qualification_of_crime = (int) ProgDatabase.getInstance(getApplicationContext()).qualification_of_crimeDao().insertAll(qualification_of_crime)[0];

            Crime crime = new Crime();
            crime.number_of_criminal_case = etNumber_of_criminal_case.getText().toString();
            crime.date_and_time_of_crime = etDate_and_time_of_crime.getText().toString();
            crime.id_criminal_case = id_criminal_case;
            crime.id_qualification_of_crime = id_qualification_of_crime;
            crime.id_type_of_group = id_type_of_group;
            crime.id_category_of_crimes = id_category_of_crimes;
            crime.id_focus_category = id_focus_category;
            crime.id_damage_category = id_damage_category;

            id_crime = (int) ProgDatabase.getInstance(getApplicationContext()).crimeDao().insertAll(crime)[0];

            Participants_in_crime participants_in_crime = new Participants_in_crime();
            participants_in_crime.id_crime = id_crime;
            participants_in_crime.id_accused = id_accused;
            participants_in_crime.id_conviction = id_conviction;
            participants_in_crime.id_condition_of_committing = id_condition_of_committing;

            ProgDatabase.getInstance(getApplicationContext()).participants_in_crimeDao().insertAll(participants_in_crime);

        }).start();
    }

    private void createGenderSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genderSpinner.setAdapter(adapter);
        genderSpinner.setDropDownHorizontalOffset(10);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void createStatementSpinner() {
        new Thread(() -> {
            List<Statement> statementList = ProgDatabase.getInstance(getApplicationContext()).statementDao().getAll();
            String[] strings = new String[statementList.size()];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = statementList.get(i).id_statement + ", " + statementList.get(i).additional_information;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerIdStatement.setAdapter(adapter);
            spinnerIdStatement.setDropDownHorizontalOffset(10);
            spinnerIdStatement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    idStatement = Integer.parseInt(string[0]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createOrgan_EmployeeSpinner() {
        new Thread(() -> {
            List<Organ_employee> organ_employees = ProgDatabase.getInstance(getApplicationContext()).organ_employeeDao().getAll();
            String[] strings = new String[organ_employees.size()];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = organ_employees.get(i).id_organ_employee + ", " + organ_employees.get(i).lastname  + ", " + organ_employees.get(i).firstname  + ", " + organ_employees.get(i).middle_name;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerIdOrgan_Employee.setAdapter(adapter);
            spinnerIdOrgan_Employee.setDropDownHorizontalOffset(10);
            spinnerIdOrgan_Employee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    idOrgan_employee = string[0];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createCourtSpinner() {
        new Thread(() -> {
            List<Court> courts = ProgDatabase.getInstance(getApplicationContext()).courtDao().getAll();
            String[] strings = new String[courts.size()];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = courts.get(i).id_court + ", " + courts.get(i).title;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerIdCourt.setAdapter(adapter);
            spinnerIdCourt.setDropDownHorizontalOffset(10);
            spinnerIdCourt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    idCourt = Integer.parseInt(string[0]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createJudgeSpinner() {
        new Thread(() -> {
            List<Judge> judges = ProgDatabase.getInstance(getApplicationContext()).judgeDao().getAll();
            String[] strings = new String[judges.size()];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = judges.get(i).id_judge + ", " + judges.get(i).lastname  + ", " + judges.get(i).firstname  + ", " + judges.get(i).middle_name;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerIdJudge.setAdapter(adapter);
            spinnerIdJudge.setDropDownHorizontalOffset(10);
            spinnerIdJudge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    idJudge = string[0];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createType_of_groupSpinner() {
        new Thread(() -> {
            List<Group_of_persons_directory> group_of_persons_directories = ProgDatabase.getInstance(getApplicationContext()).group_of_persons_directoryDao().getAll();
            String[] strings = new String[group_of_persons_directories.size()];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = group_of_persons_directories.get(i).id_type_of_group + ", " + group_of_persons_directories.get(i).structure;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            id_type_of_groupSpinner.setAdapter(adapter);
            id_type_of_groupSpinner.setDropDownHorizontalOffset(10);
            id_type_of_groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    id_type_of_group = string[0];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createCategory_of_crimesSpinner() {
        new Thread(() -> {
            List<Category_of_crimes> category_of_crimes = ProgDatabase.getInstance(getApplicationContext()).category_of_crimesDao().getAll();
            String[] strings = new String[category_of_crimes.size()];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = category_of_crimes.get(i).id_category_of_crimes + ", " + category_of_crimes.get(i).category;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            id_category_of_crimesSpinner.setAdapter(adapter);
            id_category_of_crimesSpinner.setDropDownHorizontalOffset(10);
            id_category_of_crimesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    id_category_of_crimes = string[0];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createFocus_categorySpinner() {
        new Thread(() -> {
            List<Focus_category> focus_categories = ProgDatabase.getInstance(getApplicationContext()).focus_categoryDao().getAll();
            String[] strings = new String[focus_categories.size()];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = focus_categories.get(i).id_focus_category + ", " + focus_categories.get(i).type_of_focus;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            id_focus_categorySpinner.setAdapter(adapter);
            id_focus_categorySpinner.setDropDownHorizontalOffset(10);
            id_focus_categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    id_focus_category = string[0];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createDamage_categorySpinner() {
        new Thread(() -> {
            List<Damage_category> damage_categories = ProgDatabase.getInstance(getApplicationContext()).damage_categoryDao().getAll();
            String[] strings = new String[damage_categories.size()];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = damage_categories.get(i).id_damage_category + ", " + damage_categories.get(i).type_of_damage;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            id_damage_categorySpinner.setAdapter(adapter);
            id_damage_categorySpinner.setDropDownHorizontalOffset(10);
            id_damage_categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    id_damage_category = string[0];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createConvictionSpinner() {
        new Thread(() -> {
            List<Reference_previously_convicted> reference_previously_convicteds = ProgDatabase.getInstance(getApplicationContext()).reference_previously_convictedDao().getAll();
            String[] strings = new String[reference_previously_convicteds.size()];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = reference_previously_convicteds.get(i).id_conviction + ", " + reference_previously_convicteds.get(i).condition;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            id_convictionSpinner.setAdapter(adapter);
            id_convictionSpinner.setDropDownHorizontalOffset(10);
            id_convictionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    id_conviction = string[0];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createCondition_of_committingSpinner() {
        new Thread(() -> {
            List<Condition_of_committing> condition_of_committings = ProgDatabase.getInstance(getApplicationContext()).condition_of_committingDao().getAll();
            String[] strings = new String[condition_of_committings.size()];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = condition_of_committings.get(i).id_condition_of_committing + ", " + condition_of_committings.get(i).condition;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            id_condition_of_committingSpinner.setAdapter(adapter);
            id_condition_of_committingSpinner.setDropDownHorizontalOffset(10);
            id_condition_of_committingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    id_condition_of_committing = string[0];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }
}