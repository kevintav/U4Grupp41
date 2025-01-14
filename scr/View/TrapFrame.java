package View;

import java.awt.*;

public class TrapFrame extends Frame {
    private int value = 5;
    private Color background = Color.RED;
    private String penalty;

    public TrapFrame(NewMainFrame mainFrame, Color color, String penalty) {
        super(mainFrame, color);
        this.penalty = penalty;
    }

    public String getPenalty() {
        return penalty;
    }

    @Override
    public String getValue() {
        if (!isClicked()) {
            return String.valueOf(value);
        }
        return String.valueOf(value);
    }

    @Override
    public void reveal() {
        this.setBackground(background);
        this.setText("X");
        setClicked(true);
    }
}
