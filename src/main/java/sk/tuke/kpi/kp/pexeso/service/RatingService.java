package sk.tuke.kpi.kp.pexeso.service;

import sk.tuke.kpi.kp.pexeso.entity.Rating;
import java.util.List;

public interface RatingService {
    void addRatings(Rating rating);
    List<Rating> getTopRatings();
    void reset();
}
