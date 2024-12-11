package sk.tuke.kpi.kp.pexeso.service;

import sk.tuke.kpi.kp.pexeso.entity.Score;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ScoreServiceJPA implements ScoreService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addScore(Score score) {
        entityManager.persist(score);
    }

    @Override
    public List<Score> getTopScore() {
        return (List<Score>) entityManager.createQuery("select s from Score s order by s.score DESC ")
                .setMaxResults(5)
                .getResultList();
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("DELETE FROM score");
    }
}
