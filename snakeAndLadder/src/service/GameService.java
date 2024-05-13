package service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dao.GameDao;
import dao.PlayerDao;
import javafx.util.Pair;
import model.Player;
import utils.GameUtils;

import java.util.List;
import java.util.stream.Collectors;

public class GameService {

    private final GameDao gameDao;
    private final PlayerDao playerDao;
    private final GameUtils gameUtils;

    public GameService(GameDao gameDao,
                       PlayerDao playerDao,
                       GameUtils gameUtils) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
        this.gameUtils = gameUtils;
    }

    public Boolean registerBoard(int boardSize){
        return gameDao.registerBoard(boardSize);
    }

    public Boolean registerSnakes(List<Pair<Integer, Integer>> snakePositions){
        return gameDao.registerSnakes(snakePositions);
    }

    public Boolean registerLadders(List<Pair<Integer, Integer>> ladderPositions){
        return gameDao.registerLadders(ladderPositions);
    }

    public List<String> startGame(){
        List<Player> registeredPlayers = playerDao.getRegisteredPlayers();
        Boolean isGameInitialized = gameDao.initializePlayers(registeredPlayers);
        if(isGameInitialized.equals(Boolean.FALSE))
            return null;
        return registeredPlayers.stream().map(Player::getPlayerName).collect(Collectors.toList());
    }

    public Boolean isGameLive(){
        return gameDao.isGameLive();
    }

    public void diceRolled(String playerName){
        int diceNumber = gameUtils.getRandomNumberUsingNextInt(1,6);
        gameDao.registerUserMove(playerName, diceNumber);
    }
}
