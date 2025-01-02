package Model;


//TODO Hanterar skrivandet och sparande till en fil
public class Hiscores {
    public String[] hiscores;


    public void updateHiscores(String[] hiscore) {
        this.hiscores = hiscore;
    }

    public String[] getHiscores() {
        return hiscores;
    }
}
