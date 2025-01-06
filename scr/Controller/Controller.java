package Controller;

import View.Frame;
import View.MainFrame;
import View.MainPanel;
import View.ScoreBoard;
import java.awt.event.ActionListener;

public class Controller {
    private MainFrame view;
    private ScoreBoard scoreBoard;
    private MainPanel mainPanel;

    public Controller() {
        int sizeOfBoard = 10; // Kan inte sättas högre än 20
        view = new MainFrame(800, 800, this, sizeOfBoard);
        scoreBoard = view.getScoreBoard();
        mainPanel = view.getMainPanel();

        setActionListeners();
    }


    public void setActionListeners() {
        Frame[] frames = mainPanel.getBoard();
        for (int i = 0; i < frames.length; i++) {
            final int index = i;
            frames[i].addActionListener(e -> handleTileClick(index)); // Koppla action till varje ruta
        }
        scoreBoard.getStartButton().addActionListener(e -> handleStartButtonClick());
        scoreBoard.getResetButton().addActionListener(e -> handleResetButtonClick());
    }


    public void handleTileClick(int index) {
        Frame frame = mainPanel.getFrame(index);

        if (!frame.isClicked()) {
            mainPanel.revealFrame(frame);
            int points = Integer.parseInt(frame.getValue());
            scoreBoard.updateScore(points);
                scoreBoard.setGameMessage("Du fick " + points + " Poäng");

        }
    }


    public void handleStartButtonClick() {
        scoreBoard.setGameMessage("Spelet har startat! Lycka till!");
    }

    // Resetknappen i ScoreBoard
    public void handleResetButtonClick() {
        removeActionListeners();
        scoreBoard.resetScore();
        view.resetBoard();
        mainPanel = view.getMainPanel();
        setActionListeners();
        scoreBoard.setGameMessage("Poängen har nollställts. Börja om!");
    }
    public void removeActionListeners() {
        for (Frame frame : mainPanel.getBoard()) {
            if (frame != null) {
                for (ActionListener al : frame.getActionListeners()) {
                    frame.removeActionListener(al);
                }
            }
        }
    }


    public void showHiscore() {
        // Här kan du lägga till kod för att visa högsta poäng
    }
}
