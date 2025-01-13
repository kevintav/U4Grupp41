package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Hiscore {
    private final List<String> hiscores = new ArrayList<>();
    private final int maxEntries;

    public Hiscore(int maxEntries) {
        this.maxEntries = maxEntries;
    }

    public void addScore(String playerName, int score) {
        String entry = playerName + " - " + score;
        hiscores.add(entry);
        hiscores.sort(Comparator.comparingInt(newEntry -> -Integer.parseInt(newEntry.split(" - ")[1])));
        if (hiscores.size() > maxEntries) {
            hiscores.remove(hiscores.size() - 1);
        }
    }

    public List<String> getHiscores() {
        return new ArrayList<>(hiscores);
    }
}
