package View;

import java.awt.*;

public class TreasureFrame extends Frame {
    private int value = 10;
    private Color background = new Color(230, 218, 110);
    private int partOfTreasure;
    private boolean isEpic = false;
    private boolean allRevealed = false;

    public TreasureFrame(NewMainFrame mainFrame, Color color, int partOfTreasure) {
        super(mainFrame, color);
        this.partOfTreasure = partOfTreasure;

    }

    @Override
    public int getPartOfTreasure() {
        return partOfTreasure;
    }

    public void makeEpicLoot() {
        isEpic = true;
        this.background = Color.magenta;
    }


    @Override
    public void reveal() {
        this.setBackground(background);
        this.setText("X");
        setClicked(true);
    }

    public void fullReveal() {
        allRevealed = true;
        if (!isEpic) {
            setValue(10);
            this.setText("S");
            this.setBackground(new Color(230, 130, 80));
            setClicked(true);
        }
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        if (isEpic) {
            setValue(100);
            System.out.println("You found a big hidden treasure");
            return String.valueOf(value);
        }
        if (allRevealed) {
            return String.valueOf(value);
        }
        return "0";
    }
}
