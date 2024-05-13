package dao;

import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerDao {

    HashMap<String, Player> registeredPlayer;

    public PlayerDao(){
        registeredPlayer = new HashMap<>();
    }

    public Player registerPlayer(String name){
        if(registeredPlayer.containsKey(name)){
            System.out.println("Already registered a player: " + name);
            return null;
        }
        Player player = new Player(name);
        registeredPlayer.put(name, player);
        return player;
    }

    public List<Player> getRegisteredPlayers(){
        return new ArrayList<>(registeredPlayer.values());
    }
}
