package io.javabrains.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.javabrains.bean.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRatingInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackRatingInfo", threadPoolKey = "userRatingPool",
                    threadPoolProperties = {
                        @HystrixProperty(name = "coreSize", value = "20"),
                        @HystrixProperty(name = "maxQueueSize", value = "10")
                    })
    //@HystrixCommand(fallbackMethod = "getFallbackRatingInfo")
    public UserRating getUserRating(String userId) {
        UserRating userRating = restTemplate.getForObject("http://rating-data-service/users/ratings/" + userId, UserRating.class);
        return userRating;
    }

    private UserRating getFallbackRatingInfo(String userId) {
        return new UserRating(userId, null);
    }
}
