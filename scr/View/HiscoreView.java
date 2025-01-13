package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.DefaultListModel;
import java.util.List;
import Controller.NewController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HiscoreView extends JPanel {
    private JList<String> hiscores;
    private JFrame hiscoreFrame;

    public HiscoreView(NewController controller) {
        DefaultListModel<String> model = new DefaultListModel<>();
        hiscores = new JList<>(model);
        hiscores.setFont(new Font("Arial", Font.BOLD, 20));
        hiscores.setBackground(new Color(120, 160, 130));
        hiscores.setForeground(Color.white);
        hiscores.setOpaque(true);

        hiscoreFrame = new JFrame("High Scores");
        hiscoreFrame.setSize(400, 500);
        hiscoreFrame.setLocationRelativeTo(null);
        hiscoreFrame.setResizable(false);
        hiscoreFrame.setVisible(true);
        hiscoreFrame.setBackground(Color.WHITE);
        hiscoreFrame.setLayout(new BorderLayout(1, 1));

        JLabel hiscoreLabel = new JLabel("HISCORES", SwingConstants.CENTER);
        hiscoreLabel.setBackground(new Color(80, 120, 120));
        hiscoreLabel.setFont(new Font("Arial", Font.BOLD, 29));
        hiscoreLabel.setForeground(Color.white);
        hiscoreLabel.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(hiscores);
        hiscoreFrame.add(scrollPane, BorderLayout.CENTER);
        hiscoreFrame.add(hiscoreLabel, BorderLayout.NORTH);

        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setBackground(new Color(80, 120, 120));
        closeButton.setForeground(Color.white);
        closeButton.setOpaque(true);
        closeButton.addActionListener(e -> {
            hiscoreFrame.dispose();
            controller.resetGame();
        });

        hiscoreFrame.add(closeButton, BorderLayout.SOUTH);
    }

    public void updateHiscore(List<String> scores) {
        DefaultListModel<String> model = (DefaultListModel<String>) hiscores.getModel();
        model.clear();  // Rensar listan först
        for (String score : scores) {
            model.addElement(score);
        }
    }

    public void showHighScore() {
        hiscoreFrame.setVisible(true);
    }
}
