package View;

import javax.swing.*;

import Controller.NewController;

import java.awt.*;

public class NewMainFrame extends JFrame {
    private NewMainPanel mainPanel;
    private ScoreBoard scoreBoard;
    private NewController controller;


    public NewMainFrame(int boardWidth, int boardHeight, NewController controller, int sizeOfBoard, String[] indexes) {
        super("Game");
        this.controller = controller;

        int scoreboardHeight = 150;
        int frameHeight = boardHeight + scoreboardHeight;

        this.setSize(boardWidth, frameHeight);
        this.setResizable(false);
        this.setLayout(new BorderLayout(1, 1));
        this.setLocationRelativeTo(null);

        scoreBoard = new ScoreBoard(boardWidth, scoreboardHeight);
        this.add(scoreBoard, BorderLayout.NORTH);

        mainPanel = new NewMainPanel(controller, boardWidth, boardHeight, this, sizeOfBoard, indexes);
        this.add(mainPanel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public NewMainPanel getMainPanel() {
        return mainPanel;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}

