package jar1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






import java.awt.Color;

/**
 *
 * @author לוי
 */
public class Win {

    private Turn winer;
    private Turn loser;
    private int winerScoring;
    private int loserScoring;

    public Win(Turn winer, Turn loser, int winerScoring, int loserScoring) {
        this.winer = winer;
        this.loser = loser;
        this.winerScoring = winerScoring;
        this.loserScoring = loserScoring;
    }

    public Win() {

    }

    public Turn getWiner() {
        return winer;
    }

    public void setWiner(Turn winer) {
        this.winer = winer;
    }

    public Turn getLoser() {
        return loser;
    }

    public void setLoser(Turn loser) {
        this.loser = loser;
    }

    public int getWinerScoring() {
        return winerScoring;
    }

    public void setWinerScoring(int winerScoring) {
        this.winerScoring = winerScoring;
    }

    public int getLoserScoring() {
        return loserScoring;
    }

    public void setLoserScoring(int loserScoring) {
        this.loserScoring = loserScoring;
    }

  

    String getResulet() {
        String key = "";
        switch (this.getWiner()) {

            case None:
                key = "it's a tie" + "The score for two is  Scoring=" + loserScoring + '}';
                break;

            default:
                key = "Win{" + "winer=" + winer.getTurnName()+ ", loser=" + loser.getTurnName()+ ", winerScoring=" + winerScoring + ", loserScoring=" + loserScoring + '}';
        }
        return  key ;
    }

    Color getResuletColor() 
    {
        return winer.getColor();
    }

}
