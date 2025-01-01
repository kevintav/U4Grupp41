package Controller;
import View.MainFrame;

public class Controller {
    private MainFrame view;
    public Controller(){

        int sizeOfBoard=10;
        view = new MainFrame(800, 800, this, sizeOfBoard);

    }

    public static void getClickedButton(int index) {
        System.out.println("tryckte på " + index);
    }




}
