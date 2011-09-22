/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prisonersdilemma;

/**
 *
 * @author Elly
 */
public class CooperatePlayer implements Player {

   // player always cooperates
    int totalScore=initscore;
    int numwins=initscore;
           
    @Override
    public int makeMove() {
        return 0;
   
    }

    @Override
    public String name(){
        return "Cooperate";
       
    }

    @Override
    public void plots(int oppMove, int Score) {
        totalScore=totalScore+Score;
        
    }
}
