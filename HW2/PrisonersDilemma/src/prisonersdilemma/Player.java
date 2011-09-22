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
    
  
    int makeMove(); // each player makes its move
    void plots (int oppMove, int Score); 
    // tit for tat players use this to learn opponents previous move
    String name ();
    public static final int initscore=0; //variable to start scores at 0
    public static final int nomove=-1; 
    // variable to tell TFT players that no moves have been made
    

       
             
    
    
}
