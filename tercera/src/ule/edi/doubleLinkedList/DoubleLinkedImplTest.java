package ule.edi.doubleLinkedList;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedImplTest {
	DoubleLinkedListImpl<String> lv;
	DoubleLinkedListImpl<String> listaConElems;
	
@Before
public void antesDe() {
	lv=new DoubleLinkedListImpl<String>();
	listaConElems=new DoubleLinkedListImpl<String>();
	listaConElems.insertFirst("D");
	listaConElems.insertFirst("B");
	listaConElems.insertFirst("A");
	listaConElems.insertFirst("C");
	listaConElems.insertFirst("B");
	listaConElems.insertFirst("A");
	
}


	
	@Test
	public void isEmptyTest() {
		Assert.assertTrue(lv.isEmpty());
		Assert.assertTrue(lv.size()==0);
		Assert.assertFalse(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==6);
		
	}
	
	@Test
	public void clearTest() {
		lv.clear();
		Assert.assertTrue(lv.isEmpty());
		Assert.assertTrue(lv.size()==0);
		
		
		listaConElems.clear();
		Assert.assertTrue(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==0);
		Assert.assertEquals(listaConElems.toString(),listaConElems.toStringReverse());
		
	}
	
	@Test
	public void containsTest() {
		Assert.assertFalse(lv.contains("A"));
		Assert.assertTrue(listaConElems.contains("A"));
		Assert.assertTrue(listaConElems.contains("B"));
		Assert.assertTrue(listaConElems.contains("B"));
		Assert.assertTrue(listaConElems.contains("D"));
		Assert.assertFalse(listaConElems.contains("Z"));
		
	}
	
	@Test
	public void removeAllTest() throws EmptyCollectionException {
        Assert.assertEquals(2, listaConElems.removeAll("A"));
    	Assert.assertEquals(listaConElems.toString(),"(B C B D )");
    	
        listaConElems.removeAll("B");
		Assert.assertFalse(listaConElems.contains("A"));
		Assert.assertFalse(listaConElems.contains("B"));
		Assert.assertTrue(listaConElems.contains("C"));
		Assert.assertEquals(listaConElems.toString(),"(C D )");
		listaConElems.removeAll("C");
		
		Assert.assertTrue(listaConElems.contains("D"));
		Assert.assertFalse(listaConElems.contains("C"));
        listaConElems.removeAll("D");
		
		Assert.assertFalse(listaConElems.contains("D"));
		Assert.assertTrue(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==0);
		Assert.assertEquals(listaConElems.toString(),listaConElems.toStringReverse());
		
	}
	
	@Test
	public void isSubListTest() throws EmptyCollectionException {
		 	Assert.assertTrue(listaConElems.isSubList(lv));
	    	Assert.assertTrue(listaConElems.isSubList(new DoubleLinkedListImpl<String>("A", "B", "C")));
	      	Assert.assertEquals(listaConElems.toString(),"(A B C A B D )");
	      	Assert.assertEquals(new DoubleLinkedListImpl<String>("A", "C").toString(),"(A C )");   
	     	Assert.assertFalse(listaConElems.isSubList(new DoubleLinkedListImpl<String>("A", "C")));
	     	Assert.assertEquals(listaConElems.maxRepeated(),2);
	     	listaConElems.insertBefore("A", "D");
	    	Assert.assertEquals(listaConElems.toString(),"(A B C A B A D )");
	    	Assert.assertTrue(listaConElems.maxRepeated()==3);
	        	  
	}

	@Test (expected = NullPointerException.class)
	public void containsBadTest() throws NullPointerException {
		listaConElems.contains(null);
	}

	@Test (expected = NullPointerException.class)
	public void insertFirstBadTest() throws NullPointerException {
		listaConElems.insertFirst(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void insertLastBadTest() throws NullPointerException {
		listaConElems.insertLast(null);
	}
	
	@Test
	public void insertLastTest() throws NullPointerException {
		lv.insertLast("D");
		lv.insertLast("A");
		Assert.assertEquals(lv.toString(), "(D A )");
	}
	
	@Test
	public void removeFirstTest() throws EmptyCollectionException {
		Assert.assertEquals(listaConElems.removeFirst(), "A");
		Assert.assertEquals(listaConElems.toString(), "(B C A B D )");
		Assert.assertEquals(listaConElems.removeFirst(), "B");
		Assert.assertEquals(listaConElems.toString(), "(C A B D )");
	}
	
	@Test
	public void removeFirstTest2() throws EmptyCollectionException {
		lv.insertLast("D");
		Assert.assertEquals(lv.toString(), "(D )");
		lv.removeFirst();
		Assert.assertEquals(lv.toString(), "()");
	}
	
	@Test (expected = EmptyCollectionException.class)
	public void removeFirstBadTest() throws EmptyCollectionException {
		lv.removeFirst();
	}
	
	@Test
	public void removeLastTest() throws EmptyCollectionException {
		Assert.assertEquals(listaConElems.removeLast(), "D");
		Assert.assertEquals(listaConElems.toString(), "(A B C A B )");
		Assert.assertEquals(listaConElems.removeLast(), "B");
		Assert.assertEquals(listaConElems.toString(), "(A B C A )");
	}
	
	@Test
	public void removeLastTest2() throws EmptyCollectionException {
		lv.insertLast("D");
		Assert.assertEquals(lv.toString(), "(D )");
		lv.removeLast();
		Assert.assertEquals(lv.toString(), "()");
	}
	
	@Test (expected = EmptyCollectionException.class)
	public void removeLastBadTest() throws EmptyCollectionException {
		lv.removeLast();
	}
	
	@Test
	public void toStringTest() {
		Assert.assertEquals(listaConElems.toString(), "(A B C A B D )");
		
	}
	
	@Test
	public void toStringReverseTest() {
		Assert.assertEquals(listaConElems.toStringReverse(), "(D B A C B A )");
	}
	
	@Test
	public void toStringFromUntilTest() {
		Assert.assertEquals(listaConElems.toStringFromUntil(55, 60), "()");
		Assert.assertEquals(listaConElems.toStringFromUntil(1, 3), "(A B C )");
		Assert.assertEquals(listaConElems.toStringFromUntil(3, 10), "(C A B D )");
		Assert.assertEquals(listaConElems.toStringFromUntil(2, 5), "(B C A B )");
		Assert.assertEquals(listaConElems.toStringFromUntil(3, 4), "(C A )");
		Assert.assertEquals(listaConElems.toStringFromUntil(3, 6), "(C A B D )");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void toStringFromUntilBadTest() throws IllegalArgumentException {
		listaConElems.toStringFromUntil(0, 55);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void toStringFromUntilBadTest2() throws IllegalArgumentException {
		listaConElems.toStringFromUntil(1, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void toStringFromUntilBadTest3() throws IllegalArgumentException {
		listaConElems.toStringFromUntil(10, 5);
	}
	
	@Test
	public void insertarPosTest() {

		listaConElems.insertPos("Z", 3);
		Assert.assertEquals(listaConElems.toString(), "(A B Z C A B D )");

		listaConElems.insertPos("Z", 1);
		Assert.assertEquals(listaConElems.toString(), "(Z A B Z C A B D )");

		listaConElems.insertPos("W", 2);
		Assert.assertEquals(listaConElems.toString(), "(Z W A B Z C A B D )");

		listaConElems.insertPos("W", 55);
		Assert.assertEquals(listaConElems.toString(), "(Z W A B Z C A B D W )");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void insertarPosBadTest() throws IllegalArgumentException {
		listaConElems.insertPos("Z", -3);
	}
	
	@Test (expected = NullPointerException.class)
	public void insertarPosBadTest2() throws NullPointerException {
		listaConElems.insertPos(null, 1);
	}

	@Test
	public void insertarBeforeTest() {
		listaConElems.insertBefore("Z", "A");
		Assert.assertEquals(listaConElems.toString(), "(Z A B C A B D )");
		listaConElems.insertBefore("W", "D");
		Assert.assertEquals(listaConElems.toString(), "(Z A B C A B W D )");
		listaConElems.insertBefore("W", "B");
		Assert.assertEquals(listaConElems.toString(), "(Z A W B C A B W D )");
	}
	
	@Test (expected = NullPointerException.class)
	public void insertarBeforeBadTest() throws NullPointerException {
		listaConElems.insertBefore(null, "A");
	}
	
	@Test (expected = NullPointerException.class)
	public void insertarBeforeBadTest2() throws NullPointerException {
		listaConElems.insertBefore("A", null);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void insertarBeforeBadTest3() throws NoSuchElementException {
		listaConElems.insertBefore("A", "W");
	}

	@Test
	public void getElmPosTest() {
		Assert.assertTrue(listaConElems.getElemPos(1).equals("A"));
		Assert.assertTrue(listaConElems.getElemPos(2).equals("B"));
		Assert.assertTrue(listaConElems.getElemPos(3).equals("C"));
		Assert.assertTrue(listaConElems.getElemPos(6).equals("D"));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getElmPosBadTest() throws IllegalArgumentException {
		listaConElems.getElemPos(0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getElmPosBadTest2() throws IllegalArgumentException {
		listaConElems.getElemPos(55);
	}

	@Test
	public void getPosFirtsTest() {
		Assert.assertEquals(listaConElems.getPosFirst("C"), 3);
		Assert.assertEquals(listaConElems.getPosFirst("A"), 1);
		Assert.assertEquals(listaConElems.getPosFirst("B"), 2);
		Assert.assertEquals(listaConElems.getPosFirst("D"), 6);
	}

	@Test (expected = NullPointerException.class)
	public void getPosFirtsBadTest() throws NullPointerException {
		listaConElems.getPosFirst(null);
	}

	@Test (expected = NoSuchElementException.class)
	public void getPosFirtsBadTest2() throws NoSuchElementException {
		listaConElems.getPosFirst("W");
	}

	@Test
	public void getPosLastTest() {
		Assert.assertEquals(listaConElems.getPosLast("C"), 3);
		Assert.assertEquals(listaConElems.getPosLast("A"), 4);
		Assert.assertEquals(listaConElems.getPosLast("B"), 5);
		Assert.assertEquals(listaConElems.getPosLast("D"), 6);
	}

	@Test (expected = NullPointerException.class)
	public void getPosLastBadTest() throws NullPointerException {
		listaConElems.getPosLast(null);
	}

	@Test (expected = NoSuchElementException.class)
	public void getPosLastBadTest2() throws NoSuchElementException {
		listaConElems.getPosLast("W");
	}
	
	@Test
	public void removePosTest() {
		Assert.assertEquals(listaConElems.removePos(1), "A");
		Assert.assertEquals(listaConElems.toString(), "(B C A B D )");
		Assert.assertEquals(listaConElems.removePos(5), "D");
		Assert.assertEquals(listaConElems.toString(), "(B C A B )");
		Assert.assertEquals(listaConElems.removePos(2), "C");
		Assert.assertEquals(listaConElems.toString(), "(B A B )");
		Assert.assertEquals(listaConElems.removePos(3), "B");
		Assert.assertEquals(listaConElems.toString(), "(B A )");
	}
	
	@Test
	public void removePosTest2() {
		lv.insertFirst("A");
		Assert.assertEquals(lv.removePos(1), "A");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removePosBadTest() throws IllegalArgumentException {
		listaConElems.removePos(0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removePosBadTest2() throws IllegalArgumentException {
		listaConElems.removePos(44);
	}

	@Test (expected = NullPointerException.class)
	public void removeAllBadTest() throws NullPointerException {
        listaConElems.removeAll(null);
	}

	@Test (expected = NoSuchElementException.class)
	public void removeAllBadTest2() throws NoSuchElementException {
        listaConElems.removeAll("Z");
	}
	
	@Test
	public void maxRepeatedTest() {
		lv.insertFirst("A");
		lv.insertFirst("C");
		lv.insertFirst("A");
		lv.insertFirst("B");
		lv.insertFirst("S");
		lv.insertFirst("Z");
		lv.insertFirst("A");
		lv.insertFirst("A");
		Assert.assertEquals(lv.maxRepeated(), 4);
	}
	
	@Test
	public void maxRepeatedTest2() {
		lv.insertFirst("A");
		Assert.assertEquals(lv.maxRepeated(), 1);
	}
	
	@Test
	public void reverseIteratorTest() {
		Assert.assertEquals(listaConElems.reverseIteratorPrueba(), "D B A C B A ");
	}
	
	@Test
	public void evenPositionsIteratorTest() {
		Assert.assertEquals(listaConElems.evenPositionsIteratorPrueba(), "A C B ");
		listaConElems.removePos(6);
		Assert.assertEquals(listaConElems.evenPositionsIteratorPrueba(), "A C B ");
	}
	
	@Test
	public void progressIteratorTest() {
		lv.insertLast("1");
		lv.insertLast("2");
		lv.insertLast("3");
		lv.insertLast("4");
		lv.insertLast("5");
		lv.insertLast("6");
		lv.insertLast("7");
		lv.insertLast("8");
		lv.insertLast("9");
		lv.insertLast("10");
		lv.insertLast("11");
		lv.insertLast("12");
		lv.insertLast("13");
		lv.insertLast("14");
		lv.insertLast("15");
		lv.insertLast("16");
		lv.insertLast("17");
		lv.insertLast("18");
		lv.insertLast("19");
		lv.insertLast("20");
		
		Assert.assertEquals(lv.progressIteratorPrueba(), "1 2 4 7 11 16 ");
	}
	
	@Test
	public void isEqualsTest() {
		Assert.assertTrue(listaConElems.isEquals(new DoubleLinkedListImpl<String>("A", "B", "C", "A","B", "D")));
		Assert.assertFalse(listaConElems.isEquals(new DoubleLinkedListImpl<String>("A", "B", "C", "A","B", "A")));	
		Assert.assertFalse(listaConElems.isEquals(new DoubleLinkedListImpl<String>("A")));	
	}
	
	@Test (expected = NullPointerException.class)
	public void isEqualsBadTest() throws NullPointerException {
		Assert.assertTrue(listaConElems.isEquals(null));	
	}
	
	@Test
	public void containsAllTest() {
		Assert.assertTrue(listaConElems.containsAll(new DoubleLinkedListImpl<String>("A", "B", "C")));
		Assert.assertFalse(listaConElems.containsAll(new DoubleLinkedListImpl<String>("Z")));	
		Assert.assertTrue(listaConElems.containsAll(new DoubleLinkedListImpl<String>("D", "B", "A", "A")));
	}
	
	@Test (expected = NullPointerException.class)
	public void containsAllBadTest() throws NullPointerException {
		Assert.assertTrue(listaConElems.containsAll(null));	
	}
	
	@Test (expected = NullPointerException.class)
	public void isSubListBadTest() throws NullPointerException {
		Assert.assertTrue(listaConElems.isSubList(null));	
	}
	
	@Test
	public void reverseTest() {
		DoubleList<String> prueba = listaConElems.reverse();
		Assert.assertEquals(prueba.toString(), "(D B A C B A )");
	}
}
