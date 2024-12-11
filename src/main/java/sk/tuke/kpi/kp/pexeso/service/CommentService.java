package sk.tuke.kpi.kp.pexeso.service;

import sk.tuke.kpi.kp.pexeso.entity.Comment;
import java.util.List;

public interface CommentService {
    void addComments(Comment comment);
    List<Comment> getTopComments();
    void reset();
}
