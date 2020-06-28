package ule.edi.tree;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;





public class BinarySearchTreeTests {

   
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  15
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  50
	* |  |  |  ∅
	* |  |  |  ∅
    */	
	private BinarySearchTreeImpl<Integer> tree1 = null;
	
	
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  16
	* |  |  |  15
	* |  |  |  |  ∅
	* |  |  |  |  ∅
	* |  |  ∅
  */
	private BinarySearchTreeImpl<Integer> tree2 = null;
	
	@Before
	public void setupBSTs() {
		
			
		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10, 5, 2, 20, 15, 50);
		Assert.assertEquals(tree1.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {50, ∅, ∅}}}");
		
		
		tree2 =new BinarySearchTreeImpl<Integer>();
		tree2.insert(10, 20, 5, 2, 16, 15);
		Assert.assertEquals(tree2.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {16, {15, ∅, ∅}, ∅}, ∅}}");
		
	    	}

	@Test
	public void testRemoveLeaf() {
		tree1.remove(50);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, ∅}}",tree1.toString());
	}
	
	@Test
	public void testRemoveChildren1() {
		tree1.remove(5);
		Assert.assertEquals("{10, {2, ∅, ∅}, {20, {15, ∅, ∅}, {50, ∅, ∅}}}",tree1.toString());
	}
	
	@Test
	public void testRemoveChildren2() {
		tree1.remove(10);
		Assert.assertEquals("{15, {5, {2, ∅, ∅}, ∅}, {20, ∅, {50, ∅, ∅}}}",tree1.toString());
	}
	
		@Test
		public void testTagDecendents() {
			tree1.tagDecendents();
			tree1.filterTags("decendents");
			Assert.assertEquals("{10 [(decendents, 5)], {5 [(decendents, 1)], {2 [(decendents, 0)], ∅, ∅}, ∅}, {20 [(decendents, 2)], {15 [(decendents, 0)], ∅, ∅}, {50 [(decendents, 0)], ∅, ∅}}}",tree1.toString());
		}
		
		@Test
		public void testTagHeight() {
			tree2.tagHeight();
			tree2.filterTags("height");
			Assert.assertEquals("{10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], ∅, ∅}, ∅}, {20 [(height, 2)], {16 [(height, 3)], {15 [(height, 4)], ∅, ∅}, ∅}, ∅}}",tree2.toString());
		}
		
		
		@Test
		public void testTagOnlySon() {
		
		Assert.assertEquals(tree2.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {16, {15, ∅, ∅}, ∅}, ∅}}");
		Assert.assertEquals(3,tree2.tagOnlySonInorder());
		tree2.filterTags("onlySon");
		Assert.assertEquals("{10, {5, {2 [(onlySon, 1)], ∅, ∅}, ∅}, {20, {16 [(onlySon, 3)], {15 [(onlySon, 2)], ∅, ∅}, ∅}, ∅}}",tree2.toString());

		}

	@Test
	public void coleccionTest() {
		BinarySearchTreeImpl<Integer> tree3 = new BinarySearchTreeImpl<Integer>();
		Collection<Integer> list = new ArrayList<Integer>();
		list.add(50);
		list.add(70);
		list.add(5);
		list.add(2);
		tree3.insert(list);
		Assert.assertEquals(tree3.toString(), "{50, {5, {2, ∅, ∅}, ∅}, {70, ∅, ∅}}");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void coleccionNullTest() throws IllegalArgumentException {
		BinarySearchTreeImpl<Integer> tree3 = new BinarySearchTreeImpl<Integer>();
		Collection<Integer> list = new ArrayList<Integer>();
		list.add(50);
		list.add(70);
		list.add(null);
		list.add(5);
		list.add(2);
		tree3.insert(list);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void insertTNullTest() throws IllegalArgumentException {

		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10, 5, null, 20, 15, 50);
	}
	
	@Test
	public void insertRepeatedTest() {

		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10, 5, 2, 20, 15, 50);
		Assert.assertFalse(tree1.insert(5));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void insertNullTest() throws IllegalArgumentException {

		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert((Integer) null);
	}

	@Test
	public void containsTrueTest() {
		Assert.assertTrue(tree1.contains(20));
	}

	@Test
	public void containsFalseTest() {
		Assert.assertFalse(tree1.contains(50));
	}

	@Test (expected = IllegalArgumentException.class)
	public void containsNullTest() throws IllegalArgumentException {
		Assert.assertFalse(tree1.contains(null));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeNullTest() throws IllegalArgumentException {

		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.remove((Integer) null);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeNoSuchElementTest() throws NoSuchElementException{

		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10, 5, 2, 20, 15, 50);
		tree1.remove(55);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeNullTest2() throws IllegalArgumentException {

		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.remove((Integer) null, 10, 54);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeNoSuchElementTest2() throws NoSuchElementException{

		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.remove(0, 10, 5);
	}
	
	@Test
	public void removeTest(){

		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10);
		tree1.remove(10);
		Assert.assertEquals(tree1.toString(), "∅");
	}
	
	@Test
	public void removeTest2(){

		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10, 15, 12);
		tree1.remove(15, 12);
		Assert.assertEquals(tree1.toString(), "{10, ∅, ∅}");
	}
	
	@Test (expected = IllegalStateException.class)
	public void getSubtreeIllegalTest() throws IllegalStateException{
		tree1.getSubtree(5);
	}
	

	@Test
	public void TagHeight2Test() {
		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10, 15, 25, 50);
		tree1.tagHeight();
		tree1.filterTags("height");
		Assert.assertEquals("{10 [(height, 1)], ∅, {15 [(height, 2)], ∅, {25 [(height, 3)], ∅, {50 [(height, 4)], ∅, ∅}}}}",tree1.toString());
	}

	@Test
	public void tagDecendents2Test() {
		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.tagDecendents();
		tree1.filterTags("decendents");
		Assert.assertEquals("∅", tree1.toString());
	}
	
	@Test
	public void tagDecendents3Test() {
		ejtree1emplo = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10, 15, 25, 50);
		tree1.tagDecendents();
		tree1.filterTags("decendents");
		Assert.assertEquals("{10 [(decendents, 3)], ∅, {15 [(decendents, 2)], ∅, {25 [(decendents, 1)], ∅, {50 [(decendents, 0)], ∅, ∅}}}}", tree1.toString());
	}
	
	@Test
	public void tagOnlySonInorder2Test() {
		tree1 = new BinarySearchTreeImpl<Integer>();
		Assert.assertEquals(tree1.tagOnlySonInorder(), 0);
	}
	
	@Test
	public void tagOnlySonInorder3Test() {
		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10, 15, 25, 50);
		Assert.assertEquals(tree1.tagOnlySonInorder(), 3);
		tree1.filterTags("onlySon");
		Assert.assertEquals("{10, ∅, {15 [(onlySon, 3)], ∅, {25 [(onlySon, 2)], ∅, {50 [(onlySon, 1)], ∅, ∅}}}}",tree1.toString());
	}
	
	@Test
	public void iteratorWidthTest() {
		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10, 2, 4, 15);
		Iterator<Integer> it = tree1.iteratorWidth();
	}
	
	@Test
	public void getDegreeTest() {
		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10, 2, 4, 15);
		Assert.assertEquals(2, tree1.getDegree());
	}
	
	@Test
	public void getDegree2Test() {
		tree1 = new BinarySearchTreeImpl<Integer>();
		Assert.assertEquals(0, tree1.getDegree());
	}
	
	@Test
	public void getDegree3Test() {
		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10);
		Assert.assertEquals(0, tree1.getDegree());
	}
	
	@Test
	public void renderTest() {
		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10, 4);
		Assert.assertEquals("10\n|  4\n|  |  ∅\n|  |  ∅\n|  ∅\n", tree1.render());
	}
	
	@Test
	public void render2Test() {
		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10, 4);
		Assert.assertEquals(tree1.tagOnlySonInorder(), 1);
		tree1.filterTags("onlySon");
		Assert.assertEquals("10\n|  4 [(onlySon, 1)]\n|  |  ∅\n|  |  ∅\n|  ∅\n", tree1.render());
	}

	@Test
	public void isLeafTest() {
		tree1 = new BinarySearchTreeImpl<Integer>();
		Assert.assertFalse(tree1.isLeaf());
	}

	@Test
	public void isLeaf2Test() {
		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10);
		Assert.assertTrue(tree1.isLeaf());
	}

	@Test
	public void isLeaf3Test() {
		tree1 = new BinarySearchTreeImpl<Integer>();
		tree1.insert(10, 4);
		Assert.assertFalse(tree1.isLeaf());
	}
}


