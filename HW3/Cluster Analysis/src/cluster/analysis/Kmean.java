/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cluster.analysis;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Elly
 */
public class Kmean {
    double epsilon = 0.01; // tune for accuracy vs run time
    Cluster[] clusters;
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
    
    
    
}
