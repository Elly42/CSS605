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

    int oppLastMove=nomove;
    int totalScore=initscore;
    int numwins=initscore;
    @Override
    public int makeMove() {
        if (oppLastMove>=0){      
            return (oppLastMove);
        } else return (Defect);
    
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
