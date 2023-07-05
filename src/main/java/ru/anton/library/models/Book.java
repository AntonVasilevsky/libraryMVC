package ru.anton.library.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;


public class Book {
    private final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private int book_id;
    private int person_id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Name should not be empty")
    private String author;
    //@Max(CURRENT_YEAR)
    private int year;

    public Book() {
    }

    public Book(int book_id, int person_id, String name, String author, int year) {
        this.book_id = book_id;
        this.person_id = person_id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", person_id=" + person_id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
