package sk.tuke.kpi.kp.pexeso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sk.tuke.kpi.kp.pexeso.entity.Score;
import java.util.Arrays;
import java.util.List;

public class ScoreServiceRestClient implements ScoreService{
    private String url = "http://localhost:8090/api/score";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addScore(Score score) {
        restTemplate.postForEntity(url,score,Score.class);
    }

    @Override
    public List<Score> getTopScore() {
        return Arrays.asList(restTemplate.getForEntity(url , Score[].class).getBody());
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Reset is not supported on web interface");
    }
}
