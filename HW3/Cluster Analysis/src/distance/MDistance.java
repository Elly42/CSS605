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
public class MDistance implements Distance {
       // Calculates Manhattan Distance between two points

    @Override
    public double getDistance(Point A, Point B) {
        
        double XDist = Math.abs(B.x-A.x);
        double YDist = Math.abs(B.y-A.y);
        double D = XDist + YDist;
        return D;
    }
 
    
    
}
