/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prisonersdilemma;

/**
 *
 * @author Elly
 */
public class Judge {
    /* This class: determines outcome - ie manages the payoff table
     * keeps a total score
     */
   static final int CC=1; // both cooperate: player gets one month
   static final int CD=12; 
   // player cooperates opponent defects: player gets 12 months
   static final int DC=0;
   //player defects opponent cooperated: player gets 0 months
   static final int DD=3;
   // both defect: player gets 3 months
   
   static final int[][] payoffTable= {{CC, CD},
                                      {DC, DD}};
   int player1TScore=0;
   int player2TScore=0;
   int [] totalScore={0,0};
           
   
   
   
   
   int [] judgement (int player1Move, int player2Move){
       int [] results= {0,0};
       results [0] = payoffTable [player1Move][player2Move];
       results [1] = payoffTable [player2Move][player1Move];
       return results;
       
       
       
   }
//   
//   int [] keepScore (int player1Score, int player2Score){
//       player1TScore = totalScore[0]+player1Score;
//       player2TScore = totalScore[1]+player2Score;
//       totalScore [0]=player1TScore;
//       totalScore [1]= player2TScore;
//       return totalScore;
//       
      int [] keepScore (int [] lastScore){
       player1TScore = totalScore[0]+lastScore [0];
       player2TScore = totalScore[1]+lastScore [1];
       totalScore [0]=player1TScore;
       totalScore [1]= player2TScore;
       return totalScore;    
               
   }
   
}
   
           
   
