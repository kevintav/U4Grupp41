package View;

import java.awt.*;

public class SurpriseFrame extends Frame {
    private String effect;

    public SurpriseFrame(NewMainFrame mainFrame, String effect) {
        super(mainFrame, Color.BLUE);
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    public void reveal() {
        this.setBackground(Color.CYAN);
        this.setText("Ö");
        setClicked(true);
    }
}