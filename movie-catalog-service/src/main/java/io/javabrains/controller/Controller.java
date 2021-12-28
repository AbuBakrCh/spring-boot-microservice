package io.javabrains.controller;

import io.javabrains.bean.*;
import io.javabrains.services.MovieInfo;
import io.javabrains.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private UserRatingInfo userRatingInfo;

    @Autowired
    private MovieInfo movieInfo;

    @GetMapping("/catalog/{userId}")
    public UserCatalog getUserCatalog(@PathVariable("userId") String userId) {
        UserCatalog userCatalog = new UserCatalog();
        userCatalog.setUserId(userId);

        List<CatalogInfo> catalogInfoList = new ArrayList<>();

        UserRating userRating = userRatingInfo.getUserRating(userId);
//        UserRating userRating = webClientBuilder.build()
//                .get()
//                .uri("http://localhost:8082/users/ratings/" + userId)
//                .retrieve()
//                .bodyToMono(UserRating.class).block();

        userRating.getRatingList().stream().forEach(
                rating -> {
                    CatalogInfo catalogInfo = new CatalogInfo();
                    Movie movie = movieInfo.getMovieInfo(rating);
                    catalogInfo.setMovieName(movie.getMovieName());
                    catalogInfo.setGenre(movie.getGenre());
                    catalogInfo.setRating(rating.getRating());
                    catalogInfoList.add(catalogInfo);
                }
        );

        userCatalog.setCatalogInfoList(catalogInfoList);
        return userCatalog;
    }
}
