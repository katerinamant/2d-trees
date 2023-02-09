package two_d_trees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileApp {
	private TwoDTree tree = new TwoDTree();

	public void ReadFile(String file) throws Exception {
		BufferedReader reader = null;
		System.out.printf("[Reading from file: %s]\n", file);
		reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		int linenum = 1;

		// Get number of points
		while (line.length() == 0) {
			// Skip empty lines
			System.out.printf("Empty line found. Ignoring line %d...\n", linenum);
			line = reader.readLine();
			linenum++;
		}
		line.trim();
		int points;
		try {
			points = Integer.parseInt(line);
		} catch (NumberFormatException e) {
			reader.close();
			throw new NumberFormatException(String.format("[Error when reading from file: %s]\n"
					+ "Line %d - Invalid number of points", file, linenum));
		}

		// Read points
		int i = 0;
		while (i<points) {
			line = reader.readLine();
			linenum++;
			while (line.length() == 0) {
				// Skip empty lines
				System.out.printf("Empty line found. Ignoring line %d...\n", linenum);
				line = reader.readLine();
				linenum++;
			}

			// Read coordinates
			line.trim();
			String split[] = line.split(" ");
			if (split.length != 2) {
				reader.close();
				throw new IOException(String.format("[Error when reading from file: %s]\n"
						+ "Line %d - Invalid point coordinates", file, linenum));
			}
			int x, y;
			try {
				x = Integer.parseInt(split[0]);
			} catch (NumberFormatException e) {
				reader.close();
				throw new NumberFormatException(String.format("[Error when reading from file: %s]\n"
						+ "Line %d - Invalid x coordinate", file, linenum));
			}
			try {
				y = Integer.parseInt(split[1]);
			} catch (NumberFormatException e) {
				reader.close();
				throw new NumberFormatException(String.format("[Error when reading from file: %s]\n"
						+ "Line %d - Invalid y coordinate", file, linenum));
			}

			if (x < 0 || x > 100 || y < 0 || y > 100) {
				// Check if coordinates are in [0,100]
				reader.close();
				throw new IOException(String.format("[Error when reading from file: %s]\n"
						+ "Line %d - Coordinates are out of bounds", file, linenum));
			}

			// Valid coordinates, insert at tree
			tree.insert(new Point(x, y));

			// Increment counter
			i++;
		}
		reader.close();
		System.out.printf("[Succesfully read from file: %s]\n\n", file);
	}

	public TwoDTree getTree() {
		return tree;
	}
}