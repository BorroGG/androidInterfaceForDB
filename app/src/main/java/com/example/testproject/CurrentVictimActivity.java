package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.testproject.db.entities.Victim;

public class CurrentVictimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_victim);

        Bundle arguments = getIntent().getExtras();

        Victim currentVictim = (Victim) arguments.get("currentVictim");
        System.out.println(currentVictim.firstname + " " + currentVictim.middle_name + " " + currentVictim.lastname);
    }
}