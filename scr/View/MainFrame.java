package View;

import javax.swing.*;
import Controller.Controller;

import java.awt.*;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private ScoreBoard scoreBoard;
    private Controller controller;

    public MainFrame(int boardWidth, int boardHeight, Controller controller, int sizeOfBoard) {
        super("Game");
        this.controller = controller;


        int scoreboardHeight = 100;
        int frameWidth = boardWidth;
        int frameHeight = boardHeight + scoreboardHeight;


        this.setSize(frameWidth, frameHeight);
        this.setResizable(false);
        this.setLayout(new BorderLayout());


        scoreBoard = new ScoreBoard(frameWidth, scoreboardHeight);
        this.add(scoreBoard, BorderLayout.NORTH);


        mainPanel = new MainPanel(boardWidth, boardHeight, this, sizeOfBoard);
        this.add(mainPanel, BorderLayout.CENTER);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void revealFrame(int index) {
        mainPanel.revealFrame(index);
        mainPanel.updateBoard();

        scoreBoard.updateScore(10);
    }
}
