package com.zybooks.myvacationplanner.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zybooks.myvacationplanner.Database.Repository;
import com.zybooks.myvacationplanner.R;

public class VacationDetails extends AppCompatActivity {

    String name;
   int vacationID;
   EditText editName;
   EditText editPlace;
   EditText editStartDate;
   EditText editEndDate;
   Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vacation_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton fab= findViewById(R.id.floatingActionButtonVacationDetails);
        editName = findViewById(R.id.vacationtitletext);
        editPlace = findViewById(R.id.placetostaytext);
        editStartDate = findViewById(R.id.editTextStartDateVacations);
        editEndDate = findViewById(R.id.editTextEndDateVacations);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VacationDetails.this, ExcursionDetails.class);
                startActivity(intent);
            }
        });

    }

    //menu inflater
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vacation_details, menu);
        return true;
    }
    // TODO: add save/delete vacation options for the vacation details menu


}