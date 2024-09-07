package com.example.demo.async;

import com.example.demo.Book;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@org.springframework.stereotype.Controller
public class MyGraphQLResolver {
    // Asynchronous method
    @Async
    @QueryMapping
    public CompletableFuture<List<Book>> asyncBooks() {
        // Simulate a time-consuming operation (e.g., database or API call)
        return CompletableFuture.supplyAsync(Book::getBooks);
    }
}
