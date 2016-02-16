import java.awt.Color;
import java.util.ArrayList;

public class cluster {
	
	private ArrayList<point> mypoints;
	private Color c;

	public cluster(ArrayList<point> a, Color c) {
		mypoints = a;
		this.c = c;
	}
	
	public ArrayList<point> getPoints() {
		return mypoints;
	}
	
	public Color getColor() {
		return c;
	}
	
}
