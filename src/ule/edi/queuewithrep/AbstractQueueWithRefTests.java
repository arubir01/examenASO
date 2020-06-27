package ule.edi.queuewithrep;


import static org.junit.Assert.*;

import org.junit.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public abstract class AbstractQueueWithRefTests {

	protected abstract <T> QueueWithRep<T> createQueueWithRep();
	
	private QueueWithRep<String> S1;

	private QueueWithRep<String> S2;
	
	@Before
	public void setupQueueWithReps() {

		this.S1 = createQueueWithRep();
		
		this.S2 = createQueueWithRep();
		
		S2.add("ABC", 5);
		S2.add("123", 5);
		S2.add("XYZ", 10);
	}

	@Test
	public void testConstructionIsEmpty() {
		assertTrue(S1.isEmpty());
		assertFalse(S2.isEmpty());
	}
	
	@Test
	//Las nuevas instancias del TAD tienen tamaño cero: 
	public void testConstructionCardinality() {
		assertEquals(S1.size(), 0);
	}

	@Test
	public void testToStringInEmpty() {
		assertTrue(S1.isEmpty());
		assertEquals(S1.toString(), "()");
	}
	
	@Test
	public void testToString1elem() {
		assertTrue(S1.isEmpty());
		S1.add("A",3);
		assertEquals(S1.toString(), "(A A A )");
	}
	
	@Test
	//Añadir elementos con una multiplicidad incrementa su contador y el tamaño de la cola: ")
	public void testAddWithCount() {
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 5);
		assertEquals(S1.size(), 5);
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 10);
		S1.add("123", 5);		
		assertEquals(S1.count("123"), 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 15);
	}
	
	
	@Test (expected = NoSuchElementException.class)
	//Se pueden eliminar cero instancias de un elemento con remove(x, 0): ")
	public void testRemoveZeroInstances() throws NoSuchElementException {
		S1.remove("ABC", 0);
	}
	
	@Test
	public void ArrayQueueWithRepImpCapacidad() {
		QueueWithRep<String> S4 = new ArrayQueueWithRepImpl(20);
		assertEquals(S4.toString(), "()");
	}
	
	@Test
	public void addTimes() {
		S1.add("ABD", 2);
		S1.add("ABC", 2);
		S1.add("ABD", 2);
		S1.add("ABC", 5);
	}
	
	@Test
	public void addExpandsSize() {
		QueueWithRep<String> S5 = new ArrayQueueWithRepImpl(1);
		S5.add("ABC", 2);
		S5.add("ABD", 2);
		S5.add("ABC", 2);
	}
	
	@Test (expected = NullPointerException.class)
	public void addBad() {
		S1.add(null, 5);
	}
	@Test (expected = IllegalArgumentException.class)
	public void addBad2() {
		S1.add("ABC", -10);
	}
	
	@Test
	public void addNoTimes() {
		S1.add("ABD");
		S1.add("ABC");
		S1.add("ABD");
		S1.add("ABC");
	}
	
	@Test (expected = NullPointerException.class)
	public void addNoTimesBad() {
		S1.add(null);
	}
	
	@Test
	public void addNoTimesExpandsSize() {
		QueueWithRep<String> S5 = new ArrayQueueWithRepImpl(1);
		S5.add("ABC");
		S5.add("ABD");
	}
	
	@Test
	public void clearTest() {
		assertFalse(S2.isEmpty());
		S2.clear();
		assertTrue(S2.isEmpty());
	}
	
	@Test
	public void remove() {
		S1.add("ABC", 5);
		S1.remove("ABC", 3);
		assertEquals(S1.size(), 2);
	}
	
	@Test
	public void remove2() {
		S1.add("ABC", 5);
		S1.add("A", 5);
		S1.remove("ABC", 3);
		assertEquals(S1.size(), 7);
	}

	@Test (expected = IllegalArgumentException.class)
	public void removeBad1() {
		S1.add("ABC", 5);
		S1.remove("ABC", 5);
	}
	
	@Test (expected = NullPointerException.class)
	public void removeBad2() {
		S1.remove(null, 3);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeBad3() {
		S1.remove("ABC", -55);
	}
	
	@Test (expected = NullPointerException.class)
	public void countNull() {
		S1.count(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void containsNull() {
		S1.contains(null);
	}
	
	@Test
	public void removeFirst() throws EmptyCollectionException {
		S1.add("A", 2);
		S1.add("B", 2);
		S1.add("C", 2);
		
		assertEquals(S1.remove(), 2);
	}
	
	@Test (expected = EmptyCollectionException.class)
	public void removeFirstEmpty() throws EmptyCollectionException {
		S1.remove();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testHasNext() throws NoSuchElementException {
		Iterator iterator = S1.iterator();
		iterator.next();
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testremoveIterator() throws UnsupportedOperationException {
		Iterator iterator = S1.iterator();
		iterator.remove();
	}
	
	
	// TODO AÑADIR MAS TESTS
}
