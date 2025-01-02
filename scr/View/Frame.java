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
    }

    public void hidePanel(){
        clicked=false;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void reveal() {
        this.setBackground(Color.WHITE); //Denna måste ändras sen till att visa typ ett attribute som är en subklass. (treasure, trap)
        clicked = true;
    }

    public boolean isClicked() {
        return clicked;
    }


    public String getValue() {
    return String.valueOf(0);}

    public void setValue(int value){
    }
}
