package View;

import java.awt.*;

public class TrapFrame extends Frame {
    private int value = -10;
    private Color background= Color.RED;

    public TrapFrame(MainFrame mainFrame, Color color) {
        super(mainFrame, color);
    }

    @Override
    public String getValue(){
        if(!isClicked()) {
            return String.valueOf(value);
        } return "0";
    }

    @Override
    public void reveal() {
        this.setBackground(background);
        this.setText(String.valueOf(value)+"p");
        setClicked(true);
    }
}
