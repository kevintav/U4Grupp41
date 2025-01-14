package View;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {
    private JLabel topRightPanel;
    private JLabel topLeftPanel;
    private JLabel gameMessage;
    private JLabel gameDirector;
    private JButton resetButton;

    public ScoreBoard(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(250, 250, 250));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 3)); // 1 row, 3 columns
        topPanel.setBackground(new Color(100, 150, 150));

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 12));
        resetButton.setBackground(new Color(80, 120, 120));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.setOpaque(true);
        topPanel.add(resetButton);

        topLeftPanel = new JLabel("Player 1:   Antal liv: 3   Poäng: 0", SwingConstants.CENTER);
        topLeftPanel.setFont(new Font("Arial", Font.BOLD, 15));
        topLeftPanel.setForeground(Color.WHITE);
        topLeftPanel.setOpaque(true);
        topLeftPanel.setBackground(new Color(100, 150, 150));
        topPanel.add(topLeftPanel);

        topRightPanel = new JLabel("Player 2:   Antal liv: 3   Poäng: 0", SwingConstants.CENTER);
        topRightPanel.setFont(new Font("Arial", Font.BOLD, 15));
        topRightPanel.setForeground(Color.WHITE);
        topRightPanel.setOpaque(true);
        topRightPanel.setBackground(new Color(100, 150, 150));
        topPanel.add(topRightPanel);

        gameMessage = new JLabel("Välkommen", SwingConstants.CENTER);
        gameMessage.setFont(new Font("Arial", Font.BOLD, 15));
        gameMessage.setForeground(Color.WHITE);
        gameMessage.setOpaque(true);
        gameMessage.setBackground(new Color(80, 120, 120));
        gameMessage.setPreferredSize(new Dimension(width, 40));

        gameDirector = new JLabel("", SwingConstants.CENTER);
        gameDirector.setFont(new Font("Arial", Font.BOLD, 15));
        gameDirector.setBackground(new Color(80, 120, 120));
        gameDirector.setForeground(Color.white);
        gameDirector.setOpaque(true);
        gameDirector.setPreferredSize(new Dimension(width, 40));

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(gameMessage, BorderLayout.SOUTH);
        this.add(gameDirector, BorderLayout.CENTER);
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

    public void setResetButton(JButton resetButton) {
        this.resetButton = resetButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }
}
