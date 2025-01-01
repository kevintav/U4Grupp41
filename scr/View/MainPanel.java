package View;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private int sizeOfBoard;
    private Frame[] frames;
    private int lastClickedIndex = -1;
    //TODO den här constructorn ska ta en till parameter med storlek spelbräded(antal knappar)
    public MainPanel(int width, int height, MainFrame mainFrame, int sizeOfBoard) {

        this.sizeOfBoard = sizeOfBoard;
        this.frames = new Frame[sizeOfBoard * sizeOfBoard];
        this.setBackground(new Color(70, 30, 30));
        this.setSize(width, height);

        for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) {
            final int index = i;
            frames[i] = new Frame(mainFrame, new Color(200 - (i * 10) / sizeOfBoard, 130, 130));

            /* //TODO den här constructorn kan användas för att visa alla knapparna med index.
            frames[i] = new Frame(mainFrame, new Color(200 - (i * 10) / sizeOfBoard, 130, 130), index);
            */

            frames[i].addActionListener(e -> Controller.getClickedButton(index));
            add(frames[i]);
        }

        this.setLayout(new GridLayout(sizeOfBoard, sizeOfBoard, 3, 3));
        this.setVisible(true);


    }

    public void revealFrame(int index){
        frames[index].reveal();

    }

    public void updateBoard(){
        for(int i = 0; i < frames.length; i++){
            if(frames[i].isClicked()){
                frames[i].reveal();
                System.out.println("testade iallafall");
            }
        }
    }

}
