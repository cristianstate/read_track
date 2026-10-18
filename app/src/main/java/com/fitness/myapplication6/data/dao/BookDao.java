package com.fitness.myapplication6.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.fitness.myapplication6.data.model.Book;

import java.util.List;

/**
 * Data Access Object (DAO) pentru entitatea {@link Book}.
 * Definește metodele de acces și manipulare a datelor din tabelul "books".
 */
@Dao
public interface BookDao {

    /**
     * Inserează o carte în baza de date. În caz de conflict (ex: id existent), înlocuiește înregistrarea.
     *
     * @param book Obiectul carte de inserat
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Book book);

    /**
     * Actualizează datele unei cărți existente în baza de date.
     *
     * @param book Obiectul carte actualizat
     */
    @Update
    void update(Book book);

    /**
     * Șterge o carte din baza de date.
     *
     * @param book Obiectul carte de șters
     */
    @Delete
    void delete(Book book);

    /**
     * Returnează o carte după identificatorul său unic.
     *
     * @param id Identificatorul cărții
     * @return Un obiect LiveData ce conține cartea găsită
     */
    @Query("SELECT * FROM books WHERE id = :id")
    LiveData<Book> getBookById(int id);

    /**
     * Returnează lista tuturor cărților, ordonate alfabetic după titlu.
     *
     * @return Obiect LiveData ce conține lista de cărți
     */
    @Query("SELECT * FROM books ORDER BY title ASC")
    LiveData<List<Book>> getAllBooks();

    /**
     * Caută cărți ale căror titlu sau autor se potrivesc cu termenul de căutare.
     *
     * @param searchQuery Termenul de căutare (poate conține caractere joker `%`)
     * @return Obiect LiveData cu lista de cărți găsite, ordonate alfabetic după titlu
     */
    @Query("SELECT * FROM books WHERE title LIKE :searchQuery OR author LIKE :searchQuery ORDER BY title ASC")
    LiveData<List<Book>> searchBooks(String searchQuery);
}
