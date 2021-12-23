package io.javabrains.rest;

import io.javabrains.bean.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/users/movie/{movieId}")
    public Movie getMovieDetails(@PathVariable("movieId") String movieId) {
        if("1".equals(movieId)) {
            return new Movie("1", "Transformers", "Action");
        } else {
            return new Movie("2", "Titanic", "Romantic");
        }
    }
}
