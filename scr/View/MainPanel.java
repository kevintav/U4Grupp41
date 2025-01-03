package View;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MainPanel extends JPanel {
    private int sizeOfBoard;
    private Frame[] frames;
    private int lastClickedIndex = -1;
    private MainFrame mainFrame;

    public MainPanel(int width, int height, MainFrame mainFrame, int sizeOfBoard) {
        this.mainFrame = mainFrame;
        this.sizeOfBoard = sizeOfBoard;
        this.frames = new Frame[sizeOfBoard * sizeOfBoard];
        this.setBackground(Color.white);
        this.setSize(width, height);
        int treasureNbr = 1;

        for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) {
            Random randomize = new Random();
            int randomPlace = randomize.nextInt(100);
            int randomTreasure = randomize.nextInt(4);
            final int index = i;

            if (randomPlace <= 20) {
                int[] placeEm = treasureShape(randomTreasure, sizeOfBoard, i);
                if (frames[i] == null && placeEm.length > 0 && isValidPlacement(placeEm)) {
                    for (int k : placeEm) {
                        if (k != -1 && k < frames.length) {
                            frames[k] = new TreasureFrame(mainFrame, new Color(120, 130, 130), treasureNbr);
                            //frames[k].setText(String.valueOf(treasureNbr + "-" + randomTreasure));
                        }
                    }
                }
                treasureNbr++;
            }

            if (frames[i] == null) {
                frames[i] = new Frame(mainFrame, new Color(120, 130, 130));
            }

            if (frames[i] != null) {
                frames[i].addActionListener(e -> {
                    mainFrame.updateLastClickedIndex(index);
                    revealFrame(index);
                    updateBoard();
                });
            }

            add(frames[i]);
        }

        this.setLayout(new GridLayout(sizeOfBoard, sizeOfBoard, 1, 1));
        this.setVisible(true);
    }

    public void resetBoard() {
        for (Frame frame : frames) {
            frame.hidePanel();
        }
    }

    public int[] treasureShape(int type, int sizeOfBoard, int startPosition) {
        int[] positions = new int[5];
        boolean isValid = true;

        if (type == 0) {
            positions[0] = startPosition;
            positions[1] = startPosition + 1;
            positions[2] = startPosition + sizeOfBoard;
            positions[3] = startPosition + sizeOfBoard + 1;
            positions[4] = -1;

            if (startPosition % sizeOfBoard == sizeOfBoard - 1 || positions[3] >= sizeOfBoard * sizeOfBoard) {
                isValid = false;
            }
        } else if (type == 1) { // STAR(suger)
            positions[0] = startPosition;
            positions[1] = startPosition + sizeOfBoard - 1;
            positions[2] = startPosition + sizeOfBoard;
            positions[3] = startPosition + sizeOfBoard + 1;
            positions[4] = startPosition + 2 * sizeOfBoard;

            if (positions[1] >= sizeOfBoard * sizeOfBoard || positions[1] < 0 ||
                    positions[2] >= sizeOfBoard * sizeOfBoard || positions[2] < 0 ||
                    positions[3] >= sizeOfBoard * sizeOfBoard || positions[3] < 0 ||
                    positions[4] >= sizeOfBoard * sizeOfBoard || positions[4] < 0) {
                isValid = false;
            }

            if ((startPosition % sizeOfBoard == 0 || startPosition % sizeOfBoard == sizeOfBoard - 1) ||
                    (startPosition + sizeOfBoard - 1) % sizeOfBoard == sizeOfBoard - 1 ||
                    (startPosition + sizeOfBoard) / sizeOfBoard == sizeOfBoard - 1) {
                isValid = false;
            }

        } else if (type == 2) {
            positions[0] = startPosition;
            positions[1] = startPosition + 1;
            positions[2] = -1;
            positions[3] = -1;
            positions[4] = -1;

            if (startPosition % sizeOfBoard == sizeOfBoard - 1) {
                isValid = false;
            }
        } else if (type == 3) {
            positions[0] = startPosition;
            positions[1] = -1;
            positions[2] = -1;
            positions[3] = -1;
            positions[4] = -1;
        }

        return isValid ? positions : new int[0];
    }

    public void checkIfRevealed(int treasureNbr) {
        boolean allClicked = true;
        for (int i = 0; i < frames.length; i++) {
            if (treasureNbr == frames[i].getPartOfTreasure()) {
                if (!frames[i].isClicked()) {
                    allClicked = false;
                }
            }
        }
        if (allClicked) {
            for (int i = 0; i < frames.length; i++) {
                if (treasureNbr == frames[i].getPartOfTreasure()) {
                    frames[i].fullReveal();
                }
            }
        }

    }

    public boolean isValidPlacement(int[] positions) {
        for (int pos : positions) {
            if (pos != -1 && (pos >= frames.length || frames[pos] != null)) {
                return false;
            }
        }
        return true;
    }

    public void revealFrame(int index) {
        System.out.println("Points gained:" + frames[index].getValue());
        frames[index].reveal();

    }

    public void updateBoard() {
        for (int i = 0; i < frames.length; i++) {
            if (frames[i].isClicked()) {
                frames[i].reveal();
                checkIfRevealed(frames[i].getPartOfTreasure());
            }
        }

        int check = 0;
        for (int i = 0; i < frames.length; i++) {
            if (frames[i].isClicked()) check++;
        } if (check==frames.length) resetBoard();

    }

    public Frame getFrame(int index) {
        return frames[index];
    }

    public int getSizeOfBoard() {
        return sizeOfBoard;
    }
}
