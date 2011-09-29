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
        return Cooperate;
   
    }

  
    public String name(){
        return "Cooperate";
       
    }

    @Override
    public void plots(int oppMove, int Score) {
        totalScore=totalScore+Score;
        
    }

//    @Override
//    public String getID() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void setscore(int myMove, int oppMove, int myScore, int oppScore, String oppID) {
//        totalScore=totalScore+myScore;
//    }
}
