package View;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class NewMainPanel extends JPanel {
    private int sizeOfBoard;
    private Frame[] frames;
    private NewMainFrame mainFrame;
    private int treasureCount = 0; // Räknar antalet placerade skatter


    public NewMainPanel(int width, int height, NewMainFrame mainFrame, int sizeOfBoard, String[] indexes) {
        this.mainFrame = mainFrame;
        this.sizeOfBoard = sizeOfBoard;
        this.frames = new Frame[sizeOfBoard * sizeOfBoard];
        setBoard(indexes);


        this.setBackground(Color.white);
        this.setSize(width, height);

        this.setLayout(new GridLayout(sizeOfBoard, sizeOfBoard, 1, 1));
        this.setVisible(true);
    }

    /**
     * Genererar ett spelbräde där den först och främst slumpar fram positioner där skatter ska placeras, sedan tar den
     * kvarvarande platser och slumpar ut fällor och epicloot. Efter allt detta fylls hela brädet med tommar frames.
     *
     * @ Author Christoffer Björnheimer
     */
    private void setBoard(String[] indexes) {
        for(int i=0; i<20; i++){
            System.out.println(indexes[i]);
        }


        this.frames=new Frame[indexes.length];
        for(int i = 0; i<indexes.length; i++){
           if(Objects.equals(indexes[i], "Trap")){
                frames[i]=new TrapFrame(mainFrame, new Color(220, 160, 130));
            } else if(Objects.equals(indexes[i], "Treasure")){
                frames[i] = new TreasureFrame(mainFrame, new Color(120, 160, 130), treasureCount);
            } else if(Objects.equals(indexes[i], "Epic")){
                frames[i] = new TreasureFrame(mainFrame, new Color(120, 160, 130), treasureCount);
                frames[i].makeEpicLoot();
            }  if(Objects.equals(indexes[i], "Empty")){
                frames[i] = new Frame(mainFrame,new Color(120, 160, 130));
            }

        }
        for (int i =0 ; i < frames.length; i++) {
            frames[i].setBackground(new Color(230 - i * 3 / 2, 160, 130));
            add(frames[i]);
        }
    }

    public void revealFrame(Frame click) {
        click.reveal();
        // Om det är en TreasureFrame, kontrollera om alla delar av skatten har avslöjats.
        if (click instanceof TreasureFrame) {
            int treasureNbr = click.getPartOfTreasure();
            checkAndRevealTreasure(treasureNbr);
        }
    }

    public void revealAllFrames(){
        for(int i = 0; i<frames.length; i++){
            frames[i].reveal();
        }
    }

    private void checkAndRevealTreasure(int treasureNbr) {
        for (Frame frame : frames) {
            if (frame instanceof TreasureFrame && frame.getPartOfTreasure() == treasureNbr) {
                if (!frame.isClicked()) {
                    return;
                }
            }
        }
        for (Frame frame : frames) {
            if (frame instanceof TreasureFrame && frame.getPartOfTreasure() == treasureNbr) {
                frame.fullReveal();
            }
        }
    }

    public Frame getFrame(int index) {
        return frames[index];
    }

    public Frame[] getBoard() {
        return frames;
    }

    /**
     * Har som syfte att skapa en form på skatter, den kontrolleras sedan om den valda formen kommer få plats på brädet
     * genom att checka så att inget av indexarna hamnar utanför brädet.
     * @param type typen av form som ska läggas till.
     * @param sizeOfBoard Storleken på brädet, för att kunna kontrollera om formen får plats.
     * @param startPosition Startposition för formen, ifrån denna utgår formen.
     * @return en int-array med alla positioner som ska användas, dessa hämtas sedan av generateBoard och placeras ut
     * på samma index som int-arrayen säger. Om en position inte går att skapa returneras alla ints som -1.
     * @author Christoffer Björnheimer
     */




}
