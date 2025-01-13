package View;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {
    private JLabel scoreLabel;
    private int score = 0;
    private JLabel topRightPanel;
    private JLabel topLeftPanel;
    private JLabel gameMessage;
    private JLabel gameDirector;
    private JButton resetButton;

    public ScoreBoard(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(250, 250, 250));

        topLeftPanel = new JLabel("Player 1:   Antal liv: 3   Poäng: 0", SwingConstants.CENTER);
        topLeftPanel.setFont(new Font("Arial", Font.BOLD, 15));
        topLeftPanel.setForeground(Color.WHITE);
        topLeftPanel.setBackground(new Color(100, 150, 150));
        topLeftPanel.setPreferredSize(new Dimension(width / 2, 50));
        topLeftPanel.setOpaque(true);

        scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 15));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(new Color(100, 150, 150));
        scoreLabel.setPreferredSize(new Dimension(width / 2, 50));
        scoreLabel.setOpaque(true);

        topRightPanel = new JLabel("Player 2:   Antal liv: 3   Poäng: 0", SwingConstants.CENTER);
        topRightPanel.setFont(new Font("Arial", Font.BOLD, 15));
        topRightPanel.setForeground(Color.WHITE);
        topRightPanel.setOpaque(true);
        topRightPanel.setBackground(new Color(100, 150, 150));
        topRightPanel.setPreferredSize(new Dimension(width / 2, 50));

        gameMessage = new JLabel("Välkommen", SwingConstants.CENTER);
        gameMessage.setFont(new Font("Arial", Font.BOLD, 15));
        gameMessage.setForeground(Color.WHITE);
        gameMessage.setOpaque(true);
        gameMessage.setBackground(new Color(80, 120, 120));
        gameMessage.setPreferredSize(new Dimension(width / 2, 40));

        gameDirector = new JLabel("", SwingConstants.CENTER);
        gameDirector.setFont(new Font("Arial", Font.BOLD, 15));
        gameDirector.setBackground(new Color(80, 120, 120));
        gameDirector.setForeground(Color.white);
        gameDirector.setOpaque(true);
        gameDirector.setPreferredSize(new Dimension(24, 60));

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 15));
        resetButton.setBackground(new Color(80, 120, 120));
        resetButton.setForeground(Color.white);
        resetButton.setOpaque(true);
        resetButton.setPreferredSize(new Dimension(24, 60));


        this.setLayout(new BorderLayout(1, 1));
        this.add(gameMessage, BorderLayout.SOUTH);
        this.add(topLeftPanel, BorderLayout.WEST);
        this.add(topRightPanel, BorderLayout.EAST);
        this.add(scoreLabel, BorderLayout.CENTER);
        this.add(gameDirector, BorderLayout.NORTH);
        //this.add(resetButton, BorderLayout.WEST);
    }

    public void setGameMessage(String text) {
        gameMessage.setText(text);
    }

    public void setGameDirector(String text) {
        gameDirector.setText(text);
    }

    public void setPlayer1Label(String text) {
        topLeftPanel.setText(text);
    }

    public void setPLayer2Label(String text) {
        topRightPanel.setText(text);
    }


}
