/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cluster.analysis;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.util.LinkedList;

/**
 *
 * @author Elly
 */
public class ClusterAnalysis {
    

    public static  LinkedList<Point> dataSnatcher() {
        LinkedList <Point> points=new LinkedList <Point>();
        try {
            CSVReader csv = new CSVReader(new FileReader("C:\\Users\\Elly\\Documents\\NetBeansProjects\\Cluster Analysis\\Elly-Roland-PDout.txt"),',','/',1);
            String[] nextLine;
            while ((nextLine = csv.readNext()) != null) {
//                System.out.println(nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3]);
                int x= Integer.parseInt(nextLine[2]);
                int y= Integer.parseInt(nextLine[3]);
                Point p = new Point();
                p.x=x;
                p.y=y;
                points.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e);
        }
        return points;

    }

    public static void main(String[] args) {
        LinkedList <Point> data;
        int numClusters=2;
        data=dataSnatcher();
        Kmean cluster= new Kmean(data, numClusters);
        cluster.run();
        
    }
}
