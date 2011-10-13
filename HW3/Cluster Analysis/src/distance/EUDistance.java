/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package distance;

import cluster.analysis.Point;

/**
 *
 * @author Elly
 */
public class EUDistance implements Distance {
    // Calcualtes euclidean distance between two points

    @Override
    public double getDistance(Point A, Point B) {   
        double Xs= Math.pow(B.x-A.x, 2);
        double Ys= Math.pow (B.y-A.y, 2);
        
        double D= Math.sqrt (Xs+Ys);
        return D;
    }
    
}
