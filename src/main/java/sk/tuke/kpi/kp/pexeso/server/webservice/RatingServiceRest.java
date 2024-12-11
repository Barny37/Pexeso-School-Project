package sk.tuke.kpi.kp.pexeso.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.kpi.kp.pexeso.entity.Rating;
import sk.tuke.kpi.kp.pexeso.service.RatingService;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingServiceRest {
    @Autowired
    private RatingService ratingService;

    @GetMapping
    public List<Rating> getTopRating(){return ratingService.getTopRatings();}

    @PostMapping
    public void addRating(@RequestBody Rating rating){ratingService.addRatings(rating);}
}
