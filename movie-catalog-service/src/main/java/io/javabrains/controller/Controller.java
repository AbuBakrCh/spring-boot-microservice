package io.javabrains.controller;


import io.javabrains.bean.CatalogInfo;
import io.javabrains.bean.Movie;
import io.javabrains.bean.UserCatalog;
import io.javabrains.bean.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/catalog/{userId}")
    public UserCatalog getUserCatalog(@PathVariable("userId") String userId) {
        UserCatalog userCatalog = new UserCatalog();
        userCatalog.setUserId(userId);

        List<CatalogInfo> catalogInfoList = new ArrayList<>();

        UserRating userRating = restTemplate.getForObject("http://rating-data-service/users/ratings/" + userId, UserRating.class);
//        UserRating userRating = webClientBuilder.build()
//                .get()
//                .uri("http://localhost:8082/users/ratings/" + userId)
//                .retrieve()
//                .bodyToMono(UserRating.class).block();

        userRating.getRatingList().stream().forEach(
                rating -> {
                    CatalogInfo catalogInfo = new CatalogInfo();
                    Movie movie = restTemplate.getForObject("http://movie-info-service/users/movie/" + rating.getMovieId(), Movie.class);
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
