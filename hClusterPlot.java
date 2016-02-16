import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class hClusterPlot extends JFrame {
	
	public static ArrayList<point> points;
	hgraph derp;
	private JButton stepforward;
	private ArrayList<Color> colors;
	private ArrayList<Color> usedcolors;
	private ArrayList<cluster> clusters;
	private ArrayList<point> temp;
	
	public hClusterPlot(ArrayList<point> a) {
		super("Hierarchical Clustering algorithm");
		points = a;
		clusters = new ArrayList<cluster>();
		colors = new ArrayList<Color>();
		usedcolors = new ArrayList<Color>();

		makeColors();
		
		for (point p : a) { // put all points into their own cluster
			temp = new ArrayList<point>();
			temp.add(p);
			clusters.add(new cluster(temp, Color.black));
		}
		
		derp = new hgraph();
		add(derp);
		
		stepforward = new JButton("step forward");
		stepforward.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						connectClusters();
						// re-paint everything, because the points have changed color
						derp.repaint();
					}	
				}
		);
		add(stepforward, BorderLayout.SOUTH);
	}
	
	public void connectClusters() { // connect the closest two clusters
		if (clusters.size() == 2) { // once have only two clusters
			System.out.println("DONE!");
			return;
		}
		
		
		cluster close1 = clusters.get(0),
				close2 = clusters.get(1);
		double closestCluster = computeDistance(close1, close2);
		
		for (int i=0; i<clusters.size(); i++) {
			cluster c1 = clusters.get(i);
			
			for (int j=0; j<clusters.size(); j++) { // go through all the clusters and find closest
				if (i!=j) {
					cluster c2 = clusters.get(j);
					double dist = computeDistance(c1,c2);
					if (dist < closestCluster) {
						close1 = c1;
						close2 = c2;
						closestCluster = dist;
					}
				}
			}
		}
		
		makeCluster(close1, close2);
		
	}
	
	public double computeDistance(cluster c1, cluster c2) { // get the distances between the clusters
		double closest = 4000;
		for (point p1 : c1.getPoints()) {
			for (point p2 : c2.getPoints()) {
				double dist = Math.sqrt(Math.pow( (p1.x() - p2.x()) , 2) + Math.pow( (p1.y() - p2.y()) , 2));
				if (dist < closest) {
					closest = dist;
				}
			}
		}
		return closest;
	}
	
	public int rand() {
		return (int) (Math.random() * colors.size());
	}
	
	public void makeCluster(cluster c1, cluster c2) { // merge clusters
		// choose a color for new cluster
		Color newC = Color.black;
		while (true) {
			newC = colors.get(rand());
			if (!usedcolors.contains(newC)) { // if color hasn't been used
				usedcolors.add(newC); // add color to the used colors list
				break;
			}
		}
		ArrayList<point> cpoints = new ArrayList<point>();
		for (point p : c1.getPoints()) { // add points from both clusters to the new one
			cpoints.add(p);
		}
		for (point p : c2.getPoints()) {
			cpoints.add(p);
		}
		
		// make a new cluster passing it all the points from both of the others
		cluster flurp = new cluster(cpoints, newC);
		
		// remove the two old clusters
		clusters.remove(c1); // DOUBLE CHECK THIS, MAY NEED THE CLUSTER'S INDEX
		clusters.remove(c2);
		
		// add the new cluster
		clusters.add(flurp);
		
	}
	
	public void makeColors() {
		for (int i=200; i>=50; i-=30) {
			for (int j=200; j>=50; j-=30) {
				for (int k=200; k>=50; k-=30) {
					colors.add(new Color(i,j,k));
				}
			}
		}
	}
	
	class hgraph extends JPanel {
		
		@Override
		public void paint(Graphics g) { // this will run automatically
			g.setColor(Color.white);
	        g.fillRect(0, 0, 700, 700);
	        g.setColor(Color.black);
	        g.drawLine(0, 700, 700, 700); // the graph lines
	        g.drawLine(0, 0, 0, 700);
	        g.drawLine(300, 700, 300, 690); // the tick marks
	        g.drawLine(600, 700, 600, 690);
	        g.drawLine(0, 400, 10, 400);
	        g.drawLine(0, 100, 10, 100);
	        
	        for (int i=0; i<clusters.size(); i++) { // the points in the cluster
	        	cluster cl = clusters.get(i);
	        	g.setColor(cl.getColor());
	        	for (int j=0; j<cl.getPoints().size(); j++) {
	        		point p = cl.getPoints().get(j);
		        	g.fillOval(p.x(), p.y(), 7, 7);
	        	}
	        	
	        }
		}
		
	}
	
}
