package com.zybooks.myvacationplanner.UI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zybooks.myvacationplanner.Database.Repository;
import com.zybooks.myvacationplanner.Entities.Excursion;
import com.zybooks.myvacationplanner.R;

import java.util.ArrayList;
import java.util.List;

public class ExcursionDetails extends AppCompatActivity {

    String name;
    String date;
    int excursionID;
    int vacaID;
    EditText editName;
    EditText editDate;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_excursion_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editName = findViewById(R.id.excursionnametext);
        editDate = findViewById(R.id.editTextDateExcursions);
        name = getIntent().getStringExtra("name");
        date = getIntent().getStringExtra("date");
        excursionID = getIntent().getIntExtra("id", -1);
        vacaID = getIntent().getIntExtra("vacaID", 1);
        editName.setText(name);
        editDate.setText(date);

        repository = new Repository(getApplication());

    }
    //menu inflater for save delete
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_excursion_details, menu);
        return true;
    }
    //save and delete excursions for both B5B and B3H task requirements
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.saveexcursion) {
            Excursion excursion;
            if (excursionID == -1) {
                if (repository.getmAllExcursions().size() == 0) excursionID = 1;
                else
                    excursionID = repository.getmAllExcursions().get(repository.getmAllExcursions().size() - 1).getExcursionID() + 1;
                excursion = new Excursion(excursionID, editName.getText().toString(), editDate.getText().toString(), vacaID);
                repository.insert(excursion);
                this.finish();
            }
            else {
                excursion = new Excursion(excursionID, editName.getText().toString(), editDate.getText().toString(), vacaID);
                repository.update(excursion);
                this.finish();
            }
        }
            //TODO: delete excursion option
        if(item.getItemId() == R.id.deleteexcursion) {
            Excursion excursion;
            excursion = new Excursion(excursionID,editName.getText().toString(), editDate.getText().toString(), vacaID);
            Toast.makeText(ExcursionDetails.this,"Successfully deleted", Toast.LENGTH_LONG).show();
            repository.delete(excursion);
            this.finish();
        }

        return true;
    }
}