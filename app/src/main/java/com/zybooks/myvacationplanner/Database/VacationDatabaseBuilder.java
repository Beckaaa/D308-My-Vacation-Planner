package com.zybooks.myvacationplanner.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.zybooks.myvacationplanner.DAO.ExcursionDAO;
import com.zybooks.myvacationplanner.DAO.VacationDAO;
import com.zybooks.myvacationplanner.Entities.Excursion;
import com.zybooks.myvacationplanner.Entities.Vacation;

@Database(entities= {Vacation.class, Excursion.class}, version= 3, exportSchema = false)
public abstract class VacationDatabaseBuilder extends RoomDatabase {
    public abstract VacationDAO vacationDAO();
    public abstract ExcursionDAO excursionDAO();

    private static volatile VacationDatabaseBuilder INSTANCE;

    // starts a database
    static VacationDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VacationDatabaseBuilder.class) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), VacationDatabaseBuilder.class, "MyVacationDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
