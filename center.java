import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;



public class center {

	ArrayList<point> mypoints = new ArrayList<point>();
	private double x, y;
	Color c;
	
	public center(Color c) {
		this.c = c;
		x = 50 + getRand();
		y = 700 - getRand();
	}
	
	public void clearPoints() {
		mypoints = new ArrayList<point>();
	}
	
	public void addPoint(point p) {
		mypoints.add(p);
	}
	
	public void calculateNextPos() {
		if (mypoints.size() == 0) {
			return;
		}
		double allx = 0;
		double ally = 0;
		for (int i=0; i<mypoints.size(); i++) {
			point p = mypoints.get(i);
			allx += p.x();
			ally += p.y();
		}
		x = allx/mypoints.size();
		y = ally/mypoints.size();
	}
	
	public double calculateDistortion() {					// ADD ALL DISTORTION
		double distortion = 0;
		for (int i=0; i<mypoints.size(); i++) {
			distortion += mypoints.get(i).getDist();
		}
		return distortion;
	}
	
	
	public int getRand() {
		Random rand = new Random();
		return rand.nextInt(500) + 50;
	}
	
	public int x() {
		return (int) x;
	}
	
	public int y() {
		return (int) y;
	}
	
	public Color color() {
		return c;
	}
	
}
