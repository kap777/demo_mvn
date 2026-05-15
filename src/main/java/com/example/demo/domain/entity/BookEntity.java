package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    private String isbn;

    private String title;

    // cascade All = all changes made with returned Author will be written to db
    // When you get a Book you also get an Author and can make changes to the Author, and these changes will be written to DB.
    // При создании Book (с автором кторого ещё нет в базе) мы создаём Author и передаём этого Author в конструктор Book,
    // делаем save[to DB] для этой Book, при этом в DB создастся и автор
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

}
