package io.javabrains.rest;

import io.javabrains.bean.Rating;
import io.javabrains.bean.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {
    @GetMapping("/users/ratings/{userId}")
    public UserRating getRatings(@PathVariable("userId") String userId) {
        if("1".equals(userId)) {
            List<Rating> ratingList = Arrays.asList(
              new Rating("1", 4),
              new Rating("2", 5)
            );
            return new UserRating("1", ratingList);

        } else {
            List<Rating> ratingList = Arrays.asList(
              new Rating("1", 3),
              new Rating("2", 2)
            );
            return new UserRating("2", ratingList);
        }
    }
}
