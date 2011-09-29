/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prisonersdilemma;

/**
 *
 * @author Elly
 */
public interface Player {
    // interface sets the basic abilities for the players
    
//    String getID ();
    int makeMove(); // each player makes its move
     public void plots(int oppMove, int Score); 
   String name ();
    public static final int initscore=0; //variable to start scores at 0
    public static final int nomove=-1; 
    // variable to tell TFT players that no moves have been made
    public static final int Defect=1;
    public static final int Cooperate=0;

       
             
    
    
}
