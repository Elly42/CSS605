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
public class SQDistance implements Distance {
    // calculates squared euclidean distance between two points

    @Override
    public double getDistance(Point A, Point B) {
        double xDist = Math.pow (B.x-A.x, 2);
        double yDist = Math.pow (B.y-A.y, 2);
        double D = xDist +yDist;
        return D;
    }
    
}
