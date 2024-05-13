package service;

import dao.PlayerDao;
import model.Player;

public class PlayerService {

    public final PlayerDao playerDao;

    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public Player registerPlayer(String name){
        return playerDao.registerPlayer(name);
    }
}
