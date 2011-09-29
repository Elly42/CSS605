/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prisonersdilemma;

/**
 *
 * @author Elly
 */
public class PTFTPlayer implements Player {
    // player defects then responds with opponent's last move

    int oppLastMove=Defect;
    int totalScore=initscore;
    int numwins=initscore;
    @Override
    public int makeMove() {
      
            return (oppLastMove);
    
    }


    @Override
    public String name() {
        return "Pessimistic Tit-for-tat";
    }

    @Override
    public void plots(int oppMove, int Score) {
        oppLastMove=oppMove;
        totalScore=totalScore+Score;
    }
    
}
