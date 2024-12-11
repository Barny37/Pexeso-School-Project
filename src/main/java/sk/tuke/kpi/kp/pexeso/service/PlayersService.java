package sk.tuke.kpi.kp.pexeso.service;

import sk.tuke.kpi.kp.pexeso.entity.Players;

import java.util.List;

public interface PlayersService {

    void addPlayer(Players players);
    Players getPlayer(String login, String password);
}
