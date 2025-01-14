package Controller;

import Model.*;
import View.*;
import View.Frame;

public class NewController {
    private NewMainFrame view;
    private ScoreBoard scoreBoard;
    private NewMainPanel mainPanel;
    private Player activePlayer;
    private BoardManager boardManager;
    private Hiscore Hiscore;
    private HiscoreView HiscoreView;
    private Player player1;
    private Player player2;
    private String player1Name;
    private String player2Name;

    public NewController() {
        int sizeOfBoard = 10;

        this.boardManager = new BoardManager(sizeOfBoard);
        this.Hiscore = new Hiscore(10);
        Hiscore.loadHighScoreFile("highscores.txt");

        view = new NewMainFrame(800, 800, this, sizeOfBoard, boardManager.getFrames());
        scoreBoard = view.getScoreBoard();
        mainPanel = view.getMainPanel();

        scoreBoard.getResetButton().addActionListener(e -> {
            resetGame();
        });
        setActionListeners();
        startGame();
    }

    public void startGame() {
        player1Name = view.getPlayer1Name();
        player2Name = view.getPlayer2Name();
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        activePlayer = player1;
        scoreBoard.setGameDirector("Välkommen, "+player1Name+", gör ditt drag");
    }

    public void changePlayer() {
        if (activePlayer == player1) {
            activePlayer = player2;
            scoreBoard.setGameDirector(player2Name+", gör ditt drag");
        } else {
            activePlayer = player1;
            scoreBoard.setGameDirector(player1Name+", gör ditt drag");
        }
        scoreBoard.setPlayer1Label(String.format(player1Name+":   Antal liv: %s   Poäng: %s", player1.getCrewMembers(), player1.getScore()));
        scoreBoard.setPLayer2Label(String.format(player2Name+":   Antal liv: %s   Poäng: %s", player2.getCrewMembers(), player2.getScore()));
    }

    private void updateScoreBoard() {
        scoreBoard.setPlayer1Label(String.format(player1Name+":   Antal liv: %s   Poäng: %s", player1.getCrewMembers(), player1.getScore()));
        scoreBoard.setPLayer2Label(String.format(player2Name+":   Antal liv: %s   Poäng: %s", player2.getCrewMembers(), player2.getScore()));
    }

    public int getTreasureNbr(int index) {
        int[] plopp = boardManager.getTreasureIndexes();
        return plopp[index];
    }

    private void addPoints(int index) {
        int pointsToAdd = 0;
        Frame clickedFrame = mainPanel.getFrame(index);
        int frameValue = Integer.parseInt(clickedFrame.getValue());
        int treasureNbr = clickedFrame.getPartOfTreasure();

        if (treasureNbr != -1 && clickedFrame instanceof TreasureFrame) {
            boolean allRevealed = true;

            // Kontrollera om alla delar av skatten är avslöjade
            for (Frame frame : mainPanel.getFrames()) {
                if (frame instanceof TreasureFrame && frame.getPartOfTreasure() == treasureNbr && !frame.isClicked()) {
                    allRevealed = false;
                    break;
                }
            }

            if (allRevealed) {
                // Lägg till poäng för hela skatten
                for (Frame frame : mainPanel.getFrames()) {
                    if (frame instanceof TreasureFrame && frame.getPartOfTreasure() == treasureNbr) {
                        pointsToAdd += Integer.parseInt(frame.getValue());
                    }
                }
                scoreBoard.setGameMessage("Du hittade hela skatten och får " + pointsToAdd + " poäng.");
                if (pointsToAdd == 100) {
                    scoreBoard.setGameMessage("Du hittade en hemlig skatt! Grattis till: " + pointsToAdd + " poäng!");
                }
            } else {
                // Lägg till endast poäng för den enskilda delen av skatten
                pointsToAdd += frameValue;
                scoreBoard.setGameMessage("Du hittade en del av en skatt! Fortsätt leta efter resten.");
            }
        } else {
            // Hantera andra typer av rutor
            if (frameValue == 0) {
                scoreBoard.setGameMessage("Miss");
            }
        }

        // Uppdatera poängen för den aktiva spelaren
        activePlayer.addToScore(pointsToAdd); // Förutsätter en `addToScore`-metod
        updateScoreBoard();
    }


