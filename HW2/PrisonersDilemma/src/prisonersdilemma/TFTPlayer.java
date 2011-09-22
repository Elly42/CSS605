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
public class TFTPlayer implements Player {
  /* player's first move is random
     * player responds with the opponent's previous move
     */
   
    int oppLastMove=nomove;
    int totalScore=initscore;
    int numwins=initscore;

    @Override
    public int makeMove() {
        if (oppLastMove>-1){
            return (oppLastMove);
            
        } else {
            Random r=new Random ();
            return (r.nextInt(2));
        }
        
    }

    @Override
    public String name() {
        return "Tit-for-tat";
    }

    @Override
    public void plots(int oppMove, int Score) {
    oppLastMove= oppMove;
    totalScore=totalScore+Score;
    }

    
}
