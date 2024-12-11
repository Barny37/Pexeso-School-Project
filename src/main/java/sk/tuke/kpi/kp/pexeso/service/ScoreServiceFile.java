package sk.tuke.kpi.kp.pexeso.service;


import sk.tuke.kpi.kp.pexeso.entity.Score;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreServiceFile implements ScoreService{


    private List<Score> scores = new ArrayList<>();

    @Override
    public void addScore(Score score){
        scores.add(score);
    }

    @Override
    public List<Score> getTopScore() {
//        scores = load();
//        return scores.stream()
//                .filter(s -> s.setScore(equals(score))
//                        .serted((s1, s2) -> -Integer.compare(s1.getScore(),s2.getScore()))
//                        .limit(10)
//                        .collect(Collectors.toList());
        return null;
    }

    @Override
    public void reset() {

        scores = new ArrayList<>();
    }






}
