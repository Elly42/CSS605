/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prisonersdilemma;

/**
 *
 * @author Elly
 */
public class OTFTPlayer implements Player{
    int totalScore=initscore;
    int numwins=initscore;
    int oppLastMove=Cooperate;
    
    @Override
    public int makeMove() {
         
            return (oppLastMove);
           
        
    }


    @Override
    public String name() {
        return "Optimistic Tit-for-tat";
    }

    @Override
    public void plots(int oppMove, int Score) {
        totalScore=totalScore+Score;
        oppLastMove=oppMove;
    }
    
}
