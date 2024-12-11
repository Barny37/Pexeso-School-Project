package sk.tuke.kpi.kp.pexeso.service;

import sk.tuke.kpi.kp.pexeso.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class RatingServiceJPA implements RatingService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRatings(Rating rating) {
        entityManager.persist(rating);
    }

    @Override
    public List<Rating> getTopRatings() {
        return (List<Rating>) entityManager.createQuery("select r from Rating r order by r.date DESC ")
                .setMaxResults(5)
                .getResultList();
    }

    @Override
    public void reset() {
     entityManager.createNativeQuery("DELETE FROM rating");
    }
}
