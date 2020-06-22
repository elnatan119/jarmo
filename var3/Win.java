/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var3;

/**
 *
 * @author לוי
 */
public class Win 
{
    Turn winer;
    Turn loser;
    int winerScoring;
     int loserScoring;
    public Win(Turn winer, Turn loser, int winerScoring, int loserScoring) {
        this.winer = winer;
        this.loser = loser;
        this.winerScoring = winerScoring;
        this.loserScoring = loserScoring;
    }

    public Win() 
    {
        
    }

    public Turn getWiner() 
    {
        return winer;
    }

    public void setWiner(Turn winer) 
    {
        this.winer = winer;
    }

    public Turn getLoser() 
    {
        return loser;
    }

    public void setLoser(Turn loser) 
    {
        this.loser = loser;
    }

    public int getWinerScoring() 
    {
        return winerScoring;
    }

    public void setWinerScoring(int winerScoring) 
    {
        this.winerScoring = winerScoring;
    }

    public int getLoserScoring() 
    {
        return loserScoring;
    }

    public void setLoserScoring(int loserScoring) 
    {
        this.loserScoring = loserScoring;
    }

    @Override
    public String toString() 
    {
        return "Win{" + "winer=" + winer + ", loser=" + loser + ", winerScoring=" + winerScoring + ", loserScoring=" + loserScoring + '}';
    }
    
            
}
