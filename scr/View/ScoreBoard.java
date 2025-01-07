package View;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {
    private JLabel scoreLabel;
    private int score = 0;
    private JButton resetButton;
    private JLabel topLeftPanel;
    private JLabel gameMessage;

    public ScoreBoard(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(250, 250, 250));

        topLeftPanel = new JLabel("test",SwingConstants.CENTER);
        topLeftPanel.setFont(new Font("Arial", Font.BOLD, 15));
        topLeftPanel.setForeground(Color.WHITE);
        topLeftPanel.setBackground(new Color(100, 150, 150));
        topLeftPanel.setPreferredSize(new Dimension(width/4, 50));
        topLeftPanel.setOpaque(true);

        scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 15));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(new Color(100, 150, 150));
        scoreLabel.setPreferredSize(new Dimension(width/2, 50));
        scoreLabel.setOpaque(true);

        resetButton = new JButton("Reset Board");
        resetButton.setFont(new Font("Arial", Font.BOLD, 15));
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(100, 150, 150));
        resetButton.setPreferredSize(new Dimension(width/4, 50));

        gameMessage= new JLabel("Välkommen", SwingConstants.CENTER);
        gameMessage.setFont(new Font("Arial", Font.BOLD, 15));
        gameMessage.setForeground(Color.WHITE);
        gameMessage.setOpaque(true);
        gameMessage.setBackground(new Color(80 , 120, 120));
        gameMessage.setPreferredSize(new Dimension(width/2, 50));

        this.setLayout(new BorderLayout(1,1 ));
        this.add(gameMessage, BorderLayout.SOUTH);
        this.add(topLeftPanel, BorderLayout.WEST);
        this.add(resetButton, BorderLayout.EAST);
        this.add(scoreLabel, BorderLayout.CENTER);
    }

    public void updateScore(int points) {
        this.score += points;
        scoreLabel.setText("Score: " + score);
    }

    public void resetScore(){
        score=0;
        scoreLabel.setText("Score: "+ score);
    }

    public String getScore(){
        return String.valueOf(score);
    }

    public void setGameMessage(String text){
        gameMessage.setText(text);
    }

    public JLabel getTopLeftPanel() {
        return topLeftPanel;
    }
    public void setPlayer1Label(String text){
        topLeftPanel.setText(text);
    }

    public JButton getResetButton() {
        return resetButton;
    }
}
