package sk.tuke.kpi.kp.pexeso.service;

import sk.tuke.kpi.kp.pexeso.entity.Score;

import java.util.List;

public interface ScoreService {
    void addScore(Score Score);
    List<Score> getTopScore();
    void reset();

}
