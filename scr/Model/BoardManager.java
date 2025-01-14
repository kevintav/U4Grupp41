package Model;

import java.util.Arrays;
import java.util.Random;

public class BoardManager {
    private int sizeOfBoard;
    private int treasureCount;
    private String[] frames;
    private int[] treasureIndexes;

    public BoardManager(int sizeOfBoard) {
        this.sizeOfBoard = sizeOfBoard;
        this.frames = new String[sizeOfBoard * sizeOfBoard];
        generateBoard();


    }

    public String[] getFrames() {
        return frames;
    }

    public int[] getTreasureIndexes() {
        return treasureIndexes;
    }

    private void generateBoard() {
        Random randomize = new Random();
        treasureIndexes = new int[frames.length];

        while (treasureCount < (sizeOfBoard * sizeOfBoard) / 10) {
            int randomIndex = randomize.nextInt(sizeOfBoard * sizeOfBoard);
            int randomTreasure = randomize.nextInt(5);

            // Hämta de beräknade positionerna för den slumpade skatten
            int[] shapeIndexes = treasureShape(randomTreasure, sizeOfBoard, randomIndex);

            // Kontrollera om placeringen är giltig
            if (isValidPlacement(randomIndex, randomTreasure)) {
                for (int k : shapeIndexes) {
                    if (k != -1 && k < frames.length) { // Placera endast på giltiga positioner
                        frames[k] = "Treasure";
                        treasureIndexes[k] = treasureCount;
                    }
                }
                treasureCount++; // Öka räknaren endast om placeringen är giltig
            }
        }

        // Skapa fällor
        int traps = 0;
        while (traps < 3) {
            for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) {
                int randomPlace = randomize.nextInt(sizeOfBoard * sizeOfBoard);
                if (randomPlace >= 85 && frames[i] == null && traps < 9) {
                    TrapType trapType = TrapType.values()[randomize.nextInt(TrapType.values().length)];
                    frames[i] = "Trap:"+trapType.name();
                    traps++;
                }
            }
        }

        // skapar överraskningar med slumpmässig effekt
        int surprises = 0;
        while (surprises < 3) { // Limit to 3 surprises
            int randomIndex = randomize.nextInt(sizeOfBoard * sizeOfBoard);
            if (frames[randomIndex] == null) {
                frames[randomIndex] = "Surprise";
                surprises++;
            }
        }

        // Skapa epic loot
        for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) {
            int randomPlace = randomize.nextInt(100);
            if (randomPlace == 50 && frames[i] == null) {
                frames[i] = "Epic";
            }
        }

        // Skapa tomma rutor
        for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) {
            if (frames[i] == null) {
                frames[i] = "Empty";
            }
        }
    }

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
}
