package View;

import java.awt.*;

public class TreasureFrame extends Frame{
    private int value= 10;


    public TreasureFrame(MainFrame mainFrame, Color color) {
        super(mainFrame, color);

    }

    @Override
    public void reveal() {
        this.setBackground(Color.ORANGE);
        this.setText(String.valueOf(value)+"p");
        setClicked(true);
    }

    @Override
    public int getValue(){
        return value;
    }
}
