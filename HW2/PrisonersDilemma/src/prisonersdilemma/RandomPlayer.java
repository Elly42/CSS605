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
       return (r.nextInt(2)); // returns a random integer 0 or 1
     
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
