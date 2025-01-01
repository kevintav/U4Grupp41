package View;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private Frame[] frames = new Frame[25];
    private Frame next;

    //TODO den här constructorn ska ta en till parameter med storlek spelbräded(antal knappar)
    public MainPanel(int width, int height, MainFrame mainFrame) {
        int sizeOfBoard = 5;

        this.setBackground(Color.WHITE);
        this.setSize(width, height);

        for(int i = 0; i < sizeOfBoard*sizeOfBoard; i++){
            frames[i]= new Frame(100,100, mainFrame, new Color(200-(i*2),130,130));
        }


        /*
        //TODO Skapa en loop som skapar lika många frames som man valt till spelet.
        frames[0] = new Frame(100, 100, mainFrame, Color.BLACK);
        frames[1] = new Frame(100, 100, mainFrame, Color.WHITE);
        frames[2] = new Frame(100, 100, mainFrame, Color.RED);
        frames[3] = new Frame(100, 100, mainFrame, Color.GREEN);
        frames[4] = new Frame(100, 100, mainFrame, Color.BLUE);
        frames[5] = new Frame(100, 100, mainFrame, Color.ORANGE);
        frames[6] = new Frame(100, 100, mainFrame, Color.CYAN);
        frames[7] = new Frame(100, 100, mainFrame, Color.MAGENTA);
        frames[8] = new Frame(100, 100, mainFrame, Color.LIGHT_GRAY);
        */
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
