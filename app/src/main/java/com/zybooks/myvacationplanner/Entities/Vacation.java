package com.zybooks.myvacationplanner.Entities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zybooks.myvacationplanner.Database.Repository;
import com.zybooks.myvacationplanner.R;
import com.zybooks.myvacationplanner.UI.ExcursionAdapter;
import com.zybooks.myvacationplanner.UI.ExcursionDetails;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "vacations")
public class Vacation {
    @PrimaryKey(autoGenerate = true)
    private int vacationID;
    private String vacationTitle;
    private String placeName;
    private String startDate;
    private String endDate;

    //constructor
    public Vacation(int vacationID, String vacationTitle, String placeName, String startDate, String endDate) {
        this.vacationID = vacationID;
        this.vacationTitle = vacationTitle;
        this.placeName = placeName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //getters and setters
    public int getVacationID() {
        return vacationID;
    }

    public void setVacationID(int vacationID) {
        this.vacationID = vacationID;
    }

    public String getVacationTitle() {
        return vacationTitle;
    }

    public void setVacationTitle(String vacationTitle) {
        this.vacationTitle = vacationTitle;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


}
