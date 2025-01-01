package View;

import javax.swing.*;
import Controller.Controller;
public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private Controller controller;

    public MainFrame(int width, int height, Controller controller) {
        super("Bakery");
        this.controller = controller;
        this.setResizable(false);
        this.setSize(width, height);
        this.mainPanel = new MainPanel(width, height, this);
        this.setContentPane(mainPanel);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}