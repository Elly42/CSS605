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
    
    //to do - sort ties in playGame
    // make human player
    // make interesting player strategy (one chance cooperates until 
    //opponent defects then always defects)
    //fix tit for tat players so they know when a new match has started
    // fix when a player plays itself it is the same instance of the class
    // to do if time - get rid of random variables that don't do anything
    // to do if time - add more comments
    
    public static final int numRounds=100;
    // variable for the number of times each pair plays the game
    
        
    static int playGame (Player p1, Player p2, int turns){
        //runs a game between two players, returns the scores and the winner
        // having this as an int no longer serves a purpose 
        // purpose was to count the number of wins each player had
    Judge dredd = new Judge();
    for  (int i=0; i< turns; i++){
    int p1m=p1.makeMove();
    int p2m=p2.makeMove();
    int [] Score=dredd.judgement(p1m, p2m);
    dredd.keepScore(Score);
    p1.plots(p2m, Score[0]);
    p2.plots(p1m, Score[1]);
    }
 
    System.out.println ("When "+p1.name()+" faces "+p2.name()+" the score is "
            +p1.name()+": "+dredd.totalScore[0]+" and "+p2.name()+": "
            +dredd.totalScore[1]);
    if (dredd.totalScore[0]<dredd.totalScore[1]){
        System.out.println(p1.name()+" is the Winner!");
        return 0;

                
    } else {
        System.out.println (p2.name()+" is the Winner!");
        return 1; 

    }
       
        
    
    }
    
        public static void roundrobin (Player Self, Player [] Opps){
            for (int i=0; i<5; i++){
                int kwin=playGame (Self, Opps [i], numRounds);
            }
           
        }
    
        public static void main(String[] args) {
        // round robin tournament
            
            
            

        Player r=new RandomPlayer ();
        Player c=new CooperatePlayer ();
        Player d=new DefectPlayer ();
//        Player t=new TFTPlayer ();
        Player o=new OTFTPlayer ();
        Player p=new PTFTPlayer ();
        Player [] tourny= {r,c,d,o,p};
        // array holds the players in the tournament
        int [] numWins={0,0,0,0,0,0};   
        /* array to hold number of wins for each player {random, cooperate, 
        *defect, TFT, OTFT, PTFT}
        */
        String [] playerNames={"Random","Cooperate","Defect","Tit-for-tat", 
            "Optimistic tit-for-tat", "Pessimistic Tit-for-tat"};
        
        int maxValue=0;
        int idMax=-1;//magic number
        int win=0;
        
        for (int i=0; i<5; i++){
            roundrobin (tourny[i], tourny);
        }
        

        }  
}
