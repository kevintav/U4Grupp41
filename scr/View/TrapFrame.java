package View;

import java.awt.*;

public class TrapFrame extends Frame {
    private int pointPenalty = 10;
    private Color background= Color.RED;

    public TrapFrame(MainFrame mainFrame, Color color, int index) {
        super(mainFrame, color, index);
    }

    public int getPointPenalty() {
        return pointPenalty;
    }
}
