package com.example.demo;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.util.ArrayList;
import java.util.Arrays;

@DgsComponent
public class BookQuery {
    public long maxRequestTime = 0;
    public long avgRequestTime = 0;
    public int requestCount = 0;

    @DgsQuery
    public ArrayList<Book> test() {
        long startTime = System.currentTimeMillis();
        ArrayList<Book> books = new ArrayList<>();

        for (int index = 0; index < 500; index++) {
            books.addAll(Arrays.asList(
                    new Book("book-1", "Effective Java", 416, "author-1"),
                    new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
                    new Book("book-3", "Down Under", 436, "author-3")
            ));
        }

        books.forEach((book) -> book.author = Author.getById(book.authorId));

        long requestTime = System.currentTimeMillis() - startTime;
        avgRequestTime =
                (avgRequestTime * requestCount + requestTime) / (requestCount + 1);
        requestCount++;
        maxRequestTime = Math.max(requestTime, maxRequestTime);

        System.out.println("curr: " + requestTime);
        System.out.println("avg: " + avgRequestTime);
        System.out.println("max: " + maxRequestTime);

        return new ArrayList<>(books);
    }
}
