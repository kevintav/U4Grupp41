package Model;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.*;

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

    public void saveHighScoreFile(String highscores) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(highscores))) {
            for (String entry : hiscores) {
                writer.println(entry);
            }
        } catch (IOException e) {
            System.err.println("Error saving highscore: "+e.getMessage());
        }
    }

    public void loadHighScoreFile(String highscores) {
        hiscores.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(highscores))) {
            String line;
            while ((line = reader.readLine()) != null) {
                hiscores.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Highscore file not found");
        } catch (IOException e) {
            System.err.println("Error loading highscore: "+e.getMessage());
        }
    }
}
