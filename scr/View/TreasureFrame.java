package View;

import java.awt.*;

public class TreasureFrame extends Frame {
    private int value = 10;
    private Color background = new Color(230, 218, 110);
    private int partOfTreasure;
    private boolean isEpic = false;
    private int numberOfPieces;
    private boolean allRevealed=false;

    public TreasureFrame(MainFrame mainFrame, Color color, int partOfTreasure) {
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
        allRevealed=true;
        if (!isEpic){
            this.setText("S");
            this.setBackground(Color.ORANGE);
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
        else{
            return String.valueOf(value);
        }
    }
    public void setNumberOfPieces(int nbr){
        numberOfPieces=nbr;
    }

}
