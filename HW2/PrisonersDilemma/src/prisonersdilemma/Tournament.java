/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prisonersdilemma;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
/**
 *
 * @author Elly
 */
public class Tournament {
    
    
    void reset (Player p1, Player p2){
            p1.plots(Player.nomove, 0);
            p2.plots (Player.nomove, 0);
    
       
    }

    
    
    void writesToFile(String p1, String p2, String p1Score, String p2Score){
        String [] line={p1, p2, p1Score, p2Score};
        String Tab = "\t";
        String NL = System.getProperty ("line.separator");
        try{
            
            File f=new File("C:\\Users\\Elly\\Documents\\NetBeansProjects\\PrisonersDilemma\\Output.txt");
            FileWriter fw= new FileWriter (f);
            BufferedWriter bw= new BufferedWriter (fw);
            
            for (String s:line){
                bw.append(s);
                bw.append(Tab);
            }

            bw.append(NL);
            bw.flush();
            bw.close();
            
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println ("ERROR!");
        }

        
        
    }
    
    void playGame (Player p1, Player p2, int turns){
        //runs a game between two players, returns the scores and the winner
    Judge dredd = new Judge();
    for  (int i=0; i< turns; i++){
    int p1m=p1.makeMove();
    int p2m=p2.makeMove();
    int [] Score=dredd.judgement(p1m, p2m);
    dredd.keepScore(Score);
    p1.plots(p2m, Score[0]);
    p2.plots(p1m, Score[1]);
    }
    
    reset (p1,p2);
    // tells TFT players that the match has ended
    String p1Score=""+dredd.totalScore[0];
    String p2Score=""+dredd.totalScore[1];
    writesToFile(p1.name(), p2.name(), p1Score , p2Score);
    System.out.print (p1.name() + "\t" + p2.name() + "\t");
    System.out.println(dredd.totalScore[0] + "\t" + dredd.totalScore[1]);
  //  new output method

    /*   this is the original output
    System.out.println ("When "+p1.name()+" faces "+p2.name()+" the score is "
            +p1.name()+": "+dredd.totalScore[0]+" and "+p2.name()+": "
            +dredd.totalScore[1]);
    // prints the contestants

    if (dredd.totalScore[0]<dredd.totalScore[1]){
        System.out.println(p1.name()+" is the Winner!");
               
    } else if(dredd.totalScore[0]>dredd.totalScore[1]) {
        System.out.println (p2.name()+" is the Winner!");
    } else {
        System.out.println (p1.name () + " and " + p2.name() + " tie.");
    }
      //prints winner of each match 
     
  */  
    }
    
            public void roundrobin (Player Self, Player [] Opps, int start, int rounds){
     //           System.out.println("Player1"+"\t"+"Player2"+"\t"+ "Score");
                
                writesToFile ("Player1", "Player2", "Score1", "Score2");
    
                for (int i=start+1; i<Opps.length; i++){
                playGame (Self, Opps [i], rounds);
            }
           
        }
}
