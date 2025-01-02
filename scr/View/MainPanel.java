package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MainPanel extends JPanel {
    private int sizeOfBoard;
    private Frame[] frames;
    private int lastClickedIndex = -1;
    private ScoreBoard scoreBoard;
    private MainFrame mainFrame;

    public MainPanel(int width, int height, MainFrame mainFrame, int sizeOfBoard) {
        this.mainFrame = mainFrame;
        this.sizeOfBoard = sizeOfBoard;
        this.frames = new Frame[sizeOfBoard * sizeOfBoard];
        this.setBackground(Color.white);
        this.setSize(width, height);


        for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) {
            Random randomize = new Random();

            int random = randomize.nextInt(100);
            final int index = i;
            if (random >= 50 && random <= 70) {
                frames[i] = new TreasureFrame(mainFrame, new Color(200 - (i * 10) / sizeOfBoard, 130, 130));
            } else if (random >= 10 && random <= 20) {
                frames[i] = new TrapFrame(mainFrame, new Color(200 - (i * 10) / sizeOfBoard, 130, 130));

            } else if (random==33) {
                frames[i] = new TreasureFrame(mainFrame, new Color(200 - (i * 10) / sizeOfBoard, 130, 130));
                frames[i].setValue(100);

            } else {
                frames[i] = new Frame(mainFrame, new Color(200 - (i * 10) / sizeOfBoard, 130, 130));
            }

            frames[i].addActionListener(e -> {
                mainFrame.updateLastClickedIndex(index);


                revealFrame(index);
                updateBoard();
            });

            add(frames[i]);
        }

        this.setLayout(new GridLayout(sizeOfBoard, sizeOfBoard, 1, 1));
        this.setVisible(true);
    }


    public void resetBoard() {
        for (Frame frame : frames) {
            frame.hidePanel();
        }
    }


    public void revealFrame(int index) {
        if (!frames[index].isClicked()) {
            frames[index].reveal();
            System.out.println("Points gained:" + frames[index].getValue());
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
    public Frame getFrame(int index){
        return frames[index];
    }
}