    public void setActionListeners() {
        Frame[] frames = mainPanel.getFrames();
        for (int i = 0; i < frames.length; i++) {
            final int index = i;
            frames[i].addActionListener(e -> handleFrameClick(index));
        }
    }

    public void handleFrameClick(int index) {
        Frame frame = mainPanel.getFrame(index);
        if (!frame.isClicked()) {
            if (frame instanceof TrapFrame trap) {
                handleTrap(trap.getPenalty());
            } else if (frame instanceof SurpriseFrame surprise) {
                activePlayer.addCrewMember();
            }

            mainPanel.revealFrame(frame);
            addPoints(index);
            if (allFramesRevealed()) {
                endGame(); //Ends game if all frames are revealed
            } else if (player1.getCrewMembers() <= 0 || player2.getCrewMembers() <= 0) {
                endGame();
            } else {
                changePlayer();
            }
        }
    }

    private boolean allFramesRevealed() {
        for (Frame frame : mainPanel.getFrames()) {
            if (!frame.isClicked()) {
                return false;
            }
        }
        return true;
    }

    private void handleTrap(String penalty) {
        switch (penalty) {
            case "KILL_CREW":
                activePlayer.killCrewMember();
                scoreBoard.setGameMessage(activePlayer.getName()+" lost a crew member");
                break;
            case "STEAL_POINTS":
                int stolenPoints = activePlayer.getScore() / 10;
                activePlayer.addToScore(-stolenPoints);
                getOpponentPlayer().addToScore(stolenPoints);
                scoreBoard.setGameMessage(getOpponentPlayer().getName()+" stole "+stolenPoints+" from "+activePlayer.getName());
                break;
            case "LOSE_POINTS":
                if (activePlayer.getScore() -30 >= 0) {
                    activePlayer.addToScore(-30);
                    scoreBoard.setGameMessage(activePlayer.getName()+" lost 30 points");
                } else {
                    scoreBoard.setGameMessage(activePlayer.getName()+" lost "+activePlayer.getScore()+" points");
                    activePlayer.addToScore(-activePlayer.getScore());
                }
                break;
        }
        updateScoreBoard();
    }

    public void endGame() {
        scoreBoard.setGameDirector("Spelet är över!");
        mainPanel.revealAllFrames();

        Player Winner = determineTheWinner();
        if (Winner != null && Winner.getScore() > 0) {
            Hiscore.addScore(Winner.getName(), Winner.getScore());
            Hiscore.saveHighScoreFile("highscores.txt");
        }
        showHighScore();
    }

    public void resetGame() {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        activePlayer = player1;

        if (view != null) {
            view.dispose();  // Stänger, sparar mycket datorkraft märkte jag.
        }

        int sizeOfBoard = 10;
        boardManager = new BoardManager(sizeOfBoard);
        view = new NewMainFrame(800, 800, this, sizeOfBoard, boardManager.getFrames());
        scoreBoard = view.getScoreBoard();
        mainPanel = view.getMainPanel();

        scoreBoard.setGameDirector("Välkommen, "+player1Name+", gör ditt drag");
        scoreBoard.getResetButton().addActionListener(e -> resetGame());
        setActionListeners();
    }

    public Player determineTheWinner() {
        if (player1.getCrewMembers() >= 0) {
            return player1;
        } else {
            return player2;
        }
    }

    public void showHighScore() {
        if (HiscoreView == null) {
            HiscoreView = new HiscoreView(this);
        }
        HiscoreView.updateHiscore(Hiscore.getHiscores());
        HiscoreView.showHighScore();
    }

    public Player getOpponentPlayer() {
        return (activePlayer == player1) ? player2 : player1;
    }
}
