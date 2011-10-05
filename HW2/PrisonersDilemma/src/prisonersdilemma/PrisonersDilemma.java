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
           int [] totalScore={0,0,0,0,0,0,0};

     
    // to do if time - get rid of random variables that don't do anything
    // to do if time - add more comments
    // to do if time - fix grammar in score output for human player - doesn't matter
    
    public static final int numRounds=5;
    // variable for the number of times each pair plays the game
    
     
    static void reset (Player p1, Player p2){
            p1.plots(Player.nomove, 0);
            p2.plots (Player.nomove, 0);
    
       
    }
    
    static void playGame (Player p1, Player p2, int turns, int counter){
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
    int []t={0,0};
    int [] temp =dredd.keepScore (t);
    totalScore[counter]=totalScore[counter]+temp[0];
    totalScore[counter+1]=totalScore[counter+1]+temp[1];
    reset (p1,p2);

    System.out.println ("When "+p1.name()+" faces "+p2.name()+" the score is "
            +p1.name()+": "+dredd.totalScore[0]+" and "+p2.name()+": "
            +dredd.totalScore[1]);
    if (dredd.totalScore[0]<dredd.totalScore[1]){
        System.out.println(p1.name()+" is the Winner!");
//        return 0;

                
    } else if(dredd.totalScore[0]>dredd.totalScore[1]) {
        System.out.println (p2.name()+" is the Winner!");
 //       return 1; 
    } else {
        System.out.println (p1.name () + " and " + p2.name() + " tie.");
    }
       
     
    
    }
    
        public static void roundrobin (Player Self, Player [] Opps, int start){
            for (int i=start+1; i<Opps.length; i++){
                playGame (Self, Opps [i], numRounds, start);
            }
           
        }
    
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
   
        /* array to hold number of wins for each player {random, cooperate, 
        *defect, TFT, OTFT, PTFT}
        */
//        String [] playerNames={"Random","Cooperate","Defect","Tit-for-tat", 
 //           "Optimistic tit-for-tat", "Pessimistic Tit-for-tat"};
        
 //       int maxValue=0;
 //       int idMax=-1;//magic number
//        int win=0;
        
        for (int i=0; i<tourny.length; i++){
            roundrobin (tourny[i], tourny, i);
        }
        

        }  
}
