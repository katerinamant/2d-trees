package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import two_d_trees.*;

public class TestTwoDTree {
	private TwoDTree t = new TwoDTree();

	@BeforeAll
	public static void setUp() {
		System.out.println("[Running TwoDTree Tests]");
	}

	@AfterAll
	public static void tearDown() {
		System.out.println("[Successfully ran TwoDTree Tests]");
	}

	@Test
	public void test_constructor() {
		System.out.println("constructor");
		assertTrue(t.size() == 0);
		assertTrue(t.isEmpty());
		assertTrue(t.head() == null);

	}

	@Test
	public void test_constructor_2() {
		System.out.println("constructor");
		Point p = new Point(1,1);
		TwoDTree new_tree = new TwoDTree(p);
		assertTrue(new_tree.size() == 1);
		assertFalse(new_tree.isEmpty());
		assertTrue(new_tree.head() == p);
	}

	@Test
	public void test_insert() {
		System.out.println("insert");
		Point p = new Point(0,0);
		t.insert(p);
		assertTrue(t.size() == 1);
		assertTrue(t.head() == p);
	}

	@Test
	public void test_search() {
		System.out.println("search");
		t.insert(new Point(1,1));
		assertTrue(t.search(new Point(1,1)));
		assertFalse(t.search(new Point(0,0)));
	}

	@Test
	public void test_neighbot() {
		System.out.println("nearest neighbor");
		Point A = new Point(0,0);
		Point B = new Point(100,100);
		Point C = new Point(15,70);
		Point D = new Point(15,80);
		t.insert(A);
		t.insert(B);
		t.insert(C);
		t.insert(D);

		assertTrue(t.nearestNeighbor(new Point(15,0)) == A);
		assertTrue(t.nearestNeighbor(new Point(60,100)) == B);
		assertTrue(t.nearestNeighbor(new Point(15,74)) == C);
		assertTrue(t.nearestNeighbor(new Point(15,76)) == D);
	}
}
