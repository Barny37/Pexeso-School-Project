package sk.tuke.kpi.kp.pexeso.service;

import sk.tuke.kpi.kp.pexeso.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CommentServiceJPA implements CommentService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addComments(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public List<Comment> getTopComments() {
        return (List<Comment>) entityManager.createQuery("select c from Comment c order by c.date DESC ")
                .setMaxResults(5)
                .getResultList();
    }

    @Override
    public void reset() {
    entityManager.createNativeQuery("DELETE FROM comment");
    }
}
