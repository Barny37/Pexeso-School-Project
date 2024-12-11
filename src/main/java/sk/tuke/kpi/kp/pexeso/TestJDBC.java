package sk.tuke.kpi.kp.pexeso;

import sk.tuke.kpi.kp.pexeso.service.ScoreServiceFile;
import sk.tuke.kpi.kp.pexeso.service.ScoreServiceJDBC;

public class TestJDBC {
    public static void main(String[] args) throws Exception {
        ScoreServiceJDBC service = new ScoreServiceJDBC();
//        service.reset();
//        service.addScore(new Score("barnyi", 16, 30, 700));
//        service.addScore(new Score("barnyui", 17, 32, 600));
//        service.addScore(new Score("barnykj", 18, 34, 500));
//        service.addScore(new Score("barnygn", 19, 36, 400));
//        service.addScore(new Score("barnyf", 10, 20, 100));

        var scores = service.getTopScore();
        System.out.println(scores);
    }
}