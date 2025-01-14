package View;

import java.awt.*;

public class SurpriseFrame extends Frame {
    private String effect;

    public SurpriseFrame(NewMainFrame mainFrame) {
        super(mainFrame, Color.BLUE);
    }

    public void reveal() {
        this.setBackground(Color.CYAN);
        this.setText("Ö");
        setClicked(true);
    }
}