package View;

import javax.swing.*;
import Controller.Controller;

import java.awt.*;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private ScoreBoard scoreBoard;
    private Controller controller;
    private int lastClickedIndex;

    public MainFrame(int boardWidth, int boardHeight, Controller controller, int sizeOfBoard) {
        super("Game");
        this.controller = controller;

        int scoreboardHeight = 150;
        int frameWidth = boardWidth;
        int frameHeight = boardHeight + scoreboardHeight;

        this.setSize(frameWidth, frameHeight);
        this.setResizable(false);
        this.setLayout(new BorderLayout(1,1));

        scoreBoard = new ScoreBoard(frameWidth, scoreboardHeight);
        this.add(scoreBoard, BorderLayout.NORTH);

        mainPanel = new MainPanel(boardWidth, boardHeight, this, sizeOfBoard);
        this.add(mainPanel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    //TODO det här ska nog ligga i antingen model eller i controller. den hanterar poäng. men jag har implementerat ett system för att få poängen
    public void updateLastClickedIndex(int newIndex) {
        this.lastClickedIndex = newIndex;
        if(!mainPanel.getFrame(lastClickedIndex).isClicked()){
            if(mainPanel.getFrame(lastClickedIndex).getValue().equals("100")){
                scoreBoard.setGameMessage("DU HITTADE EN HEMLIG SKATT OCH FICK: " + mainPanel.getFrame(lastClickedIndex).getValue() + ", GRATTIS!");
                scoreBoard.updateScore(Integer.parseInt(mainPanel.getFrame(lastClickedIndex).getValue()));
            } else{
                scoreBoard.setGameMessage("Du fick " + mainPanel.getFrame(lastClickedIndex).getValue() + " Poäng");
                scoreBoard.updateScore(Integer.parseInt(mainPanel.getFrame(lastClickedIndex).getValue()));
            }

        }
    }

    public int getLastClickedIndex() {
        return lastClickedIndex;
    }

    public int getMainPanelSize(){
        return mainPanel.getSizeOfBoard();
    }
}

