package View;

import javax.swing.*;
import java.awt.*;

public class Frame extends JPanel {
    public Frame(int width, int height, MainFrame mainFrame, Color color) {
        this.setBackground(color);
        this.setVisible(true);
        this.setSize(width, height);

    }

}
