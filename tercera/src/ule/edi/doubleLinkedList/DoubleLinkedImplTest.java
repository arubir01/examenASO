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
		Assert.assertFalse(listaConElems.contains("D"));
		
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
	public void containsNullTest() throws NullPointerException {
		listaConElems.contains(null);
	}

	@Test (expected = NullPointerException.class)
	public void insertFirstNullTest() throws NullPointerException {
		listaConElems.insertFirst(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void insertLastNullTest() throws NullPointerException {
		listaConElems.insertLast(null);
	}
	
	@Test
	public void insertLastTest() throws NullPointerException {
		lv.insertLast("Z");
		lv.insertLast("J");
		Assert.assertEquals(lv.toString(), "(Z J )");
	}
	
	@Test
	public void removeFirstTest() throws EmptyCollectionException {
		Assert.assertEquals(listaConElems.removeFirst(), "A");
		Assert.assertEquals(listaConElems.toString(), "(B C A B D )");
		Assert.assertEquals(listaConElems.removeFirst(), "B");
		Assert.assertEquals(listaConElems.toString(), "(C A B D )");
	}
	
	@Test
	public void removeFirstTest1Elem() throws EmptyCollectionException {
		lv.insertLast("D");
		Assert.assertEquals(lv.toString(), "(D )");
		lv.removeFirst();
		Assert.assertEquals(lv.toString(), "()");
	}
	
	@Test (expected = EmptyCollectionException.class)
	public void removeFirstEmptyTest() throws EmptyCollectionException {
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
	public void removeLastTest1Elem() throws EmptyCollectionException {
		lv.insertLast("D");
		Assert.assertEquals(lv.toString(), "(D )");
		lv.removeLast();
		Assert.assertEquals(lv.toString(), "()");
	}
	
	@Test (expected = EmptyCollectionException.class)
	public void removeLastEmptyTest() throws EmptyCollectionException {
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
		Assert.assertEquals(listaConElems.toStringFromUntil(11, 15), "()");
		Assert.assertEquals(listaConElems.toStringFromUntil(1, 3), "(A B C )");
		Assert.assertEquals(listaConElems.toStringFromUntil(3, 4), "(C A )");
		Assert.assertEquals(listaConElems.toStringFromUntil(2, 5), "(B C A B )");
		Assert.assertEquals(listaConElems.toStringFromUntil(1, 4), "(A B C A )");
		Assert.assertEquals(listaConElems.toStringFromUntil(3, 6), "(C A B D )");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void toStringFrom0UntilTest() throws IllegalArgumentException {
		listaConElems.toStringFromUntil(0, 21);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void toStringFromUntilBad0Test() throws IllegalArgumentException {
		listaConElems.toStringFromUntil(4, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void toStringFromBiggerThanUntilTest() throws IllegalArgumentException {
		listaConElems.toStringFromUntil(19, 2);
	}
	
	@Test
	public void insertarPosTest() {

		listaConElems.insertPos("X", 3);
		Assert.assertEquals(listaConElems.toString(), "(A B X C A B D )");

		listaConElems.insertPos("A", 1);
		Assert.assertEquals(listaConElems.toString(), "(A A B Z C A B D )");

		listaConElems.insertPos("M", 2);
		Assert.assertEquals(listaConElems.toString(), "(Z M A B Z C A B D )");

		listaConElems.insertPos("W", 66);
		Assert.assertEquals(listaConElems.toString(), "(Z W A B Z C A B D W )");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void insertarNegativePosTest() throws IllegalArgumentException {
		listaConElems.insertPos("Z", -1);
	}
	
	@Test (expected = NullPointerException.class)
	public void insertarNullElemPosTest() throws NullPointerException {
		listaConElems.insertPos(null, 4);
	}

	@Test
	public void insertarBeforeTest() {
		
		listaConElems.insertBefore("B", "A");
		Assert.assertEquals(listaConElems.toString(), "(B A B C A B D )");
		listaConElems.insertBefore("L", "B");
		Assert.assertEquals(listaConElems.toString(), "(Z A B C A L B D )");
		listaConElems.insertBefore("X", "Z");
		Assert.assertEquals(listaConElems.toString(), "(X Z A B C A B W D )");
	}
	
	@Test (expected = NullPointerException.class)
	public void insertarBeforeNullElemTest() throws NullPointerException {
		listaConElems.insertBefore(null, "K");
	}
	
	@Test (expected = NullPointerException.class)
	public void insertarBeforeNullTimesTest() throws NullPointerException {
		listaConElems.insertBefore("P", null);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void insertarBeforeNoSuchElemTest() throws NoSuchElementException {
		listaConElems.insertBefore("F", "W");
	}

	@Test
	public void getElmPosTest() {
		Assert.assertTrue(listaConElems.getElemPos(1).equals("A"));
		Assert.assertTrue(listaConElems.getElemPos(2).equals("B"));
		Assert.assertTrue(listaConElems.getElemPos(3).equals("C"));
		Assert.assertTrue(listaConElems.getElemPos(6).equals("D"));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getElmPos0Test() throws IllegalArgumentException {
		listaConElems.getElemPos(0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getElmPosOFBTest() throws IllegalArgumentException {
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
	public void getPosFirtsNullTest() throws NullPointerException {
		listaConElems.getPosFirst(null);
	}

	@Test (expected = NoSuchElementException.class)
	public void getPosFirtsNoSuchElementTest() throws NoSuchElementException {
		listaConElems.getPosFirst("L");
	}

	@Test
	public void getPosLastTest() {
		Assert.assertEquals(listaConElems.getPosLast("C"), 3);
		Assert.assertEquals(listaConElems.getPosLast("A"), 4);
		Assert.assertEquals(listaConElems.getPosLast("B"), 5);
		Assert.assertEquals(listaConElems.getPosLast("D"), 6);
	}

	@Test (expected = NullPointerException.class)
	public void getPosLastNullTest() throws NullPointerException {
		listaConElems.getPosLast(null);
	}

	@Test (expected = NoSuchElementException.class)
	public void getPosLastNoSuchElementTest() throws NoSuchElementException {
		listaConElems.getPosLast("X");
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
	public void removePos0Test() throws IllegalArgumentException {
		listaConElems.removePos(0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removePosOFBTest() throws IllegalArgumentException {
		listaConElems.removePos(99);
	}

	@Test (expected = NullPointerException.class)
	public void removeAllNullTest() throws NullPointerException {
        listaConElems.removeAll(null);
	}

	@Test (expected = NoSuchElementException.class)
	public void removeAllNoSuchElementTest() throws NoSuchElementException {
        listaConElems.removeAll("R");
	}
	
	@Test
	public void maxRepeatedTest() {
		lv.insertFirst("A");
		lv.insertFirst("B");
		lv.insertFirst("C");
		lv.insertFirst("T");
		lv.insertFirst("T");
		lv.insertFirst("T");
		lv.insertFirst("T");
		lv.insertFirst("T");
		Assert.assertEquals(lv.maxRepeated(), 5);
	}
	
	@Test
	public void maxRepeatedTest2() {
		lv.insertFirst("M");
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
	public void isEqualsNullTest() throws NullPointerException {
		Assert.assertTrue(listaConElems.isEquals(null));	
	}
	
	@Test
	public void containsAllTest() {
		Assert.assertTrue(listaConElems.containsAll(new DoubleLinkedListImpl<String>("A", "B", "C")));
		Assert.assertFalse(listaConElems.containsAll(new DoubleLinkedListImpl<String>("Z")));	
		Assert.assertTrue(listaConElems.containsAll(new DoubleLinkedListImpl<String>("D", "B", "A", "A")));
	}
	
	@Test (expected = NullPointerException.class)
	public void containsAllNullTest() throws NullPointerException {
		Assert.assertTrue(listaConElems.containsAll(null));	
	}
	
	@Test (expected = NullPointerException.class)
	public void isSubListNullTest() throws NullPointerException {
		Assert.assertTrue(listaConElems.isSubList(null));	
	}
	
	@Test
	public void reverseTest() {
		DoubleList<String> prueba = listaConElems.reverse();
		Assert.assertEquals(prueba.toString(), "(D B A C B A )");
	}
}
