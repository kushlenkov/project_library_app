package org.example.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @NotEmpty(message = "Поле Название не может быть пустым")
    @Size(min = 3, max = 50, message = "Значение поля Название должно быть между 3 и 50 знаками")
    @Column(name = "title")
    private String title;


    @NotEmpty(message = "Поле имя автора не может быть пустым")
    @Size(min = 3, max = 50, message = "Значение поля Имя автора должно быть между 3 и 50 знаками")
    @Column(name = "author_name")
    private String authorName;

    @Min(value = 1500, message = "Поле год выхода должно быть больше 1500")
    @Column(name = "realise_year")
    private int realiseYear;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}