package com.fitness.myapplication6.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.fitness.myapplication6.data.model.Book;
import com.fitness.myapplication6.data.repository.BookRepository;

import java.util.List;

/**
 * ViewModel pentru interfața grafică ce gestionează datele legate de cărți.
 * Păstrează datele supraviețuind schimbărilor de configurație ale activității.
 */
public class BookViewModel extends AndroidViewModel {

    // Referință către repository
    private final BookRepository repository;

    // LiveData ce conține lista cărților filtrată în funcție de interogarea de căutare
    private final LiveData<List<Book>> allBooks;

    // MutableLiveData pentru stocarea termenului curent de căutare
    private final MutableLiveData<String> searchQuery = new MutableLiveData<>("");

    /**
     * Constructorul ViewModel-ului.
     *
     * @param application Contextul aplicației pasat clasei AndroidViewModel
     */
    public BookViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepository(application);

        // SwitchMap reactiv: când 'searchQuery' se modifică, se execută interogarea corespunzătoare
        allBooks = Transformations.switchMap(searchQuery, query -> {
            if (query == null || query.isEmpty()) {
                return repository.getAllBooks();
            } else {
                return repository.searchBooks(query);
            }
        });
    }

    /**
     * Returnează lista tuturor cărților (sau lista filtrată dacă există o căutare activă).
     */
    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    /**
     * Obține o carte specifică în funcție de ID.
     */
    public LiveData<Book> getBookById(int id) {
        return repository.getBookById(id);
    }

    /**
     * Setează termenul de căutare pentru filtrarea cărților.
     */
    public void setSearchQuery(String query) {
        searchQuery.setValue(query);
    }

    /**
     * Inserează o carte nouă prin intermediul repositoriului.
     */
    public void insert(Book book) {
        repository.insert(book);
    }

    /**
     * Actualizează datele unei cărți prin intermediul repositoriului.
     */
    public void update(Book book) {
        repository.update(book);
    }

    /**
     * Șterge o carte prin intermediul repositoriului.
     */
    public void delete(Book book) {
        repository.delete(book);
    }
}
