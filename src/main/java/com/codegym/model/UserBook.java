package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "userbook")
public class UserBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    public Long code;
    int number;
    @ManyToOne
    private Book book;



    public UserBook() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


}
