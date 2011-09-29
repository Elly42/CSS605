/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prisonersdilemma;

/**
 *
 * @author Elly
 */
public class DefectPlayer implements Player {

  // player always defects
    
    int totalScore=initscore;
    int numwins=initscore;
    
    @Override
    public int makeMove() {
        return Defect;
                
    }


    public String name (){
        return "Defect";
    }

    @Override
    public void plots(int oppMove, int Score) {
        totalScore=totalScore+Score;
    }
 
}
