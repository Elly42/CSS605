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
    
     static int numRounds=10;

    
    static int playGame (Player p1, Player p2, int turns){
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
    
        public static void main(String[] args) {
        // round robin tournament
            
            
            

        Player r=new RandomPlayer ();
        Player c=new CooperatePlayer ();
        Player d=new DefectPlayer ();
        Player t=new TFTPlayer ();
        Player o=new OTFTPlayer ();
        Player p=new PTFTPlayer ();
        int [] numWins={0,0,0,0,0,0};
        /* array to hold number of wins for each player {random, cooperate, 
        *defect, TFT, OTFT, PTFT}
        */
        String [] playerNames={"Random","Cooperate","Defect","Tit-for-tat", 
            "Optimistic tit-for-tat", "Pessimistic Tit-for-tat"};
        
        int maxValue=0;
        int idMax=-1;//magic number
        int count=1;
        int win=0;
        while (count<6){
        
        if (count==1){
            win=playGame (r,c,numRounds);
        }else if (count==2){
            win=playGame (r,d,numRounds);
        }else if (count==3){
            win=playGame (r,t,numRounds);
        }else if (count==4){
            win=playGame (r,o,numRounds);
        }else if (count==5){
            win=playGame (r,p,numRounds);
        }    
        if (win==0){
            numWins[0]=numWins[0]+1;
        }
        else {
            numWins[count]=numWins[count]+1;
        }
        count=count+1;
        }
        
   
        while(count<10){

                if (count==6){
                    win=playGame(c,d,numRounds);
                } else if (count==7){
                    win=playGame(c,t,numRounds);
                }else if (count==8){
                    win=playGame(c,o,numRounds);
                }else if (count==9){
                    win=playGame(c,p,numRounds);
                }
                if (win==0){
                    numWins[1]=numWins[1]+1;
                }else {
                    numWins[count-4]=numWins[count-4]+1;
                }
           
        
     count=count+1;
            
        }
         
                while (count<13){
                
                    if (count==10){
                        win=playGame (d,t,numRounds);
                    }else if (count==11){
                        win=playGame (d,o,numRounds);
                    }else if (count==12){
                        win=playGame (d,p,numRounds);
                    }
                if (win==0){
                    numWins[2]=numWins[2]+1;
                }else {
                    numWins[count-7]=numWins[count-7]+1;
                }
                count=count+1;
                }
            
        
        
        
       
        win=playGame (t,o,numRounds);
        if (win==0) numWins[3]=numWins[3]+1;
        else numWins[4]=numWins[4]+1;
        
        win=playGame (t,p,numRounds);
        if (win==0) numWins[3]=numWins[3]+1;
        else numWins[5]=numWins[5]+1;
        
        win=playGame (p,o,numRounds);
        if (win==0) numWins [4]=numWins[4]+1;
        else numWins[5]=numWins[5]+1;
                
      for(int i=0; i<6; i++){
          
          if (numWins [i]>maxValue){
              maxValue=numWins[i];
              idMax=i;
          }                  
      }
      
      System.out.println ("The best strategy is "+playerNames[idMax]+
              " with a total of "+maxValue+" wins!");
      
   
       
 
        

    }
}
