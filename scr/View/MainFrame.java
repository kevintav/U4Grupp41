package View;

import javax.swing.*;
import Controller.Controller;
public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private Controller controller;

    public MainFrame(int width, int height, Controller controller, int sizeOfBoard) {
        super("Game");
        this.controller = controller;
        this.setResizable(false);
        this.setSize(width, height);
        this.mainPanel = new MainPanel(width, height, this, sizeOfBoard);
        this.setContentPane(mainPanel);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}