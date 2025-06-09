package com.zybooks.myvacationplanner.UI;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ExcursionDetails extends AppCompatActivity {

    String name;
    String date;
    int excursionID;
    int vacaID;
    String vacaStart;
    String vacaEnd;
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
        excursionID = getIntent().getIntExtra("excursionid", -1);
        vacaID = getIntent().getIntExtra("vacationID", -1);
        vacaStart = getIntent().getStringExtra("vacationStart");
        vacaEnd = getIntent().getStringExtra("vacationEnd");
        editName.setText(name);
        editDate.setText(date);
        editDate.setOnClickListener(v -> showDate(editDate));
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
            //B5E add excursion date is during the associated vacation validation
            String excursionDate = editDate.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.US);

            try {
                Date excursionDateCheck = sdf.parse(excursionDate);
                Log.d("ExcursionDetails", "vacaStart: " + vacaStart + ", vacaEnd: " + vacaEnd);
                Date vacaStartCheck = sdf.parse(vacaStart);
                Date vacaEndCheck = sdf.parse(vacaEnd);

                if (excursionDateCheck.before(vacaStartCheck)) {
                    Toast.makeText(this, "Excursion date cannot be before the vacation starts.", Toast.LENGTH_LONG).show();
                    return true;
                }
                if (excursionDateCheck.after(vacaEndCheck)) {
                    Toast.makeText(this, "Excursion date cannot be after the vacation ends.", Toast.LENGTH_LONG).show();
                    return true;
                }

            }
            catch (ParseException e){
                Toast.makeText(this, "Invalid date format", Toast.LENGTH_LONG).show();
                return true;
            }


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
            //delete excursion option
        if(item.getItemId() == R.id.deleteexcursion) {
            Excursion excursion;
            excursion = new Excursion(excursionID,editName.getText().toString(), editDate.getText().toString(), vacaID);
            Toast.makeText(ExcursionDetails.this,"Successfully deleted", Toast.LENGTH_LONG).show();
            repository.delete(excursion);
            this.finish();
        }

        return true;
    }

    //added calendar and format validation
    private void showDate(EditText targetedEditText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay)-> {
            String formattedDate = String.format("%02d/%02d/%02d", selectedMonth +1, selectedDay, selectedYear % 100);
            targetedEditText.setText(formattedDate);
            targetedEditText.setError(null);
            //formattedDate validation
            if (!formattedDate.matches(("\\d{2}/\\d{2}/\\d{2}"))){
                targetedEditText.setError("Invalid date format");
            }

        }, year, month, day);
        datePickerDialog.show();
    }



    //TODO: B5D add an alert that the user can set that will trigger on the excursion date, stating the excursion title
}