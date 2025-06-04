package com.zybooks.myvacationplanner.Database;

import android.adservices.ondevicepersonalization.OnDevicePersonalizationManager;
import android.app.Application;

import com.zybooks.myvacationplanner.DAO.ExcursionDAO;
import com.zybooks.myvacationplanner.DAO.VacationDAO;
import com.zybooks.myvacationplanner.Entities.Excursion;
import com.zybooks.myvacationplanner.Entities.Vacation;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    //TODO: Create REPO and then vacation list activity layout add button and menu for delete/update for user CRUD and push B.1a
    //TODO: add validation for delete to prevent vacation deletion if excursion is associated push B.1.b

    private ExcursionDAO mExcursionDAO;
    private VacationDAO mVacationDAO;
    private List<Vacation> mAllVacations;
    private List<Excursion> mAllExcursions;
    private List<Excursion> mAssociatedExcursions;
    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository (Application application) {
        //get database instance if exists, else trigger to create new instance to build database
        VacationDatabaseBuilder db= VacationDatabaseBuilder.getDatabase(application);
        mExcursionDAO= db.excursionDAO();
        mVacationDAO = db.vacationDAO();
    }

    //db query all vacations

    //db query all excursions

    //db query associated excursions


}
