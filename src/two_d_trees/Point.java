package two_d_trees;

public class Point {
	private int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int x() { return x; }

	public int y() { return y; }

	public double distanceTo(Point z) {
		// Euclidean distance between two points
		return Math.sqrt(Math.pow(z.x() - x, 2) + Math.pow(z.y() - y, 2));
	}

	public int squareDistanceTo(Point z) {
		// Square of the Euclidean distance between two points
		return (int) (Math.pow(z.x() - x, 2) + Math.pow(z.y() - y, 2));
	}

	@Override
	public String toString() {
		// Returns "(x, y)"
		return String.format("(%d, %d)", x, y);
	}
}
