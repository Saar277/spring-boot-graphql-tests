package com.example.demo;

import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    public long maxRequestTime = 0;
    public long avgRequestTime = 0;
    public int requestCount = 0;
    @QueryMapping("test")
    public ArrayList<Book> test() {
        long startTime = System.currentTimeMillis();

        List<Book> books = Book.getBooks();

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
