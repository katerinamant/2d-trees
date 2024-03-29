package two_d_trees;

import java.util.Scanner;

public class TwoDTree {
	static ReadFileApp reader = new ReadFileApp();
	static Scanner in = new Scanner(System.in);

	private class TreeNode {
		Point item;
		TreeNode l = null;
		TreeNode r = null;

		private TreeNode(Point p) {
			item = p;
		}
	}

	private TreeNode head;
	private int size = 0;

	public TwoDTree() {
		head = null;
	}

	public TwoDTree(Point p) {
		head = new TreeNode(p);
		size++;
	}

	public Point head() {
		return (head == null) ? null : head.item;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void insert(Point p) {
		// Inserts point p to the tree
		head = insert(head, p, 0);
		size++;
	}

	private TreeNode insert(TreeNode head, Point p, int level) {
		if(head == null) return new TreeNode(p);

		if(level%2 == 0) {
			// If true, compare x coordinates
			if(p.x() < head.item.x()) {
				head.l = insert(head.l, p, level+1);
			} else {
				head.r = insert(head.r, p, level+1);
			}
		} else {
			// Else, compare y coordinates
			if(p.y() < head.item.y()) {
				head.l = insert(head.l, p, level+1);
			} else {
				head.r = insert(head.r, p, level+1);
			}
		}
		return head;
	}

	public boolean search(Point p) {
		if(isEmpty()) return false;

		return search(head, p, 0);
	}

	private boolean search(TreeNode head, Point p, int level) {
		if(head == null) return false;

		// Item found
		if(p.x() == head.item.x() && p.y() == head.item.y()) return true;

		if(level%2 == 0) {
			// If true, compare x coordinates
			if(p.x() < head.item.x()) {
				return search(head.l, p, level+1);
			} else {
				return search(head.r, p, level+1);
			}
		} else {
			// Else, compare y coordinates
			if(p.y() < head.item.y()) {
				return search(head.l, p, level+1);
			} else {
				return search(head.r, p, level+1);
			}
		}
	}

	public Point nearestNeighbor(Point p) {
		/*
		 * Returns the point in the 2-d tree
		 * that is closest to given point p.
		 */
		return nearestNeighbor(head, p, 0).item;
	}

	private TreeNode nearestNeighbor(TreeNode root, Point p, int depth) {
		/*
		 * Returns the node with the item closest to point p.
		 */
		if (root == null) return null;

		TreeNode next = null, other = null;
		int diff = 0;

		if (depth%2 == 0) {
			// Compare X cords
			diff = root.item.x() - p.x();
		} else {
			// Compare Y cords
			diff = root.item.y() - p.y();
		}

		next = (diff > 0) ? root.l : root.r;
		other = (diff > 0) ? root.r : root.l;

		TreeNode temp = nearestNeighbor(next, p, depth+1);
		TreeNode best = closestNodeToPoint(temp, root, p);

		int squared = p.squareDistanceTo(best.item);

		/*
		 * If the perpendicular distance to the section described
		 * by the branch we did not visit (other) is shorter
		 * than the distance to the point we found (best)
		 * then we need to check the other side of the tree
		 * for a possibly closer point.
		 *
		 * Since we are calculating a perpendicular distance,
		 * the line that describes it is either vertical or horizontal
		 * depending on the comparable dimension (x/y) that corresponds
		 * to the tree's depth.
		 */
		int distanceToOther = (depth%2 == 0) ? p.x()-root.item.x() : p.y()-root.item.y();

		if (distanceToOther*distanceToOther <= squared) {
			temp = nearestNeighbor(other, p, depth+1);
			best = closestNodeToPoint(temp, best, p);
		}

		return best;
	}

	private TreeNode closestNodeToPoint(TreeNode A, TreeNode B, Point p) {
		if (A == null) return B;
		if (B == null) return A;

		int distA = p.squareDistanceTo(A.item);
		int distB = p.squareDistanceTo(B.item);

		return (distA < distB) ? A : B;
	}

	public MyLinkedList<Point> rangeSearch(Rectangle rect) {
		MyLinkedList<Point> res = new MyLinkedList<Point>();
		rangeSearch(head, rect, res, 0);
		return res;
	}

	private void rangeSearch(TreeNode root, Rectangle rect, MyLinkedList<Point> res, int depth) {
		if (root == null) return;

		// If point is contained in the given rectangle
		// add it to the result list
		if (rect.contains(root.item)) res.pushBack(root.item);

		// If the left child's comparison axis (x/y) is
		// greater than the lower bound of the rectangle,
		// then the left branch might contain points
		// inside of the rectangle
		if (root.l != null &&
				((depth%2==0 && root.l.item.x() >= rect.xmin()) ||
						depth%2!=0 && root.l.item.y() >= rect.ymin())) {
			rangeSearch(root.l, rect, res, depth+1);
		}

		// If the right child's comparison axis (x/y) is
		// smaller than the upper bound of the rectangle,
		// then the right branch might contain points
		// inside of the rectangle
		if (root.r != null &&
				((depth%2==0 && root.r.item.x() <= rect.xmax()) ||
						depth%2!=0 && root.r.item.y() <= rect.ymax())) {
			rangeSearch(root.r, rect, res, depth+1);
		}
	}

	public static void main(String[] args) throws Exception {
		// Read file input
		try {
			reader.ReadFile(args[0]);
		} catch (NullPointerException e) {
			/* Other errors are handled and thrown with custom messages
			 * inside of the ReadFileApp class.
			 * If an error is caught here, then the only case left
			 * is an early end of file (EOF) when we expect more data.
			 */
			throw new NullPointerException(String.format("[Error when reading from file: %s]\n"
					+ "\nFile ended abruptly\n",
					args[0]));
		}
		TwoDTree tree = reader.getTree();

		boolean done = false;
		while(!done) {
			System.out.println("\n\t[MENU]");
			System.out.println("1. Compute the size of the tree");
			System.out.println("2. Insert a new point");
			System.out.println("3. Search if a given point exists in the tree");
			System.out.println("4. Provide a query rectangle");
			System.out.println("5. Provide a query point");
			System.out.println("0. Exit");
			System.out.print("> ");

			String ans = in.nextLine();
			int x,y;
			Point p;
			switch(ans) {
			case "0":
				done = true;
				break;

			case "1":
				System.out.printf("Size of tree = %d%n", tree.size());
				break;

			case "2":
				System.out.print("\nEnter x coordinate: ");
				try {
					x = Integer.parseInt(in.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid input!");
					break;
				}
				System.out.print("Enter y coordinate: ");
				try {
					y = Integer.parseInt(in.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid input!");
					break;
				}

				if (x < 0 || x > 100 || y < 0 || y > 100) {
					// Check if coordinates are in [0,100]
					System.out.println("Invalid input!");
					break;
				}

				System.out.println("\nInserting point...");
				tree.insert(new Point(x, y));
				break;

			case "3":
				System.out.print("\nEnter x coordinate: ");
				try {
					x = Integer.parseInt(in.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid input!");
					break;
				}
				System.out.print("Enter y coordinate: ");
				try {
					y = Integer.parseInt(in.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid input!");
					break;
				}

				if (x < 0 || x > 100 || y < 0 || y > 100) {
					// Check if coordinates are in [0,100]
					System.out.println("Invalid input!");
					break;
				}

				p = new Point(x, y);
				System.out.println("\nSearching for point " + p + "...");
				if(tree.search(p)) {
					System.out.println("Point " + p + " exists in the tree");
				} else {
					System.out.println("Point " + p + " does not exist in the tree");
				}
				break;

			case "4":
				int xmin, xmax, ymin, ymax;
				System.out.print("\nEnter xmin bound: ");
				try {
					xmin = Integer.parseInt(in.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid input!");
					break;
				}
				System.out.print("Enter xmax bound: ");
				try {
					xmax = Integer.parseInt(in.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid input!");
					break;
				}
				System.out.print("Enter ymin bound: ");
				try {
					ymin = Integer.parseInt(in.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid input!");
					break;
				}
				System.out.print("Enter ymax bound: ");
				try {
					ymax = Integer.parseInt(in.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid input!");
					break;
				}

				if (xmin < 0 || xmin > 100 || xmax < 0 || xmax > 100 || ymin < 0 || ymin > 100 || ymax < 0 || ymax > 100) {
					// Check if coordinates are in [0,100]
					System.out.println("Invalid input!");
					break;
				}

				Rectangle rect = new Rectangle(xmin, xmax, ymin, ymax);
				System.out.println("\nSearching for points inside of rectangle: " + rect + "...");
				System.out.println("Points inside of given rectangle: " + tree.rangeSearch(rect));
				break;

			case "5":
				System.out.print("\nEnter x coordinate: ");
				try {
					x = Integer.parseInt(in.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid input!");
					break;
				}
				System.out.print("Enter y coordinate: ");
				try {
					y = Integer.parseInt(in.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid input!");
					break;
				}

				if (x < 0 || x > 100 || y < 0 || y > 100) {
					// Check if coordinates are in [0,100]
					System.out.println("Invalid input!");
					break;
				}

				p = new Point(x, y);
				System.out.println("\nSearching for nearest neighbor to point: " + p + "...");
				System.out.println("Nearest neighbor is: " + tree.nearestNeighbor(p));
				break;
			}
		}
		in.close();
	}
}
