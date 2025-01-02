package View;

import java.awt.*;

public class TreasureFrame extends Frame{
    private int value= 10;
    private Color background= Color.orange;


    public TreasureFrame(MainFrame mainFrame, Color color) {
        super(mainFrame, color);

    }



    @Override
    public void reveal() {
        this.setBackground(background);
        this.setText(String.valueOf(value)+"p");

        setClicked(true);
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String getValue(){
        if(value==100){
            System.out.println("You found a big hidden treasure");
        }
        return String.valueOf(value);
    }
}
