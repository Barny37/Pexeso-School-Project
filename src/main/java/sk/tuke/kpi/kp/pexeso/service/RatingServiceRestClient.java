package sk.tuke.kpi.kp.pexeso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sk.tuke.kpi.kp.pexeso.entity.Rating;

import java.util.Arrays;
import java.util.List;

public class RatingServiceRestClient implements  RatingService{
    private String url = "http://localhost:8090/api/rating";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addRatings(Rating rating) {
        restTemplate.postForEntity(url,rating,Rating.class);
    }

    @Override
    public List<Rating> getTopRatings() {
        return Arrays.asList(restTemplate.getForEntity(url,Rating[].class).getBody());
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Reset is not supported on web interface");
    }
}
