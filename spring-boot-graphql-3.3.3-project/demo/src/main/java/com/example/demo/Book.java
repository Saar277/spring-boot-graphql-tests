package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book {
    public String id;
    public String name;
    public int pageCount;
    public String authorId;

    public Author author;

    public Book(String id, String name, int pageCount, String authorId) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
        this.authorId = authorId;
    }

    public static List<Book> books = Arrays.asList(
            new Book("book-1", "Effective Java", 416, "author-1"),
            new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
            new Book("book-3", "Down Under", 436, "author-3")
    );

    public static Book getById(String id) {
        return books.stream()
                .filter(book -> book.id.equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();

        for (int index = 0; index < 5000; index++) {
            books.addAll(Arrays.asList(
                    new Book("book-1", "Effective Java", 416, "author-1"),
                    new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
                    new Book("book-3", "Down Under", 436, "author-3")
            ));
        }

        books.forEach((book) -> book.author = Author.getById(book.authorId));

        return new ArrayList<>(books);
    }
}