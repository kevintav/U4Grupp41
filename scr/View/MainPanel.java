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

        while (treasureCount < (sizeOfBoard * sizeOfBoard) / 10) {
            int randomIndex = randomize.nextInt(sizeOfBoard * sizeOfBoard);
            int randomTreasure = randomize.nextInt(5);

            // Hämta de beräknade positionerna för den slumpade skatten
            int[] shapeIndexes = treasureShape(randomTreasure, sizeOfBoard, randomIndex);

            // Kontrollera om placeringen är giltig
            if (isValidPlacement(randomIndex, randomTreasure)) {
                for (int k : shapeIndexes) {
                    if (k != -1 && k < frames.length) { // Placera endast på giltiga positioner
                        frames[k] = new TreasureFrame(mainFrame, new Color(120, 160, 130), treasureCount);
                    }
                }
                treasureCount++; // Öka räknaren endast om placeringen är giltig
            }
        }

        // Skapa fällor
        int traps=0;
        for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) {
            int randomPlace = randomize.nextInt(100);
            if (randomPlace >= 85 && frames[i] == null && traps<5) {
                frames[i] = new TrapFrame(mainFrame, new Color(120, 160, 130));
                traps++;
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

    public void revealAllFrames(){
        for(int i = 0; i<frames.length; i++){
            frames[i].reveal();
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
                frame.fullReveal();
            }
        }
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

    /**
     * Har som syfte att skapa en form på skatter, den kontrolleras sedan om den valda formen kommer få plats på brädet
     * genom att checka så att inget av indexarna hamnar utanför brädet.
     * @param type typen av form som ska läggas till.
     * @param sizeOfBoard Storleken på brädet, för att kunna kontrollera om formen får plats.
     * @param startPosition Startposition för formen, ifrån denna utgår formen.
     * @return en int-array med alla positioner som ska användas, dessa hämtas sedan av generateBoard och placeras ut
     * på samma index som int-arrayen säger. Om en position inte går att skapa returneras alla ints som -1.
     * @author Christoffer Björnheimer
     */
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

                if (positions[1] < 0 || positions[2] < 0 || positions[3] < 0 || positions[4] < 0 ||
                        positions[1] >= sizeOfBoard * sizeOfBoard || positions[2] >= sizeOfBoard * sizeOfBoard ||
                        positions[3] >= sizeOfBoard * sizeOfBoard || positions[4] >= sizeOfBoard * sizeOfBoard) {
                    isValid = false;
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
                positions[2] = startPosition - 1;
                positions[3] = -1;
                positions[4] = -1;

                if (startPosition % sizeOfBoard == sizeOfBoard - 1 ||
                        startPosition % sizeOfBoard == 0 || //
                        positions[1] >= sizeOfBoard * sizeOfBoard ||
                        positions[2] < 0 || positions[2] >= sizeOfBoard * sizeOfBoard) {
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


                if (startPosition % sizeOfBoard == sizeOfBoard - 1 ||
                        positions[3] >= sizeOfBoard * sizeOfBoard ||
                        positions[2] >= sizeOfBoard * sizeOfBoard ||
                        startPosition / sizeOfBoard == sizeOfBoard - 1 ||
                        (startPosition + 1) / sizeOfBoard == sizeOfBoard - 1) {
                    isValid = false;
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

    /**
     * Metod för att kolla om formens alla indexar är tomma.
     * @param startIndex indexet som formen utgår ifrån
     * @param shape typen av shape som ska kontrolleras
     * @return en boolean som returnerar false ifall något index som ingår i den specifika Shapen är upptaget
     * @author Christoffer Björnheimer
     */
    public boolean isValidPlacement(int startIndex, int shape) {
        if (shape == -1) {
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
                if (startIndex % sizeOfBoard == sizeOfBoard - 1 || // Sista kolumnen (kan inte gå åt höger)
                        startIndex + 2 >= sizeOfBoard * sizeOfBoard || // Index + 2 går utanför brädet
                        frames[startIndex] != null || // Kontrollera om startIndex är upptagen
                        frames[startIndex + 1] != null || // Kontrollera om startIndex + 1 är upptagen
                        frames[startIndex + 2] != null) { // Kontrollera om startIndex + 2 är upptagen
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
