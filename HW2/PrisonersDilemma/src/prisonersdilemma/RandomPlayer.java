/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prisonersdilemma;
import java.util.Random;

/**
 *
 * @author Elly
 */
public class RandomPlayer implements Player {
//player makes a random choice each turn

    int totalScore=initscore;
    int numwins=initscore;
    
    @Override
    public int makeMove() {
       Random r=new Random();
       if (r.nextBoolean()) return Cooperate;
       else return Defect;
       //nectBoolean randomly returns true or false with roughly equal probability
       //this should randomly return Cooperate and Defect with equal probability
    }

    @Override
    public String name() {
        return "Random";
    }

    @Override
    public void plots(int oppMove, int Score) {
        totalScore=totalScore+Score;
    }

 
}
