package View;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Frame extends JButton {
    private boolean clicked=false;

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
     * @param mainFrame
     * @param color
     * Author Christoffer Björnheimer
     */
    public Frame(MainFrame mainFrame, Color color) {
        this.setBackground(color);
        this.setVisible(true);


    }

    public void reveal() {
        this.setBackground(Color.BLACK);
        clicked=true;
    }

    public boolean isClicked() {
        return clicked;
    }


}
