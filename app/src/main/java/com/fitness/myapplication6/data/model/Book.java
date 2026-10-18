package com.fitness.myapplication6.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entitate Room care reprezintă o carte în baza de date.
 * Mapată pe tabelul "books".
 */
@Entity(tableName = "books")
public class Book {

    // Identificator unic generat automat de Room pentru fiecare carte
    @PrimaryKey(autoGenerate = true)
    private int id;

    // Titlul cărții
    private String title;

    // Autorul cărții
    private String author;

    // Categoria sau genul cărții
    private String category;

    // Numărul total de capitole
    private int chapters;

    // Numărul total de pagini ale cărții
    private int totalPages;

    // Pagina curentă la care se află cititorul
    private int currentPage;

    // Calea/URI-ul către imaginea de copertă a cărții
    private String imagePath;

    /**
     * Constructor pentru crearea unui nou obiect Book.
     *
     * @param title       Titlul cărții
     * @param author      Autorul cărții
     * @param category    Categoria/genul cărții
     * @param chapters    Numărul de capitole
     * @param totalPages  Numărul total de pagini
     * @param currentPage Pagina curentă citită
     * @param imagePath   Calea către imaginea coperții
     */
    public Book(String title, String author, String category, int chapters, int totalPages, int currentPage, String imagePath) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.chapters = chapters;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.imagePath = imagePath;
    }

    // Getters și Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getChapters() { return chapters; }
    public void setChapters(int chapters) { this.chapters = chapters; }

    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

    public int getCurrentPage() { return currentPage; }
    public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
