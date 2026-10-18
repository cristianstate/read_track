package com.fitness.myapplication6.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fitness.myapplication6.data.dao.BookDao;
import com.fitness.myapplication6.data.model.Book;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clasa principală a bazei de date Room pentru aplicație.
 * Contine configurația bazei de date și oferă acces la DAO-uri.
 */
@Database(entities = {Book.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Metode abstracte pentru obținerea instanței BookDao.
     */
    public abstract BookDao bookDao();

    // Instanța unică (Singleton) a bazei de date
    private static volatile AppDatabase INSTANCE;
    
    // Numărul de fire de execuție pentru operațiunile de fundal
    private static final int NUMBER_OF_THREADS = 4;
    
    /**
     * Executor Service pentru rularea operațiilor pe baza de date în fundal (background thread).
     */
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Obtine instanța unică (Singleton) a bazei de date.
     * Folosește modelul Double-Checked Locking pentru a garanta siguranța la accesul concurent (thread-safety).
     *
     * @param context Contextul aplicației
     * @return Instanța AppDatabase
     */
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "readtrack_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
