package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import two_d_trees.*;

class TestPoint {
	private Point p = new Point(1, 1);

	@BeforeAll
	public static void setUp() {
		System.out.println("[Running Point Tests]");
	}

	@AfterAll
	public static void tearDown() {
		System.out.println("[Successfully ran Point Tests]");
	}

	@Test
	public void test_constructor() {
		System.out.println("constructor");
		Point temp = new Point(1, 2);
		assertTrue(temp.x() == 1);
		assertTrue(temp.y() == 2);
		assertTrue(temp.toString().equals("(1, 2)"));
	}

	@Test
	public void test_distance() {
		System.out.println("distance");
		assertTrue(p.distanceTo(new Point(0, 0)) == Math.sqrt(2));
		assertTrue(p.squareDistanceTo(new Point(0, 0)) == 2);
		assertTrue(p.distanceTo(p) == 0);
	}
}

