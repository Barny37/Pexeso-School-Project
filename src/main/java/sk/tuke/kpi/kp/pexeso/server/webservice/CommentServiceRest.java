package sk.tuke.kpi.kp.pexeso.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.kpi.kp.pexeso.entity.Comment;
import sk.tuke.kpi.kp.pexeso.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentServiceRest {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getTopComment(){return commentService.getTopComments();}

    @PostMapping
    public void addComment(@RequestBody Comment comment){commentService.addComments(comment);}
}
