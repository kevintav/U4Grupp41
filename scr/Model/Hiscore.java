package Model;


//TODO Hanterar skrivandet och sparande till en fil
public class Hiscore {
    public String[] hiscore;

    public void updateHiscores(String[] hiscore) {
        this.hiscore = hiscore;
    }

    public String[] getHiscores() {
        return hiscore;
    }

    public void setHiscore(String[] hiscore) {
        this.hiscore = hiscore;
    }
}
