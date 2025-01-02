package View;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {
    private JLabel scoreLabel;
    private int score = 1337;

    public ScoreBoard(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(50, 80, 80));


        scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);


        this.setLayout(new BorderLayout());
        this.add(scoreLabel, BorderLayout.CENTER);
    }

    public void updateScore(int points) {

        this.score += points;
        scoreLabel.setText("Score: " + score);
    }
}
