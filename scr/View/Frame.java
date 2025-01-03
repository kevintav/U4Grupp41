package View;

import javax.swing.*;
import java.awt.*;

public class Frame extends JButton {
    private boolean clicked = false;


    /**
     * Constructor som används för att visa index på varje knapp.
     */
    public Frame(MainFrame mainFrame, Color color, int index) {
        this.setBackground(color);
        this.setVisible(true);
        this.setText(String.valueOf(index));
    }

    /**
     * Constructor som används för att skapa en ram i MainPanel.
     *
     * @param mainFrame
     * @param color     Author Christoffer Björnheimer
     */
    public Frame(MainFrame mainFrame, Color color) {
        this.setBackground(color);
        this.setVisible(true);
        this.setForeground(Color.WHITE);
        this.setMargin(new Insets(0,0,0,0));
        this.setFont(new Font("Arial", Font.BOLD, 40));
    }

    public void hidePanel(){
        clicked=false;
        this.setBackground(new Color(120, 130, 130));
        this.setText("");

    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void reveal() {
        if(!isClicked()){
            this.setBackground(Color.WHITE);
            this.setForeground(Color.GRAY);
            this.setText(String.valueOf("X"));//Denna måste ändras sen till att visa typ ett attribute som är en subklass. (treasure, trap)

            clicked = true;
        }

    }

    public boolean isClicked() {
        return clicked;
    }


    public String getValue() {
    return String.valueOf(0);}

    public void setValue(int value){
    }

    public void makeEpicLoot() {
    }

    public int getPartOfTreasure() {
    return -1;}

    public void fullReveal() {

    }
}
