package View;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {
    private JLabel scoreLabel;
    private int score = 1337;
    private JButton resetButton;
    private JButton startButton;

    public ScoreBoard(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(50, 80, 80));

        startButton = new JButton("Cool Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 15));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(100, 150, 150));


        scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 15));
        scoreLabel.setForeground(Color.WHITE);

        resetButton = new JButton("Reset Board");
        resetButton.setFont(new Font("Arial", Font.BOLD, 15));
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(100, 150, 150));

        this.setLayout(new BorderLayout(1,1 ));

        this.add(startButton, BorderLayout.WEST);
        this.add(resetButton, BorderLayout.EAST);
        this.add(scoreLabel, BorderLayout.CENTER);
    }

    public void updateScore(int points) {
        this.score += points;
        scoreLabel.setText("Score: " + score);
    }
}
