package sk.tuke.kpi.kp.pexeso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sk.tuke.kpi.kp.pexeso.entity.Comment;

import java.util.Arrays;
import java.util.List;

public class CommentServiceRestClient implements CommentService{
    private String url = "http://localhost:8090/api/comment";
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void addComments(Comment comment) {
        restTemplate.postForEntity(url,comment,Comment.class);
    }

    @Override
    public List<Comment> getTopComments() {
        return Arrays.asList(restTemplate.getForEntity(url,Comment[].class).getBody());
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Reset is not supported on web interface");
    }
}
