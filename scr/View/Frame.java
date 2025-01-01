package View;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Frame extends JButton {
    private boolean clicked=false;
    public Frame(int width, int height, MainFrame mainFrame, Color color) {
        this.setBackground(color);
        this.setVisible(true);
        this.setSize(width, height);

    }

    public void reveal() {
        this.setBackground(Color.BLACK);
        clicked=true;
    }

    public boolean isClicked() {
        return clicked;
    }


}
