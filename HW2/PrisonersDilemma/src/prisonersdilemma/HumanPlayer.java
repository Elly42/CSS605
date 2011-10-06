/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prisonersdilemma;
import java.io.*;
/**
 *
 * @author Elly
 */
public class HumanPlayer implements Player {

    int Opplastmove=nomove;
    int totalScore=initscore;
    
    public String getUserInput (String prompt){
                String inputLine=null;
        
        System.out.println(prompt +" ");
                try {
                    BufferedReader is=new BufferedReader(
                            new InputStreamReader (System.in));
                    inputLine=is.readLine();
                    if (inputLine.length()==0) return null;
                }catch (IOException e) {
                    System.out.println("IOException: "+e);                   
                }
                return inputLine;
    }
    String checkmove (String pmove){
        if ("cooperate".equals(pmove)) {
            return pmove;
        }
        else if ("defect".equals(pmove)) {
            return pmove;
        }
        else return null;
        
    }
    
    
    @Override
    public int makeMove() {
        String text="Please make a move: defect or cooperate.";
        String m=getUserInput (text);
        String move=checkmove (m);
        while (move==null){
            m=getUserInput ("That is not a valid move.  Please make a move:"
                    + " defect or cooperate");   
            move=checkmove (m);
        }
        if ("cooperate".equals(move)){
            return Cooperate;
        } else return Defect;
        
    }

    @Override
    public void plots(int oppMove, int Score) {
 //       Opplastmove=oppMove;
        totalScore=totalScore+Score;
        String [] moves = {"cooperate", "defect"};
        if (oppMove>=0){
        System.out.println ("Your opponent's move last round was "
                +moves[oppMove] + ".");
        System.out.println ("You will serve " + Score + " months in jail.");
        }else {
            System.out.println ("That concludes this match. ");
        }
          
    }

    @Override
    public String name() {
        return "You";
    }
    
}
