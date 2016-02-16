import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class clusteringMain {
	
	public static ArrayList<point> points;

	public static void main(String[] args) {
		kmeans();
		//Hclustering();
	}
	
	public static void kmeans() {
		points = new ArrayList<point>();
		read("A.txt");
		kMeansPlot kgraph = new kMeansPlot(points);
		kgraph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		kgraph.setSize(700, 820);		//size of window
		kgraph.setVisible(true);
	}
	
	public static void Hclustering() {
		points = new ArrayList<point>();
		read("B.txt");
		hClusterPlot hgraph = new hClusterPlot(points);
		hgraph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hgraph.setSize(800, 820);		//size of window
		hgraph.setVisible(true);
	}
		
	public static void read(String s) { // read a text file to get the points
		try {
			Scanner scan = new Scanner(new File(s)); // can't find
			Scanner scan2;
			int i=0;
			double x,y;
			String line = scan.nextLine(); // the first line is the title of the file, want to skip it.
			while (scan.hasNextLine()) { 
				line = scan.nextLine();
				scan2 = new Scanner(line);
				x = scan2.nextDouble();
				y = scan2.nextDouble();
				points.add(new point(x, y, i));
				i++;
			}
		} catch(Exception e) {
			System.out.println("exception");
		}
	}
	
	
	

}
