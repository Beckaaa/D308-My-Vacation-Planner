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

    //TODO:db query all vacations
    public List<Vacation>getmAllVacations() {
        databaseExecutor.execute(()-> {
            mAllVacations = mVacationDAO.getAllVacations();
        });

        try {
          Thread.sleep(1000);
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return mAllVacations;
    }
    //TODO:db query all excursions
    public List<Excursion>getmAllExcursions() {
        databaseExecutor.execute(()-> {
            mAllExcursions = mExcursionDAO.getAllExcursions();
        });

        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return mAllExcursions;
    }
    //db query associated excursions
    public List<Excursion>getmAssociatedExcursions(int vacationID) {
        databaseExecutor.execute(()-> {
            mAssociatedExcursions = mExcursionDAO.getAssociatedExcursions(vacationID);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return mAssociatedExcursions;
    }
    //db executor for insert, update, delete vacations
    public void insert(Vacation vacation) {
        databaseExecutor.execute(()->{
            mVacationDAO.insert(vacation);
        });
    }
    public void update(Vacation vacation) {
        databaseExecutor.execute(()->{
            mVacationDAO.update(vacation);
        });
    }
    public void delete(Vacation vacation) {
        databaseExecutor.execute(()->{
            mVacationDAO.delete(vacation);
        });
    }
    //db executor for insert, update, delete excursions
    public void insert(Excursion excursion) {
        databaseExecutor.execute(()-> {
            mExcursionDAO.insert(excursion);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
    public void update(Excursion excursion) {
        databaseExecutor.execute(()-> {
            mExcursionDAO.update(excursion);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
    public void delete(Excursion excursion) {
        databaseExecutor.execute(()-> {
            mExcursionDAO.delete(excursion);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
