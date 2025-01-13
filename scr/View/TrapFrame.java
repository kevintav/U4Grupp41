package View;

import java.awt.*;
import java.util.Random;

public class TrapFrame extends Frame {
    private int value = 5;
    private Color background = Color.RED;
    private String penalty;

    public TrapFrame(NewMainFrame mainFrame, Color color) {
        super(mainFrame, color);
        Random random = new Random();

        //Generate random penalty
        int choice = random.nextInt(3);
        switch (choice) {
            case 0:
                this.penalty = "killCrew";
                break;
            case 1:
                this.penalty = "stealPoints";
                break;
            case 2:
                this.penalty = "losePoints";
                break;
        }
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
