import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class kMeansPlot extends JFrame {
	
	public boolean herp = true;
	public static ArrayList<point> points;
	public ArrayList<center> centers;
	kgraph derp;
	private JButton stepforward;
	
	public kMeansPlot(ArrayList<point> a) {
		super("K-MEANS algorithm");
		points = a;
		centers = new ArrayList<center>();
		centers.add(new center(Color.green));
		centers.add(new center(Color.blue));
		centers.add(new center(Color.red));
		
		derp = new kgraph();
		add(derp);
		
		stepforward = new JButton("step forward");
		stepforward.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						step();
						// re-paint everything, because the centers have moved and the points have changed color
						derp.repaint();
					}	
				}
		);
		add(stepforward, BorderLayout.SOUTH);
	}
	
	public void step() {
		if (herp) {
			center c1 = centers.get(0);
			center c2 = centers.get(1);
			center c3 = centers.get(2);
			
			for (int i=0; i<points.size(); i++) { // assign each point to the closest center
				point p = points.get(i);
				p.setDistances(c1, c2, c3);
			}
			for (int j=0; j<3; j++) { // move each center closer to the majority of it's points
				center c = centers.get(j);
				c.clearPoints();
				for (int k=0; k<points.size(); k++) {
					point p = points.get(k);
					if (p.color() == c.color()) {
						c.addPoint(p);
					}
				}
				
			}
			printDistortions();

			herp = false;
		} else {
			

			
			for (int k=0; k<3; k++) {
				center c = centers.get(k);
				c.calculateNextPos();
			}
			
			herp = true;
		}
		
	}
	
	public void printDistortions() {			// 
		
		double d1 = Math.sqrt(centers.get(0).calculateDistortion()/300);
		double d2 = Math.sqrt(centers.get(1).calculateDistortion()/300);
		double d3 = Math.sqrt(centers.get(2).calculateDistortion()/300);
		double da = d1 + d2 +  d3;
		
		System.out.printf("Distortion Value for green: %S\n", d1);
		System.out.printf("Distortion Value for blue: %S\n", d2);
		System.out.printf("Distortion Value for red: %S\n", d3);
		System.out.printf("Distortion Value for all: %S\n\n", da);

		
	}
	
	class kgraph extends JPanel {
		
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

	        
	        for (int i=0; i<points.size(); i++) {
	        	point p = points.get(i);
	        	g.setColor(p.color());
	        	g.fillOval(p.x(), p.y(), 5, 5);
	        }
	        for (int i=0; i<3; i++) {
	        	center c = centers.get(i);
	        	g.setColor(c.color());
	        	g.fillOval(c.x(), c.y(), 11, 11);
	        	g.setColor(Color.black);
	        	g.drawOval(c.x(), c.y(), 11, 11);
	        }
	        
		}
		
	}
	
}
