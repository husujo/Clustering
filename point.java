import java.awt.Color;

public class point {

	private double x,y;
	int index;
	Color c;
	private double closestdist;
	cluster mycluster;
	
	public point(double x, double y, int index) {
		c = Color.black;
		this.x = 50 + (x * 300);
		this.y = 700 - (y * 300);
		this.index = index;
	}
	
	public point(double x, double y, int index, cluster cl) {
		c = Color.black;
		this.x = 100 + (x * 250);
		this.y = 500 - (y * 250);
		this.index = index;
		mycluster = cl;
	}
	
	public cluster getCluster() {
		return mycluster;
	}
	
	public String toString() {
		return String.format("(%s, %s) %s", x,y,index);
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
	
	public void setDistances(center c1, center c2, center c3) {			// for distortion
		center closest;
		double d1 = Math.sqrt(Math.pow((this.x - c1.x()), 2) + Math.pow((this.y - c1.y()), 2));
		double d2 = Math.sqrt(Math.pow((this.x - c2.x()), 2) + Math.pow((this.y - c2.y()), 2));
		double d3 = Math.sqrt(Math.pow((this.x - c3.x()), 2) + Math.pow((this.y - c3.y()), 2));
		
		if (d1<d2 && d1<d3) {
			closest = c1;
			closestdist = d1;
		} else if (d2<d1 && d2<d3) {
			closest = c2;
			closestdist = d2;
		} else {
			closest = c3;
			closestdist = d3;
		}

		this.c = closest.color();
		// find the closest center and set the color appropriately
	}
	
	public double getDist() {
		return closestdist;
	}
	
}
