package com.zybooks.myvacationplanner.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zybooks.myvacationplanner.Database.Repository;
import com.zybooks.myvacationplanner.Entities.Excursion;
import com.zybooks.myvacationplanner.Entities.Vacation;
import com.zybooks.myvacationplanner.R;

import java.util.ArrayList;
import java.util.List;

public class VacationDetails extends AppCompatActivity {

    String name;
    String place;
    String startDate;
    String endDate;
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

        editName = findViewById(R.id.vacationtitletext);
        editPlace = findViewById(R.id.placetostaytext);
        editStartDate = findViewById(R.id.editTextStartDateVacations);
        editEndDate = findViewById(R.id.editTextEndDateVacations);
        name = getIntent().getStringExtra("name");
        place = getIntent().getStringExtra("place");
        vacationID = getIntent().getIntExtra("id", -1);
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");
        editName.setText(name);
        editPlace.setText(place);
        editStartDate.setText(startDate);
        editEndDate.setText(endDate);


        // fab functionality
        FloatingActionButton fab = findViewById(R.id.floatingActionButtonVacationDetails);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VacationDetails.this, ExcursionDetails.class);
                startActivity(intent);
            }
        });
        //set recycler view to use excursion repo
        RecyclerView recyclerView = findViewById(R.id.vacationdetailsrecyclerview);
        repository = new Repository(getApplication());
        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
        recyclerView.setAdapter(excursionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Excursion> filteredExcursions = new ArrayList<>();
        for (Excursion e : repository.getmAllExcursions()){
            if (e.getVacationID() == vacationID) filteredExcursions.add(e);
        }
        excursionAdapter.setExcursions(filteredExcursions);
    }

    //menu inflater
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vacation_details, menu);
        return true;
    }
    // TODO: add save/delete vacation options for the vacation details menu
    public boolean onOptionsItemSelected(MenuItem item) {
        // save and update vacations
        if(item.getItemId() == R.id.savevacation) {
            Vacation vacation;
            if(vacationID == -1) {
                if(repository.getmAllVacations().size() ==0) vacationID = 1;
                else vacationID = repository.getmAllVacations().get(repository.getmAllVacations().size() -1).getVacationID() + 1;
                vacation = new Vacation(vacationID, editName.getText().toString(), editPlace.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
                repository.insert(vacation);
                this.finish();
            }
            else {
                vacation = new Vacation(vacationID, editName.getText().toString(), editPlace.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
                repository.update(vacation);
                this.finish();
            }
        }
//TODO: commit commented separate for validation as b1.b
        if(item.getItemId() == R.id.deletevacation) {
            Vacation vacation;
            vacation = new Vacation(vacationID, editName.getText().toString(), editPlace.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
            Toast.makeText(VacationDetails.this,"Successfully deleted", Toast.LENGTH_LONG).show();
            //if (repository.getmAssociatedExcursions(vacationID).size() == 0) {
            repository.delete(vacation);
             //}
            //else {
             //     Toast.makeText(VacationDetails.this,"Cannot delete this vacation", Toast.LENGTH_LONG).show();
             //}

        }
        return true;
    }
}


