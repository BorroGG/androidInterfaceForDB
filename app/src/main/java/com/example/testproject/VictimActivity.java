package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.testproject.db.CursachDatabase;
import com.example.testproject.db.entities.Organ_employee;
import com.example.testproject.db.entities.Victim;

import java.util.List;

public class VictimActivity extends AppCompatActivity {

    private boolean isDutyOfficer;
    List<Victim> victimList;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victim);

        relativeLayout = findViewById(R.id.relativeLayoutVictim);

        Bundle arguments = getIntent().getExtras();
        isDutyOfficer = (boolean) arguments.get("isDutyOfficer");

        getAllVictim();


    }

    private void getAllVictim() {
        new Thread(() -> victimList = CursachDatabase.getInstance(getApplicationContext()).victimDao().getAll()).start();
    }
}