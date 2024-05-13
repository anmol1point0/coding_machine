package model;

import statics.IdGenerator;

public class Player {

    public Player(String playerName){
        this.name = playerName;
        this.playerId = IdGenerator.generateId();
    }

    public String getPlayerName(){
        return this.name;
    }

    private String name;
    private Integer playerId;
}
