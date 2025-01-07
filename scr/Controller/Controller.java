package Controller;

import View.*;

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

    private void addPoints(int index) {
        int pointsToAdd = 0;
        Frame clickedFrame = mainPanel.getFrame(index); // Få den klickade rutan
        int frameValue = Integer.parseInt(clickedFrame.getValue()); // Få värdet för den klickade rutan
        int treasureNbr = clickedFrame.getPartOfTreasure(); // Få treasureNbr för skatten

        // Kontrollera om det är en TreasureFrame och om den är avslöjad
        if (treasureNbr != -1 && clickedFrame instanceof TreasureFrame) {
            boolean allRevealed = true;

            // Kontrollera om alla delar av skatten är avslöjade
            for (Frame frame : mainPanel.getBoard()) {
                if (frame instanceof TreasureFrame && frame.getPartOfTreasure() == treasureNbr) {
                    if (!frame.isClicked()) { // Om en del av skatten inte är avslöjad
                        allRevealed = false;
                        break;
                    }
                }
            }

            // Om alla delar är avslöjade, addera poäng
            if (allRevealed) {
                // Lägg till poäng för hela skatten
                for (Frame frame : mainPanel.getBoard()) {
                    if (frame instanceof TreasureFrame && frame.getPartOfTreasure() == treasureNbr) {
                        pointsToAdd += Integer.parseInt(frame.getValue()); // Lägg till poäng från alla delar
                    }
                }
                scoreBoard.setGameMessage("Du hittade hela skatten och får " + pointsToAdd + " poäng.");
                if (pointsToAdd == 100) {
                    scoreBoard.setGameMessage("Du hittade en hemlig skatt! Grattis till: " + pointsToAdd + " poäng!");
                }
            }
        } else {
            // Om det inte är en treasure eller den inte är avslöjad, ge feedback
            if (frameValue == 0) {
                scoreBoard.setGameMessage("Miss");
            } else if (frameValue == 5) {
                scoreBoard.setGameMessage("Du träffade en fälla och förlorar en besättningsmedlem");
            } else if (frameValue == 10) {
                scoreBoard.setGameMessage("Du hittade hela skatten");
            }
        }

        // Uppdatera poängen
        scoreBoard.updateScore(pointsToAdd);
        String player1 = "Player 1";
        scoreBoard.setPlayer1Label(String.format("%s %n %s", player1,pointsToAdd)+"p");
    }


    public void setActionListeners() {
        Frame[] frames = mainPanel.getBoard();
        for (int i = 0; i < frames.length; i++) {
            final int index = i;
            frames[i].addActionListener(e -> handleTileClick(index)); // Koppla action till varje ruta
        }
        scoreBoard.getResetButton().addActionListener(e -> handleResetButtonClick());
    }


    public void handleTileClick(int index) {
        Frame frame = mainPanel.getFrame(index);

        if (!frame.isClicked()) {
            mainPanel.revealFrame(frame);
        }
        addPoints(index);

    }


    // Resetknappen i ScoreBoard
    public void handleResetButtonClick() {
        scoreBoard.resetScore(); // Nollställ poängen
        view.resetBoard();       // Skapa ny spelplan
        mainPanel = view.getMainPanel(); // Hämta den nya spelplanen
        setActionListeners();    // Koppla ActionListeners till den nya spelplanen
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
