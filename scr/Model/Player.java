package Model;

public class Player {
    private int crewMembers = 3;
    private int score;

    public Player(){
    }

    public int getCrewMembers() {
        return crewMembers;
    }

    public void killCrewMember() {
        this.crewMembers--;
    }
    public void setScore(int score){
        this.score += score;
    }

    public int getScore(){
        return score;
    }

    public void addToScore(int points) {
        this.score += points;
    }

}
