package com.books.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BOOKS")
@Getter
@Setter
public class Book implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "RATING")
    private Double rating;

    public Book(String name, String author, Double rating) {
        this.name = name;
        this.author = author;
        this.rating = rating;
    }

    public Book() {}

}
