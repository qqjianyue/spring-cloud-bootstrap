package org.yifei.spring.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/ratings")
public class RatingserviceApp {

    public static void main(String[] args) {
        SpringApplication.run(RatingserviceApp.class, args);
    }


    private List<Rating> ratingList = Arrays.asList(
            new Rating(1L, 1L, 2),
            new Rating(2L, 1L, 3),
            new Rating(3L, 2L, 4),
            new Rating(4L, 2L, 5)
    );

    @GetMapping("")
    public List<Rating> findRatingsByBookId(@RequestParam Long bookId) {
        return bookId == null || bookId.equals(0L) ? Collections.EMPTY_LIST : ratingList.stream().filter(r -> r.getBookId().equals(bookId)).collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<Rating> findAllRatings() {
        return ratingList;
    }

    public static class Rating {
        private Long id;
        private Long bookId;
        private int stars;

        //standard getters and setters

        public Rating(Long id, Long bookId, int stars) {
            this.id = id;
            this.bookId = bookId;
            this.stars = stars;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getBookId() {
            return bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }

        public int getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Rating rating = (Rating) o;
            return stars == rating.stars &&
                    id.equals(rating.id) &&
                    bookId.equals(rating.bookId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, bookId, stars);
        }

        @Override
        public String toString() {
            return "Rating{" +
                    "id=" + id +
                    ", bookId=" + bookId +
                    ", stars=" + stars +
                    '}';
        }
    }
}
