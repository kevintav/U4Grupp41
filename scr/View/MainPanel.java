package View;

import javax.swing.*;
import java.awt.*;

import java.util.Arrays;
import java.util.Random;

public class MainPanel extends JPanel {
    private int sizeOfBoard;
    private Frame[] frames;
    private MainFrame mainFrame;
    private int score = 0;
    private int treasureCount = 0; // Räknar antalet placerade skatter
    // För att hålla reda på unika treasureNbr

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

    /**
     * Genererar ett spelbräde där den först och främst slumpar fram positioner där skatter ska placeras, sedan tar den
     * kvarvarande platser och slumpar ut fällor och epicloot. Efter allt detta fylls hela brädet med tommar frames.
     *
     * @ Author Christoffer Björnheimer
     */
    private void generateBoard() {
        Random randomize = new Random();

        // Skapa en boolean-array för att hålla reda på valda positioner
        boolean[] selectedPositions = new boolean[sizeOfBoard * sizeOfBoard];

        while (treasureCount < (sizeOfBoard * sizeOfBoard) / 10) {
            int randomIndex = randomize.nextInt(sizeOfBoard * sizeOfBoard);
            int randomTreasure = randomize.nextInt(5);

            // Hämta de beräknade positionerna för den slumpade skatten
            int[] shapeIndexes = treasureShape(randomTreasure, sizeOfBoard, randomIndex);

            // Kontrollera om placeringen är giltig
            if (isValidPlacement(randomIndex, randomTreasure)) {
                for (int k : shapeIndexes) {
                    if (k != -1 && k < frames.length) { // Placera endast på giltiga positioner
                        frames[k] = new TreasureFrame(mainFrame, new Color(180, 160, 130), treasureCount);
                    }
                }
                treasureCount++; // Öka räknaren endast om placeringen är giltig
            }
        }


        // Skapa fällor
        for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) {
            int randomPlace = randomize.nextInt(100);
            if (randomPlace >= 85 && frames[i] == null) {
                frames[i] = new TrapFrame(mainFrame, new Color(020, 160, 130));
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

        // Skapa tomma rutor
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
        for (Frame frame : frames) {
            if (frame instanceof TreasureFrame && frame.getPartOfTreasure() == treasureNbr) {
                if (!frame.isClicked()) {
                    return;
                }
            }
        }
        for (Frame frame : frames) {
            if (frame instanceof TreasureFrame && frame.getPartOfTreasure() == treasureNbr) {
                ((TreasureFrame) frame).fullReveal();
            }
        }
        addPoints(treasureNbr);
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

            case 0: // SMALL SQUARE
                positions[0] = startPosition;
                positions[1] = -1;
                positions[2] = -1;
                positions[3] = -1;
                positions[4] = -1;
                break;

            case 1: // STAR
                positions[0] = startPosition;                       // Mittpunkten (centralt)
                positions[1] = startPosition - 1;                   // Vänster
                positions[2] = startPosition + 1;                   // Höger
                positions[3] = startPosition - sizeOfBoard;         // Ovanför
                positions[4] = startPosition + sizeOfBoard;         // Nedanför

// Kontrollera om någon av positionerna är utanför brädet eller om de är upptagna
                if (positions[1] < 0 || positions[2] < 0 || positions[3] < 0 || positions[4] < 0 ||
                        positions[1] >= sizeOfBoard * sizeOfBoard || positions[2] >= sizeOfBoard * sizeOfBoard ||
                        positions[3] >= sizeOfBoard * sizeOfBoard || positions[4] >= sizeOfBoard * sizeOfBoard) {
                    isValid = false;  // Om någon position är utanför brädet, gör det ogiltigt
                }
                break;

            case 2: // 2LINE
                positions[0] = startPosition;
                positions[1] = startPosition + 1;
                positions[2] = -1;
                positions[3] = -1;
                positions[4] = -1;


                if (startPosition % sizeOfBoard == sizeOfBoard - 1 || positions[1] >= sizeOfBoard * sizeOfBoard) {
                    isValid = false;
                }
                break;

            case 3: // 3LINE
                positions[0] = startPosition;
                positions[1] = startPosition + 1;
                positions[2] = startPosition + 2;
                positions[3] = -1;
                positions[4] = -1;

                if (startPosition % sizeOfBoard == sizeOfBoard - 1 ||
                        positions[1] >= sizeOfBoard * sizeOfBoard || positions[2] >= sizeOfBoard * sizeOfBoard) {
                    isValid = false;
                } else if ((startPosition + 2) / sizeOfBoard == (sizeOfBoard - 2) && (startPosition % sizeOfBoard < sizeOfBoard - 2)) {
                    isValid = true;
                }
                break;

            case 4: // SQUARE
                positions[0] = startPosition;
                positions[1] = startPosition + 1;
                positions[2] = startPosition + sizeOfBoard;
                positions[3] = startPosition + sizeOfBoard + 1;
                positions[4] = -1;

                // Kontrollera om startPosition är på sista kolumnen eller på sista raden
                if (startPosition % sizeOfBoard == sizeOfBoard - 1 || // Om startPosition är på sista kolumnen
                        positions[3] >= sizeOfBoard * sizeOfBoard ||    // Om den sista positionen är utanför brädet
                        positions[2] >= sizeOfBoard * sizeOfBoard ||    // Om den andra positionen är utanför brädet
                        startPosition / sizeOfBoard == sizeOfBoard - 1 || // Om startPosition är på sista raden
                        (startPosition + 1) / sizeOfBoard == sizeOfBoard - 1) { // Om nästa position är på sista raden
                    isValid = false;  // Ogiltig om någon position går utanför brädet
                }
                break;
            default:
                isValid = false;
                break;
        }
        //Returnerar array med ogiltiga värden om den inte kan skapas
        if (!isValid) {
            Arrays.fill(positions, -1);
        }
        return positions;
    }


