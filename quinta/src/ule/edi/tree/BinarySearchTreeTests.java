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
	* |  |  30
	* |  |  |  ∅
	* |  |  |  ∅
    */	
	private BinarySearchTreeImpl<Integer> ejemplo = null;
	
	
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  15
	* |  |  |  12
	* |  |  |  |  ∅
	* |  |  |  |  ∅
	* |  |  ∅
  */
	private BinarySearchTreeImpl<Integer> other=null;
	
	@Before
	public void setupBSTs() {
		
			
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 5, 2, 20, 15, 30);
		Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
		
		
		other =new BinarySearchTreeImpl<Integer>();
		other.insert(10, 20, 5, 2, 15, 12);
		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");
		
	    	}

	@Test
	public void testRemoveHoja() {
		ejemplo.remove(30);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, ∅}}",ejemplo.toString());
	}
	
	@Test
	public void testRemove1Hijo() {
		ejemplo.remove(5);
		Assert.assertEquals("{10, {2, ∅, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo.toString());
	}
	
	@Test
	public void testRemove2Hijos() {
		ejemplo.remove(10);
		Assert.assertEquals("{15, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}",ejemplo.toString());
	}
	
		@Test
		public void testTagDecendentsEjemplo() {
			ejemplo.tagDecendents();
			ejemplo.filterTags("decendents");
			Assert.assertEquals("{10 [(decendents, 5)], {5 [(decendents, 1)], {2 [(decendents, 0)], ∅, ∅}, ∅}, {20 [(decendents, 2)], {15 [(decendents, 0)], ∅, ∅}, {30 [(decendents, 0)], ∅, ∅}}}",ejemplo.toString());
		}
		
		@Test
		public void testTagHeightEjemplo() {
			other.tagHeight();
			other.filterTags("height");
			Assert.assertEquals("{10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], ∅, ∅}, ∅}, {20 [(height, 2)], {15 [(height, 3)], {12 [(height, 4)], ∅, ∅}, ∅}, ∅}}",other.toString());
		}
		
		
		@Test
		public void testTagOnlySonEjemplo() {
		
		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");
		Assert.assertEquals(3,other.tagOnlySonInorder());
		other.filterTags("onlySon");
		Assert.assertEquals("{10, {5, {2 [(onlySon, 1)], ∅, ∅}, ∅}, {20, {15 [(onlySon, 3)], {12 [(onlySon, 2)], ∅, ∅}, ∅}, ∅}}",other.toString());

		}

	@Test
	public void coleccionTest() {
		BinarySearchTreeImpl<Integer> prueba = new BinarySearchTreeImpl<Integer>();
		Collection<Integer> list = new ArrayList<Integer>();
		list.add(50);
		list.add(70);
		list.add(5);
		list.add(2);
		prueba.insert(list);
		Assert.assertEquals(prueba.toString(), "{50, {5, {2, ∅, ∅}, ∅}, {70, ∅, ∅}}");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void coleccionBadTest() throws IllegalArgumentException {
		BinarySearchTreeImpl<Integer> prueba = new BinarySearchTreeImpl<Integer>();
		Collection<Integer> list = new ArrayList<Integer>();
		list.add(50);
		list.add(70);
		list.add(null);
		list.add(5);
		list.add(2);
		prueba.insert(list);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void insertTBadTest() throws IllegalArgumentException {

		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 5, null, 20, 15, 30);
	}
	
	@Test
	public void insertBadTest() {

		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 5, 2, 20, 15, 30);
		Assert.assertFalse(ejemplo.insert(5));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void insertBadTest2() throws IllegalArgumentException {

		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert((Integer) null);
	}

	@Test
	public void containsTrueTest() {
		Assert.assertTrue(ejemplo.contains(20));
	}

	@Test
	public void containsFalseTest() {
		Assert.assertFalse(ejemplo.contains(50));
	}

	@Test (expected = IllegalArgumentException.class)
	public void containsBadTest() throws IllegalArgumentException {
		Assert.assertFalse(ejemplo.contains(null));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeBadTest() throws IllegalArgumentException {

		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.remove((Integer) null);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeBadTest2() throws NoSuchElementException{

		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 5, 2, 20, 15, 30);
		ejemplo.remove(55);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeBadTest3() throws IllegalArgumentException {

		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.remove((Integer) null, 10, 54);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeBadTest4() throws NoSuchElementException{

		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.remove(0, 10, 5);
	}
	
	@Test
	public void removeTest(){

		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10);
		ejemplo.remove(10);
		Assert.assertEquals(ejemplo.toString(), "∅");
	}
	
	@Test
	public void removeTest2(){

		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 15, 12);
		ejemplo.remove(15, 12);
		Assert.assertEquals(ejemplo.toString(), "{10, ∅, ∅}");
	}
	
	@Test (expected = IllegalStateException.class)
	public void getSubtreeBadTest() throws IllegalStateException{
		ejemplo.getSubtree(5);
	}
	

	@Test
	public void TagHeight2Test() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 15, 25, 30);
		ejemplo.tagHeight();
		ejemplo.filterTags("height");
		Assert.assertEquals("{10 [(height, 1)], ∅, {15 [(height, 2)], ∅, {25 [(height, 3)], ∅, {30 [(height, 4)], ∅, ∅}}}}",ejemplo.toString());
	}

	@Test
	public void tagDecendents2Test() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.tagDecendents();
		ejemplo.filterTags("decendents");
		Assert.assertEquals("∅",ejemplo.toString());
	}
	
	@Test
	public void tagDecendents3Test() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 15, 25, 30);
		ejemplo.tagDecendents();
		ejemplo.filterTags("decendents");
		Assert.assertEquals("{10 [(decendents, 3)], ∅, {15 [(decendents, 2)], ∅, {25 [(decendents, 1)], ∅, {30 [(decendents, 0)], ∅, ∅}}}}",ejemplo.toString());
	}
	
	@Test
	public void tagOnlySonInorder2Test() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		Assert.assertEquals(ejemplo.tagOnlySonInorder(), 0);
	}
	
	@Test
	public void tagOnlySonInorder3Test() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 15, 25, 30);
		Assert.assertEquals(ejemplo.tagOnlySonInorder(), 3);
		ejemplo.filterTags("onlySon");
		Assert.assertEquals("{10, ∅, {15 [(onlySon, 3)], ∅, {25 [(onlySon, 2)], ∅, {30 [(onlySon, 1)], ∅, ∅}}}}",ejemplo.toString());
	}
	
	@Test
	public void iteratorWidthTest() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 2, 4, 15);
		Iterator<Integer> it = ejemplo.iteratorWidth();
	}
	
	@Test
	public void getDegreeTest() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 2, 4, 15);
		Assert.assertEquals(2, ejemplo.getDegree());
	}
	
	@Test
	public void getDegree2Test() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		Assert.assertEquals(0, ejemplo.getDegree());
	}
	
	@Test
	public void getDegree3Test() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10);
		Assert.assertEquals(0, ejemplo.getDegree());
	}
	
	@Test
	public void renderTest() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 4);
		Assert.assertEquals("10\n|  4\n|  |  ∅\n|  |  ∅\n|  ∅\n", ejemplo.render());
	}
	
	@Test
	public void render2Test() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 4);
		Assert.assertEquals(ejemplo.tagOnlySonInorder(), 1);
		ejemplo.filterTags("onlySon");
		Assert.assertEquals("10\n|  4 [(onlySon, 1)]\n|  |  ∅\n|  |  ∅\n|  ∅\n", ejemplo.render());
	}

	@Test
	public void isLeafTest() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		Assert.assertFalse(ejemplo.isLeaf());
	}

	@Test
	public void isLeaf2Test() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10);
		Assert.assertTrue(ejemplo.isLeaf());
	}

	@Test
	public void isLeaf3Test() {
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 4);
		Assert.assertFalse(ejemplo.isLeaf());
	}
}


