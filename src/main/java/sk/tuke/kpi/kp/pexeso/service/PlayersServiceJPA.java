package sk.tuke.kpi.kp.pexeso.service;

import sk.tuke.kpi.kp.pexeso.entity.Players;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Transactional
public class PlayersServiceJPA implements PlayersService{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addPlayer(Players players) {
        entityManager.persist(players);
    }

    @Override
    public Players getPlayer(String login, String password) {
        Players players = new Players(null,null);
        try {
            players= (Players) entityManager.createQuery("SELECT p FROM Players p WHERE p.logins =:login AND  p.passwords =:password")
                    .setParameter("login",login)
                    .setParameter("password",password)
                    .getSingleResult();
        }catch(Exception e) {
         return players;
        }
        return players;
    }
}