    public boolean isValidPlacement(int startIndex, int shape) {
        if (shape == -1) {
            return false;
        }

        // Kontrollera att startIndex inte är utanför gränserna
        if (startIndex < 0 || startIndex >= frames.length) {
            return false;
        }

        switch (shape) {
            case 0: // Enkel treasure
                if (frames[startIndex] != null) {
                    return false;
                }
                break;

            case 1: // Star
                // Kontrollera att ingen position för Star är utanför gränserna
                if (startIndex % sizeOfBoard == 0 || startIndex % sizeOfBoard == sizeOfBoard - 1 ||
                        startIndex - sizeOfBoard < 0 || startIndex + sizeOfBoard >= frames.length ||
                        frames[startIndex] != null ||
                        frames[startIndex - 1] != null ||
                        frames[startIndex + 1] != null ||
                        frames[startIndex + sizeOfBoard] != null ||
                        frames[startIndex - sizeOfBoard] != null) {
                    return false;
                }
                break;

            case 2: // 2line
                if (startIndex % sizeOfBoard == sizeOfBoard - 1 ||
                        frames[startIndex] != null || frames[startIndex + 1] != null) {
                    return false;
                }
                break;

            case 3: // 3line
                if (startIndex % sizeOfBoard == sizeOfBoard - 1 ||
                        frames[startIndex] != null || frames[startIndex + 1] != null || frames[startIndex + 2] != null) {
                    return false;
                }
                break;

            case 4: // Square
                if (startIndex % sizeOfBoard == sizeOfBoard - 1 ||
                        startIndex + 1 >= frames.length ||
                        startIndex + sizeOfBoard >= frames.length ||
                        startIndex + sizeOfBoard + 1 >= frames.length ||
                        frames[startIndex] != null ||
                        frames[startIndex + 1] != null ||
                        frames[startIndex + sizeOfBoard] != null ||
                        frames[startIndex + sizeOfBoard + 1] != null) {
                    return false;
                }
                break;
        }

        return true;
    }

}
