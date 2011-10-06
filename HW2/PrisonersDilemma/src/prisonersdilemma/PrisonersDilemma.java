/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prisonersdilemma;

/**
 *
 * @author Elly
 */
public class PrisonersDilemma {
    
    // to do if time - get rid of random variables that don't do anything
    // to do if time - add more comments
    // to do if time - fix grammar in score output for human player - doesn't matter
 

       
    public static final int numRounds=20;
    // variable for the number of times each pair plays the game
    
     
  
    

    
        public static void main(String[] args) {
        // round robin tournament
            

            

        Player r=new RandomPlayer ();
        Player c=new CooperatePlayer ();
        Player d=new DefectPlayer ();
        Player t=new TempermentalPlayer ();
        Player h=new HumanPlayer();
        Player o=new OTFTPlayer ();
        Player p=new PTFTPlayer ();
        Player [] tourny= {r,c,d,t,h,o,p};
        // array holds the players in the tournament
   
        
        for (int i=0; i<tourny.length; i++){
            Tournament PDGame=new Tournament();
            
            PDGame.roundrobin (tourny[i], tourny, i, numRounds);
        }

        }  
}
