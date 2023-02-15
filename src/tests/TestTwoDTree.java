package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import two_d_trees.Point;
import two_d_trees.TwoDTree;
import two_d_trees.Rectangle;

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
	public void test_neighbor() {
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

	@Test
	public void test_range_search() {
		System.out.println("range search");
		t.insert(new Point(0,0));
		t.insert(new Point(4,35));
		t.insert(new Point(15,90));
		assertTrue(t.rangeSearch(new Rectangle(0,100,0,100)).size() == 3);
		assertTrue(t.rangeSearch(new Rectangle(0,20,0,40)).size() == 2);
		assertTrue(t.rangeSearch(new Rectangle(3,5,30,36)).size() == 1);
		assertTrue(t.rangeSearch(new Rectangle(1,1,1,1)).size() == 0);
	}
}
