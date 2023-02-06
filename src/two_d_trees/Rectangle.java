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

	public boolean intersects(Rectangle that) {
		// One rectangle is on the side of the other
		if(xmin() > that.xmax() || that.xmin() > xmax()) return false;

		// One rectangle is on top of the other
		if(ymin() > that.ymax() || that.ymin() > ymax()) return false;

		// The two rectangles overlap
		return true;
	}

	public double distanceTo(Point p) {
		// Euclidean distance from p to closest point in rectangle
		double distanceFromA = p.distanceTo(A);
		double distanceFromB = p.distanceTo(new Point(xmax(), ymax()));
		double distanceFromC = p.distanceTo(C);
		double distanceFromD = p.distanceTo(new Point(xmin(), ymin()));

		return Math.min(Math.min(distanceFromA, distanceFromB), Math.min(distanceFromC, distanceFromD));
	}

	public int squareDistanceTo(Point p) {
		// Square of the Euclidean distance from p to closest point in rectangle
		int squareDistanceFromA = p.squareDistanceTo(A);
		int squareDistanceFromB = p.squareDistanceTo(new Point(xmax(), ymax()));
		int squareDistanceFromC = p.squareDistanceTo(C);
		int squareDistanceFromD = p.squareDistanceTo(new Point(xmin(), ymin()));

		return Math.min(Math.min(squareDistanceFromA, squareDistanceFromB), Math.min(squareDistanceFromC, squareDistanceFromD));
	}

	@Override
	public String toString() {
		// Returns "[xmin, xmax] x [ymin, ymax]
		return String.format("[%d, %d] x [%d, %d]", xmin(), xmax(), ymin(), ymax());
	}
}
