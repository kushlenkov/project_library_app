package org.example.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Book {
    private int bookId;

    @NotEmpty(message = "Поле название не может быть пустым")
    private String title;

    @NotEmpty(message = "Поле имя автора не может быть пустым")
    private String authorName;

    @Min(value = 1500, message = "Поле год выхода должен быть больше 1500")
    private int realiseYear;

    public Book() {

    }

    public Book(String title, String authorName, int realiseYear) {
        this.title = title;
        this.authorName = authorName;
        this.realiseYear = realiseYear;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getRealiseYear() {
        return realiseYear;
    }

    public void setRealiseYear(int realiseYear) {
        this.realiseYear = realiseYear;
    }
}