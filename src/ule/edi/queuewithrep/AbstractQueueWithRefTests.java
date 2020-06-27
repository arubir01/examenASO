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
	public void testToStringIsEmpty() {
		assertTrue(S1.isEmpty());
		assertEquals(S1.toString(), "()");
	}
	
	@Test
	public void testToStringElem() {
		assertTrue(S1.isEmpty());
		S1.add("Z",2);
		assertEquals(S1.toString(), "(Z Z )");
	}
	
	@Test
	
	public void testAddWithCount() {
		S1.add("ABC", 2);
		assertEquals(S1.count("ABC"), 2);
		assertEquals(S1.size(), 2);
		S1.add("ABC", 2);
		assertEquals(S1.count("ABC"), 4);
		assertEquals(S1.size(), 4);
		S1.add("123", 2);		
		assertEquals(S1.count("123"), 2);
		assertEquals(S1.count("ABC"), 4);
		assertEquals(S1.size(), 6);
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
		S1.add("AAA", 2);
		S1.add("BBB", 3);
		S1.add("CCC", 4);
		S1.add("DDD", 5);
	}
	
	@Test
	public void addExpandsSize() {
		QueueWithRep<String> S5 = new ArrayQueueWithRepImpl(1);
		S5.add("ABC", 2);
		S5.add("DFG", 2);
		S5.add("HIJ", 2);
	}
	
	@Test (expected = NullPointerException.class)
	public void addNull() {
		S1.add(null, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addNegative() {
		S1.add("ABC", -10);
	}
	
	@Test
	public void add0Times() {
		S1.add("AAA");
		S1.add("BBB");
		S1.add("CCC");
		S1.add("DDD");
	}
	
	@Test (expected = NullPointerException.class)
	public void add0TimesNull() {
		S1.add(null);
	}
	
	@Test
	public void add0TimesExpandsSize() {
		QueueWithRep<String> S5 = new ArrayQueueWithRepImpl(1);
		S5.add("AAA");
		S5.add("BBB");
	}
	
	@Test
	public void clearTest() {
		assertFalse(S2.isEmpty());
		S2.clear();
		assertTrue(S2.isEmpty());
	}
	
	@Test
	public void remove() {
		S1.add("AAA", 3);
		S1.remove("AAA", 1);
		assertEquals(S1.size(), 2);
	}
	
	@Test
	public void remove2() {
		S1.add("ZZZ", 4);
		S1.add("A", 5);
		S1.remove("ZZZ", 3);
		assertEquals(S1.size(), 6);
	}

	@Test (expected = IllegalArgumentException.class)
	public void removeAllException() {
		S1.add("AAA", 1);
		S1.remove("AAA", 1);
	}
	
	@Test (expected = NullPointerException.class)
	public void removeNull() {
		S1.remove(null, 3);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeIllegaltimes() {
		S1.remove("ZZZ", -55);
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
		S1.add("F", 5);
		S1.add("N", 4);
		S1.add("C", 3);
		
		assertEquals(S1.remove(), 5);
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
