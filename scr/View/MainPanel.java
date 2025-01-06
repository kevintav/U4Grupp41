package View;

import javax.swing.*;
import java.awt.*;

import java.util.Random;

public class MainPanel extends JPanel {
    private int sizeOfBoard;
    private Frame[] frames;
    private MainFrame mainFrame;
    private int score = 0;
    private int treasureCount  = 0; // Räknar antalet placerade skatter
    private int nextTreasureNbr = 1;  // För att hålla reda på unika treasureNbr

    public MainPanel(int width, int height, MainFrame mainFrame, int sizeOfBoard) {
        this.mainFrame = mainFrame;
        this.sizeOfBoard = sizeOfBoard;
        this.frames = new Frame[sizeOfBoard * sizeOfBoard];
        this.setBackground(Color.white);
        this.setSize(width, height);


        // Generera en ny spelplan med Treasure och Trap
        generateBoard();

        this.setLayout(new GridLayout(sizeOfBoard, sizeOfBoard, 1, 1));
        this.setVisible(true);
    }

    private void generateBoard() {
        Random randomize = new Random();

        // Skapa en boolean-array för att hålla reda på valda positioner
        boolean[] selectedPositions = new boolean[sizeOfBoard * sizeOfBoard];

        // Placera ut 20 skatter på slumpade positioner
        for (int count = 0; count < 20; count++) {
            int randomIndex;
            do {
                randomIndex = randomize.nextInt(sizeOfBoard * sizeOfBoard);  // Slumpa en position
            } while (selectedPositions[randomIndex]); // Om positionen redan är vald, försök igen

            selectedPositions[randomIndex] = true;  // Markera denna position som vald

            int randomTreasure = randomize.nextInt(5); // Slumpa vilken typ av skatt
            int[] placeEm = treasureShape(randomTreasure, sizeOfBoard, randomIndex);

            if (frames[randomIndex] == null && placeEm.length > 0 && isValidPlacement(placeEm)) {
                treasureCount++;
                for (int k : placeEm) {
                    if (k != -1 && k < frames.length) {
                        frames[k] = new TreasureFrame(mainFrame, new Color(220, 160, 130), nextTreasureNbr);
                        nextTreasureNbr++;
                    }
                }
            }
        }

        // Skapa fällor med högre sannolikhet
        for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) {
            int randomPlace = randomize.nextInt(100);
            if (randomPlace >= 85 && frames[i] == null) {
                frames[i] = new TrapFrame(mainFrame, new Color(120, 160, 130));
            }
        }

