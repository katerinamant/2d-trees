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

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void insert(Point p) {
		// Inserts point p to the tree
		head = insertRec(head, p, 0);
		size++;
	}

	private TreeNode insertRec(TreeNode head, Point p, int level) {
		if(head == null) return new TreeNode(p);

		if(level%2 == 0) {
			// If true, compare x coordinates
			if(p.x() < head.item.x()) {
				head.l = insertRec(head.l, p, level+1);
			} else {
				head.r = insertRec(head.r, p, level+1);
			}
		} else {
			// Else, compare y coordinates
			if(p.y() < head.item.y()) {
				head.l = insertRec(head.l, p, level+1);
			} else {
				head.r = insertRec(head.r, p, level+1);
			}
		}
		return head;
	}

	public boolean search(Point p) {
		if(isEmpty()) return false;

		return searchRec(head, p, 0);
	}

	private boolean searchRec(TreeNode head, Point p, int level) {
		if(head == null) return false;

		// Item found
		if(p.x() == head.item.x() && p.y() == head.item.y()) return true;

		if(level%2 == 0) {
			// If true, compare x coordinates
			if(p.x() < head.item.x()) {
				return searchRec(head.l, p, level+1);
			} else {
				return searchRec(head.r, p, level+1);
			}
		} else {
			// Else, compare y coordinates
			if(p.y() < head.item.y()) {
				return searchRec(head.l, p, level+1);
			} else {
				return searchRec(head.r, p, level+1);
			}
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

			switch(ans) {
			case "0":
				done = true;
				break;

			case "1":
				System.out.printf("Size of tree = %d%n", tree.size());
				break;

			case "2":
				int x, y;

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
				int xkey, ykey;

				System.out.print("\nEnter x coordinate: ");
				try {
					xkey = Integer.parseInt(in.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid input!");
					break;
				}
				System.out.print("Enter y coordinate: ");
				try {
					ykey = Integer.parseInt(in.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid input!");
					break;
				}

				if (xkey < 0 || xkey > 100 || ykey < 0 || ykey > 100) {
					// Check if coordinates are in [0,100]
					System.out.println("Invalid input!");
					break;
				}

				Point p = new Point(xkey, ykey);
				System.out.println("\nSearching for point " + p + "...");
				if(tree.search(p)) {
					System.out.println("Point " + p + " exists in the tree");
				} else {
					System.out.println("Point " + p + " does not exist in the tree");
				}
				break;
			}
		}
		in.close();
	}
}
