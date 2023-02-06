package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import two_d_trees.*;

public class TestRectangle {
	private Rectangle r = new Rectangle(0, 5, 0, 5);

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
		Rectangle temp = new Rectangle(1, 2, 3, 4);
		assertTrue(temp.xmin() == 1);
		assertTrue(temp.xmax() == 2);
		assertTrue(temp.ymin() == 3);
		assertTrue(temp.ymax() == 4);
		assertTrue(temp.toString().equals("[1, 2] x [3, 4]"));
	}

	@Test
	public void test_contains() {
		System.out.println("contains");
		assertTrue(r.contains(new Point(1, 1)));
		assertFalse(r.contains(new Point(10, 10)));
	}

	@Test
	public void test_intersects() {
		System.out.println("intersects");
		assertTrue(r.intersects(new Rectangle(2, 9, 2, 9)));
		assertTrue(r.intersects(new Rectangle(5, 10, 5, 10)));
		assertFalse(r.intersects(new Rectangle(6, 20, 0, 5)));
		assertTrue(r.intersects(r));
	}

	@Test
	public void test_distance() {
		System.out.println("distance");
		assertTrue(r.distanceTo(new Point(5, 6)) == 1);
		assertTrue(r.squareDistanceTo(new Point(6, 5)) == 1);
	}
}
