package org.yifei.spring.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/books")
public class BookserviceApp {

    public static void main(String[] args) {
        SpringApplication.run(BookserviceApp.class, args);
    }

    private List<Book> bookList = Arrays.asList(
            new Book(1L, "Baeldung goes to the market", "Tim Schimandle"),
            new Book(2L, "Baeldung goes to the park", "Slavisa")
    );

    @GetMapping("")
    public List<Book> findAllBooks() {
        return bookList;
    }

    @GetMapping("/{bookId}")
    public Book findBook(@PathVariable Long bookId) {
        return bookList.stream().filter(b -> b.getId().equals(bookId)).findFirst().orElse(null);
    }

    public static class Book {
        private Long id;
        private String author;
        private String title;

        public Book(Long id, String author, String title) {
            this.id = id;
            this.author = author;
            this.title = title;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Book book = (Book) o;
            return id.equals(book.id) &&
                    author.equals(book.author) &&
                    title.equals(book.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, author, title);
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
