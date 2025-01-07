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
        this.setLayout(new BorderLayout(1, 1));
        this.setLocationRelativeTo(null);

        scoreBoard = new ScoreBoard(frameWidth, scoreboardHeight);
        this.add(scoreBoard, BorderLayout.NORTH);

        mainPanel = new MainPanel(boardWidth, boardHeight, this, sizeOfBoard);
        this.add(mainPanel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void resetBoard() {
        this.remove(mainPanel);
        mainPanel = new MainPanel(mainPanel.getWidth(), mainPanel.getHeight(), this, mainPanel.getSizeOfBoard());
        this.add(mainPanel, BorderLayout.CENTER);
        repaint();
        revalidate();
    }


    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}

