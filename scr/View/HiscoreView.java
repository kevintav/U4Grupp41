package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HiscoreView extends JPanel {
    private JList<String> hiscores;
    private JFrame hiscoreFrame;
    private boolean clicked;

    public HiscoreView(){

        DefaultListModel<String> model = new DefaultListModel<>();
        hiscores = new JList<>(model);
        hiscores.setFont(new Font("Arial", Font.BOLD, 20));
        hiscores.setBackground(new Color(120, 160, 130));
        hiscores.setForeground(Color.white);
        hiscores.setOpaque(true);
        hiscores.setVisible(true);

        hiscoreFrame = new JFrame();

        hiscoreFrame.setSize(400, 500);
        hiscoreFrame.setLocationRelativeTo(null);
        hiscoreFrame.setResizable(false);
        hiscoreFrame.setVisible(true);
        hiscoreFrame.setBackground(Color.WHITE);
        hiscoreFrame.setLayout(new BorderLayout(1, 1));

        JLabel hiscore = new JLabel("HISCORES", SwingConstants.CENTER);

        hiscore.setBackground(new Color(80 , 120, 120));
        hiscore.setFont(new Font("Arial", Font.BOLD, 29));
        hiscore.setForeground(Color.white);
        hiscore.setOpaque(true);


        JScrollPane scrollPane = new JScrollPane(hiscores);
        hiscoreFrame.add(scrollPane, BorderLayout.CENTER);

        hiscoreFrame.add(hiscore, BorderLayout.NORTH);


        JButton okButton = new JButton("Okej");
        okButton.setFont(new Font("Arial", Font.BOLD, 16));
        okButton.setBackground(new Color(80, 120, 120));
        okButton.setForeground(Color.white);
        okButton.setOpaque(true);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hiscoreFrame.dispose();
            }
        });


        hiscoreFrame.add(okButton, BorderLayout.SOUTH);


        String[][] test = {
                {"Stefan", "1200"},
                {"Jeff", "300"}
        };

        updateHiscore(test);
    }


    public void updateHiscore(String[][] newList){
        DefaultListModel<String> model = (DefaultListModel<String>) hiscores.getModel();
        model.clear();  // Rensar listan först
        for (String[] entry : newList) {
            model.addElement(entry[0] + " - " + entry[1]);
        }
    }
    public boolean ok(){

    return clicked;}
}
