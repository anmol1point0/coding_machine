package dao;

import javafx.util.Pair;
import model.Player;

import java.util.HashMap;
import java.util.List;

public class GameDao {

    int sizeOfBoard = 0;
    private final HashMap<Integer,Integer> snakePositionsMap;
    private final HashMap<Integer,Integer> ladderPositionsMap;
    private final HashMap<String, Integer> playerPositions;
    private Boolean isGameLive;


    public GameDao(){
        snakePositionsMap = new HashMap<>();
        ladderPositionsMap = new HashMap<>();
        playerPositions = new HashMap<>();
        isGameLive = true;
    }

    public Boolean isGameLive(){
        return isGameLive;
    }

    public Boolean registerBoard(int size){
        sizeOfBoard = size;
        return true;
    }

    public Boolean registerSnakes(List<Pair<Integer, Integer>> snakePositions){
        if(sizeOfBoard == 0){
            System.out.println("Size of board is not initialized");
            return false;
        }
        for (Pair<Integer, Integer> snakePosition : snakePositions) {
            snakePositionsMap.put(snakePosition.getKey(), snakePosition.getValue());
        }
        return true;
    }

    public Boolean registerLadders(List<Pair<Integer, Integer>> ladderPositions){
        if(sizeOfBoard == 0){
            System.out.println("Size of board is not initialized");
            return false;
        }
        for (Pair<Integer, Integer> ladderPosition : ladderPositions) {
            ladderPositionsMap.put(ladderPosition.getValue(), ladderPosition.getKey());
        }
        return true;
    }

    public Boolean initializePlayers(List<Player> registeredPlayers){
        if(sizeOfBoard == 0){
            System.out.println("Size of board is not initialized");
            return false;
        }
        if(registeredPlayers.size() == 0){
            System.out.println("Number of players not initialized");
            return false;
        }

        for (Player registeredPlayer : registeredPlayers) {
            playerPositions.put(registeredPlayer.getPlayerName(), 0);
        }
        return true;
    }

    public void registerUserMove(String playerName, int diceOutput){
        Integer currPosOfUser = playerPositions.get(playerName);

        int initialPosOfUser = currPosOfUser;
        if(currPosOfUser + diceOutput <= sizeOfBoard){
            currPosOfUser += diceOutput;
            if(snakePositionsMap.containsKey(currPosOfUser)){
                currPosOfUser = snakePositionsMap.get(currPosOfUser);
            }
            if(ladderPositionsMap.containsKey(currPosOfUser)){
                currPosOfUser = ladderPositionsMap.get(currPosOfUser);
            }
        }

        System.out.println("Player: " + playerName + " rolled a: " + diceOutput + " and reached from: " + initialPosOfUser + " to: " + currPosOfUser);
        playerPositions.put(playerName, currPosOfUser);

        if(currPosOfUser == 100){
            System.out.println("Player: " + playerName + " wins the Game!");
            isGameLive = false;
        }
    }
}
