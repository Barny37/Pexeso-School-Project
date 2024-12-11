package sk.tuke.kpi.kp.pexeso.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.kpi.kp.pexeso.entity.Score;
import sk.tuke.kpi.kp.pexeso.service.ScoreService;

import java.util.List;

@RestController
@RequestMapping("/api/score")
public class ScoreServiceRest {
    @Autowired
    private ScoreService scoreService;

    @GetMapping
    public List<Score> getTopScore(){
        return scoreService.getTopScore();
    }

    @PostMapping
    public void addScore(@RequestBody Score score){
        scoreService.addScore(score);
    }


}
