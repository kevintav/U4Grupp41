package View;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private int sizeOfBoard;
    private Frame[] frames = new Frame[sizeOfBoard];

    //TODO den här constructorn ska ta en till parameter med storlek spelbräded(antal knappar)
    public MainPanel(int width, int height, MainFrame mainFrame, int sizeOfBoard) {
        this.sizeOfBoard=sizeOfBoard;
        this.frames = new Frame[sizeOfBoard*sizeOfBoard];
        this.setBackground(Color.WHITE);
        this.setSize(width, height);

        for(int i = 0; i < sizeOfBoard*sizeOfBoard; i++){
            frames[i]= new Frame(100,100, mainFrame, new Color(200-(i*4)/sizeOfBoard,130,130));
        }

        for(int i = 0; i < sizeOfBoard*sizeOfBoard; i++){
            add(frames[i]);
        }
        add(frames[0]);
        add(frames[1]);
        add(frames[2]);
        add(frames[3]);
        add(frames[4]);
        add(frames[5]);
        add(frames[6]);
        add(frames[7]);
        add(frames[8]);


        this.setLayout(new GridLayout(sizeOfBoard,sizeOfBoard, 3, 3));
        this.setVisible(true);


    }
}
