package sk.tuke.gamestudio.service;

import org.junit.Test;
import sk.tuke.kpi.kp.pexeso.entity.Score;
import sk.tuke.kpi.kp.pexeso.service.ScoreServiceFile;
import sk.tuke.kpi.kp.pexeso.service.ScoreServiceJDBC;

import static org.junit.Assert.*;

public class ScoreServiceTest {
    private ScoreServiceJDBC scoreService = new ScoreServiceJDBC();

    @Test
    public void testReset(){
       scoreService.reset();
        assertEquals(0,scoreService.getTopScore().size());
    }

    @Test
    public void testadd() {
        scoreService.reset();
        scoreService.addScore(new Score("barny",30,25,40));
        var scores = scoreService.getTopScore();
        assertEquals(1,scores.size());
        assertEquals("barny",scores.get(0).getPlayer());
        assertEquals(30,scores.get(0).getNum_of_cards());
        assertEquals(25,scores.get(0).getMove());
        assertEquals(40,scores.get(0).getScore());
    }
    @Test
    public void testGetTopScores(){
        scoreService.reset();

        scoreService.addScore(new Score("dodo",50,100,60));
        scoreService.addScore(new Score("miso",64,150,160));
        scoreService.addScore(new Score("jozko",64,120,1000));
        scoreService.addScore(new Score("ferko",32,100,1160));

        var scores = scoreService.getTopScore();
        assertEquals(3,scores.size());

        assertEquals("dodo",scores.get(0).getPlayer());
        assertEquals(50,scores.get(0).getMove());
        assertEquals(150,scores.get(0).getMove());
        assertEquals(60,scores.get(0).getScore());

        assertEquals("miso",scores.get(0).getPlayer());
        assertEquals(64,scores.get(0).getMove());
        assertEquals(150,scores.get(0).getMove());
        assertEquals(160,scores.get(0).getScore());

        assertEquals("jozko",scores.get(0).getPlayer());
        assertEquals(64,scores.get(0).getMove());
        assertEquals(120,scores.get(0).getMove());
        assertEquals(1000,scores.get(0).getScore());

    }
}
