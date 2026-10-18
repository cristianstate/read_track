package com.fitness.myapplication6.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.fitness.myapplication6.data.dao.BookDao;
import com.fitness.myapplication6.data.database.AppDatabase;
import com.fitness.myapplication6.data.model.Book;

import java.util.List;

/**
 * Repositoriu pentru gestionarea operațiilor cu datele despre cărți.
 * Asigură o sursă unică de adevăr (Single Source of Truth) și abstractizează accesul la baza de date.
 */
public class BookRepository {

    // DAO pentru accesarea operațiilor bazei de date
    private final BookDao bookDao;

    // Lista LiveData cu toate cărțile
    private final LiveData<List<Book>> allBooks;

    /**
     * Constructorul repositoriului. Inițializează baza de date și DAO-ul.
     *
     * @param application Contextul aplicației
     */
    public BookRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        bookDao = db.bookDao();
        allBooks = bookDao.getAllBooks();
    }

    /**
     * Obține lista tuturor cărților.
     *
     * @return LiveData cu lista de cărți
     */
    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    /**
     * Obține o carte specifică în funcție de ID.
     *
     * @param id Identificatorul cărții
     * @return LiveData cu obiectul Book
     */
    public LiveData<Book> getBookById(int id) {
        return bookDao.getBookById(id);
    }

    /**
     * Caută cărți după un termen dat.
     *
     * @param query Termenul de căutare
     * @return LiveData cu lista de cărți potrivite
     */
    public LiveData<List<Book>> searchBooks(String query) {
        return bookDao.searchBooks("%" + query + "%");
    }

    /**
     * Inserează o nouă carte în baza de date în mod asincron (pe un fir de execuție secundar).
     *
     * @param book Cartea de adăugat
     */
    public void insert(Book book) {
        AppDatabase.databaseWriteExecutor.execute(() -> bookDao.insert(book));
    }

    /**
     * Actualizează o carte existentă în mod asincron.
     *
     * @param book Cartea de actualizat
     */
    public void update(Book book) {
        AppDatabase.databaseWriteExecutor.execute(() -> bookDao.update(book));
    }

    /**
     * Șterge o carte din baza de date în mod asincron.
     *
     * @param book Cartea de șters
     */
    public void delete(Book book) {
        AppDatabase.databaseWriteExecutor.execute(() -> bookDao.delete(book));
    }
}
