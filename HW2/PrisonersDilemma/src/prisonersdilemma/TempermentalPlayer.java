/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prisonersdilemma;

/**
 *
 * @author Elly
 */
public class TempermentalPlayer implements Player {
    //Player will cooperate until the first time that the opponent defects
    // from then on the player will defect
    
    int totalScore=initscore;
    int OppLastMove=nomove;
    int test=0;

    @Override
    public int makeMove() {
        
        if (OppLastMove==1) test =1;
        else if (OppLastMove==nomove) test=0;
        if (test<1){               
        return Cooperate;       
        } else return Defect;
    }

    @Override
    public void plots(int oppMove, int Score) {
        totalScore=totalScore+Score;
        OppLastMove=oppMove;
    }

    @Override
    public String name() {
        return "Tempermental Player";
    }
    
}
