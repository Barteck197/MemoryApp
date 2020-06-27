package game;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int playerResult;
    transient static int id;

    public Player(String name, int playerResult) {
        this.name = name;
        this.playerResult = playerResult;
        id = 1;
        id++;
    }

    //getters
    public int getPlayerResult() {
        return playerResult;
    }

    @Override
    public String toString() {
        return id + ";" + name + ";" + playerResult;
    }
}