        // Skapa epic loot
        for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) {
            int randomPlace = randomize.nextInt(100);
            if (randomPlace == 50 && frames[i] == null) {
                frames[i] = new TreasureFrame(mainFrame, new Color(120, 160, 130), treasureCount);
                frames[i].makeEpicLoot();
            }
        }

        // Skapa tomma rutor om ingen skatt har lagts
        for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) {
            if (frames[i] == null) {
                frames[i] = new Frame(mainFrame, new Color(120, 160, 130));
            }
            add(frames[i]);
        }
    }



    public void revealFrame(Frame click) {
        click.reveal();
        // Om det är en TreasureFrame, kontrollera om alla delar av skatten har avslöjats.
        if (click instanceof TreasureFrame) {
            int treasureNbr = click.getPartOfTreasure();
            checkAndRevealTreasure(treasureNbr);
        }
    }

    private void checkAndRevealTreasure(int treasureNbr) {
        boolean allRevealed = true;

        for (Frame frame : frames) {
            if (frame instanceof TreasureFrame && frame.getPartOfTreasure() == treasureNbr) {
                if (!frame.isClicked()) {
                    allRevealed = false;
                    break;
                }
            }
        }

        if (allRevealed) {
            for (Frame frame : frames) {
                if (frame instanceof TreasureFrame && frame.getPartOfTreasure() == treasureNbr) {
                    ((TreasureFrame) frame).fullReveal();
                }
            }

            // Tilldela poäng för skatten
            addPoints(treasureNbr);
        }
    }

    private void addPoints(int treasureNbr) {
        int pointsToAdd = 0;

        for (Frame frame : frames) {
            if (frame instanceof TreasureFrame && frame.getPartOfTreasure() == treasureNbr) {
                pointsToAdd += Integer.parseInt(frame.getValue());
            }
        }

        score += pointsToAdd;
        System.out.println("Score: " + score);
    }

    public Frame getFrame(int index) {
        return frames[index];
    }

    public int getSizeOfBoard() {
        return sizeOfBoard;
    }

    public Frame[] getBoard() {
        return frames;
    }

    public int[] treasureShape(int type, int sizeOfBoard, int startPosition) {
        int[] positions = new int[5];
        boolean isValid = true;

        switch (type) {
            case 4: // SQUARE
                positions[0] = startPosition;
                positions[1] = startPosition + 1;
                positions[2] = startPosition + sizeOfBoard;
                positions[3] = startPosition + sizeOfBoard + 1;

                // Kontrollera om någon position går utanför brädet eller bryter rad
                if (startPosition % sizeOfBoard == sizeOfBoard - 1 || // Om startPosition är på sista kolumnen
                        positions[1] % sizeOfBoard == 0 ||               // Om position 1 är på början av rad
                        positions[3] >= sizeOfBoard * sizeOfBoard ||      // Om position 3 är utanför brädet
                        positions[2] >= sizeOfBoard * sizeOfBoard) {      // Om position 2 är utanför brädet
                    isValid = false;
                }
                break;

            case 1: // STAR
                positions[0] = startPosition;
                positions[1] = startPosition + sizeOfBoard - 1;
                positions[2] = startPosition + sizeOfBoard;
                positions[3] = startPosition + sizeOfBoard + 1;
                positions[4] = startPosition + 2 * sizeOfBoard;

                // Kontrollera att ingen position är utanför brädet
                if (positions[1] >= sizeOfBoard * sizeOfBoard || positions[2] >= sizeOfBoard * sizeOfBoard || positions[3] >= sizeOfBoard * sizeOfBoard || positions[4] >= sizeOfBoard * sizeOfBoard) {
                    isValid = false;
                }

                // Kontrollera att ingen position är på kanten
                if (startPosition % sizeOfBoard == 0 || startPosition % sizeOfBoard == sizeOfBoard - 1 || (startPosition + sizeOfBoard - 1) % sizeOfBoard == sizeOfBoard - 1 || (startPosition + sizeOfBoard) / sizeOfBoard == sizeOfBoard - 1) {
                    isValid = false;
                }
                break;

            case 2: // 2LINE
                positions[0] = startPosition;
                positions[1] = startPosition + 1;
                positions[2] = -1;
                positions[3] = -1;
                positions[4] = -1;

                // Kontrollera att position 1 inte ligger på brädets kant
                if (startPosition % sizeOfBoard == sizeOfBoard - 1 || positions[1] >= sizeOfBoard * sizeOfBoard) {
                    isValid = false;
                }
                break;

            case 0: // SMALL SQUARE
                positions[0] = startPosition;
                positions[1] = -1;
                positions[2] = -1;
                positions[3] = -1;
                positions[4] = -1;
                break;

            case 3: // 3LINE
                positions[0] = startPosition;
                positions[1] = startPosition + 1;
                positions[2] = startPosition + 2;
                positions[3] = -1;
                positions[4] = -1;

                // Kontrollera att alla tre positionerna är giltiga och inte ligger på kanten
                if (startPosition % sizeOfBoard == sizeOfBoard - 1 || // Om startPosition är på sista kolumnen
                        positions[1] >= sizeOfBoard * sizeOfBoard || positions[2] >= sizeOfBoard * sizeOfBoard) {
                    isValid = false;
                }
                break;

            default:
                isValid = false;
                break;
        }

        // Returnera de giltiga positionerna eller en tom array
        return isValid ? positions : new int[0];
    }


    public boolean isValidPlacement(int[] positions) {
        for (int pos : positions) {
            if (pos < 0 || pos >= frames.length || frames[pos] != null) {
                return false;
            }
        }
        return true;
    }
}
