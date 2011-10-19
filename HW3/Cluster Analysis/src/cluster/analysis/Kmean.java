/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cluster.analysis;
import java.util.List;
import java.util.Random;
import java.lang.Double;
/**
 *
 * @author Elly
 */
public class Kmean {
    //performs K-mean style cluster analysis
    double epsilon = 0.0001; // tune later for accuracy vs run time
    Cluster[] clusters;
    int numReassigns;
    public Kmean(List <Point> data, int k){
        clusters = new Cluster[k];
        for(int i=0; i<k; i++){
            clusters[i] = new Cluster(i);

        }
        Random r= new Random ();
        for (Point p:data){
            int c=r.nextInt(k);
            clusters[c].addPoint(p);
            // randomly assigns points to each cluster
        }
    }
    
    double Iteration (){
        //reassings points, recalculates the centroid, returns the energy of the system
        double energy=0;
        numReassigns=1;
        for (Cluster c:clusters){
            numReassigns += c.reassignPoints(clusters);
        }
        for (Cluster e:clusters){
            e.calculateCentroid();
        }
        for (Cluster d:clusters){
            energy += d.getEnergy();
        }
        return energy;
    }
    
    void run(){
        // this is ugly but I'm not sure how to make it less so
        int j=0;
        double previousEnergy = Double.MAX_VALUE;//magic numer makes the do-while loop run
        for (Cluster g:clusters){
            g.calculateCentroid(); 
        }
        // calculating the centroids first avoids a divide 
        //by zero error in on the first centroid recalculation
        double systemEnergy= Iteration();// need this out here for the while condition to work
        do {
            System.out.println (""+j);
            previousEnergy = systemEnergy;
            systemEnergy = Iteration();
            j++;
           
        }
        while (previousEnergy-systemEnergy > epsilon && numReassigns >0 && j < 500);
        /*three possible stop clauses
         * 1. energy changes drops below predefined level
         * 2. no points were reassigned (which should result in a 0 energy change)
         * 3. counter to prevent endless cycling
         */
        for (Cluster f:clusters){
            System.out.println ("The centroid of the cluster is ("+f.centroid.x +","
                    +f.centroid.y+").");
            }
        System.out.println ("The energy of the system is "+systemEnergy);
    }

    
    
}
