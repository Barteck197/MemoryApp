package game;

import java.util.Comparator;

public class PlayerSorter implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        if (o1.getPlayerResult() == o2.getPlayerResult()) {
            return 0;
        } else if (o1.getPlayerResult() > o2.getPlayerResult()) {
            return -1;
        } else {
            return 1;
        }
    }
}
