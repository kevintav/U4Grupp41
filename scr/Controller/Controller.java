package Controller;
import View.MainFrame;
import javax.swing.*;
public class Controller {
    private final MainFrame view;

    public Controller(){
        view = new MainFrame(1000, 500, this);
    }
}