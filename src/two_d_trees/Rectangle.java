package two_d_trees;

public class Rectangle {
	// Rectangle is [xmin, xmax] x [ymin, ymax]
	//  A___________B
	//	|		  	|
	//	|		  	|
	//	|		  	|
	// 	D___________C

	private Point A, C;

	public Rectangle(int xmin, int xmax, int ymin, int ymax) {
		A = new Point(xmin, ymax);
		C = new Point(xmax, ymin);
	}

	public int xmin() { return A.x(); }
	public int ymin() { return C.y(); }
	public int xmax() { return C.x(); }
	public int ymax() { return A.y(); }

	public boolean contains(Point p) {
		// Returns true if p belongs to the rectangle
		return xmin() <= p.x() && p.x() <= xmax() &&
				ymin() <= p.y() && p.y() <= ymax();
	}

	public boolean intersects(Rectangle other) {
		// One rectangle is on the side of the other
		if(xmin() > other.xmax() || other.xmin() > xmax()) return false;

		// One rectangle is on top of the other
		if(ymin() > other.ymax() || other.ymin() > ymax()) return false;

		// The two rectangles overlap
		return true;
	}

	public double distanceTo(Point p) {
		// Euclidean distance from p to closest point in rectangle
		double dx = Math.max(Math.max(xmin() - p.x(), 0), p.x() - xmax());
		double dy = Math.max(Math.max(ymin() - p.y(), 0), p.y() - ymax());

		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

	public int squareDistanceTo(Point p) {
		// Square of the Euclidean distance from p to closest point in rectangle
		double dx = Math.max(Math.max(xmin() - p.x(), 0), p.x() - xmax());
		double dy = Math.max(Math.max(ymin() - p.y(), 0), p.y() - ymax());

		return (int) (Math.pow(dx, 2) + Math.pow(dy, 2));
	}

	@Override
	public String toString() {
		// Returns "[xmin, xmax] x [ymin, ymax]"
		return String.format("[%d, %d] x [%d, %d]", xmin(), xmax(), ymin(), ymax());
	}
}
