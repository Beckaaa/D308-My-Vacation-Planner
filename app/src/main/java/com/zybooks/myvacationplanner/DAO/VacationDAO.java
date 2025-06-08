package com.zybooks.myvacationplanner.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.zybooks.myvacationplanner.Entities.Vacation;

import java.util.List;

@Dao
public interface VacationDAO {
    // CRUD operations
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Vacation vacation);

    @Update
    void update(Vacation vacation);

    @Delete
    void delete(Vacation vacation);

    @Query("SELECT * FROM VACATIONS ORDER BY vacationID ASC")
    List<Vacation> getAllVacations();
}
