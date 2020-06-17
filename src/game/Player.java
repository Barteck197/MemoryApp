package game;

public class Player {
    private String name;
    private int playerResult;
    static int id;

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

    public String getName() {
        return name;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerResult(int playerResult) {
        this.playerResult = playerResult;
    }

    @Override
    public String toString() {
        return id + ";" + name + ";" + playerResult;
    }
}
