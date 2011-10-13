/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cluster.analysis;

import distance.EUDistance;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Elly
 */
public class Cluster {

    public Cluster(int id) {
        //generates random centroid at start
        Random r = new Random();
        centroid.x = r.nextInt();
        centroid.y = r.nextInt();
        ID=id;

    }
    final int ID;
    Point centroid = new Point();
    double energy;
    LinkedList<Point> data;


    void calculateCentroid() {
        //calculates the centroid of points in the cluster
        //a centroid is a point (x,y) where x is mean of all x values 
        // and y is mean of all y values
        int sumX = 0;
        int sumY = 0;
        for (Point s : data) {
            sumX += s.x;
            sumY += s.y;
        } // adds all the x vaues and the y values of the points in the list
        centroid.x = sumX / data.size();
        centroid.y = sumY / data.size();
    }

    void addPoint(Point p) {
        data.add(p);
    }

    void removePoint(Point p) {
        Boolean removed = data.remove(p);
        if (removed != true) {
            System.out.println("Error point not removed"); // should not happen
        }
    }

    int reassignPoints(Cluster[] clusters) {
        int numReassign = 0;
        for (Point p : data) {
            EUDistance eud = new EUDistance();
            double dist = eud.getDistance(p, centroid);
            int closestCentroid = -1;
            for (int i = 0; i < clusters.length; i++) {
                if (clusters[i].ID == ID) {
                    continue;
                }
                double dist2 = eud.getDistance(p, clusters[i].centroid);
                if (dist2 < dist) {
                    closestCentroid = i;
                }
            }
            if (closestCentroid != -1 && clusters[closestCentroid].ID != ID) {
                clusters[closestCentroid].addPoint(p);
                removePoint(p);
                numReassign += 1;
            }
        }
        return numReassign;
    }

    double getEnergy() {
        energy = 0;
        for (Point p : data) {
            EUDistance eu = new EUDistance();
            double dist = eu.getDistance(p, centroid);
            energy += dist;
        }
        return energy;
    }
}
