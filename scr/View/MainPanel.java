package View;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private int sizeOfBoard;
    private Frame[] frames;
    private int lastClickedIndex = -1;

    public MainPanel(int width, int height, MainFrame mainFrame, int sizeOfBoard) {
        this.sizeOfBoard = sizeOfBoard;
        this.frames = new Frame[sizeOfBoard * sizeOfBoard];
        this.setBackground(new Color(250, 250, 250));
        this.setSize(width, height);

        for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) {
            final int index = i;
            frames[i] = new Frame(mainFrame, new Color(200 - (i * 10) / sizeOfBoard, 130, 130));


            frames[i].addActionListener(e -> {
                mainFrame.updateLastClickedIndex(index);
                System.out.println("Knapp tryckt, index: " + index);

                revealFrame(index);
                updateBoard();
            });

            add(frames[i]);
        }

        this.setLayout(new GridLayout(sizeOfBoard, sizeOfBoard, 3, 3));
        this.setVisible(true);
    }

    public int getLastClickedIndex() {
        return lastClickedIndex;
    }


    public void revealFrame(int index) {
        // Om rutan inte redan är öppnad, avslöja den
        if (!frames[index].isClicked()) {
            frames[index].reveal();
        }
    }

    public void updateBoard() {
        // Uppdatera hela brädet genom att kontrollera varje ruta
        for (int i = 0; i < frames.length; i++) {
            if (frames[i].isClicked()) {
                frames[i].reveal(); // Om rutan är klickad, avslöja den
            }
        }
    }
}
