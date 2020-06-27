package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class ArrayQueueWithRepImpl<T> implements QueueWithRep<T> {
	
	// atributos
	
    private final int capacityDefault = 10;
	
	ElemQueueWithRep<T>[] data;
    private int count;
    
	
	@SuppressWarnings("hiding")
	public class ElemQueueWithRep<T> {
		T elem;
		int num;
		
		public ElemQueueWithRep (T elem, int num){
			this.elem=elem;
			this.num=num;
		}
		
		public int getTimes() {
			return this.num;
		}
		
		public void setTimes(int times) {
			this.num = times;
		}
		
		public T getElement() {
			return this.elem;
		}
	}

	
	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class ArrayQueueWithRepIterator<T> implements Iterator<T> {
		private int count;
		private int current;
		private ElemQueueWithRep<T>[] items;
		
		public ArrayQueueWithRepIterator(ElemQueueWithRep<T>[] cola, int count){
			//TODO
			this.items = cola;
			this.count = count;
			
			this.current = 0;
		}

		@Override
		public boolean hasNext() {
			//TODO
			return (this.current < this.count);
		
		}

		@Override
		public T next() throws NoSuchElementException {
			//TODO   
			if (!hasNext())     
				throw new NoSuchElementException();
			
			this.current ++;  
			return items[this.current - 1].getElement();
		}

		@Override
		public void remove() throws UnsupportedOperationException { 
			throw new UnsupportedOperationException();
		}
	}
	////// FIN ITERATOR
	
	
	    	// Constructores
	
			@SuppressWarnings("unchecked")
			public ArrayQueueWithRepImpl() {
				this.data =   new ElemQueueWithRep[capacityDefault];
				this.count = 0;
			}
		
			@SuppressWarnings("unchecked")
			public ArrayQueueWithRepImpl(int capacity) {
				this.data =  new ElemQueueWithRep[capacity];
				this.count = 0;
			}
		
		 	@SuppressWarnings("unchecked")
		 	private void expandCapacity() {
			
				ElemQueueWithRep<T>[] nuevo = (ElemQueueWithRep<T>[]) new ElemQueueWithRep[this.data.length * 2];
				
				for(int i = 0; i < this.data.length; i++)
					nuevo[i] = this.data[i];
				
				this.data = nuevo;
			}
	
			@Override
			public void add(T element, int times) {
				// TODO Auto-generated method stub
				if(element == null) 
					throw new NullPointerException();

				if(!(contains(element))) {
					if(this.count == data.length)
						expandCapacity();
						
					data[count] = new ElemQueueWithRep(element, times);
					count++; 
					
				} else {
					int pos = posElement(element);
					this.data[pos].setTimes(this.data[pos].getTimes() + times);
				}
			}

			@Override
			public void add(T element) {
				if(element == null) 
					throw new NullPointerException();
				
				if(!(contains(element))) {
					
					if(this.count == data.length)
						expandCapacity();
					
					data[count] = new ElemQueueWithRep(element, 1);
					count++; 
				} else {
					int pos = posElement(element);
					this.data[pos].setTimes(this.data[pos].getTimes() + 1);
				}
			}

			@Override
			public void remove(T element, int times) {
				if(element == null) 
					throw new NullPointerException();
				
				if(times < 0) return 0;
				
				int pos = posElement(element);
				
				if(pos == -1) 
					throw new NoSuchElementException();

				if(this.data[pos].getTimes() <= times) return 0;
				this.data[pos].setTimes(this.data[pos].getTimes() - times);
			}

			@Override
			public int remove() throws EmptyCollectionException {
				//TODO		
				if(isEmpty() == true)
					throw new EmptyCollectionException("");
				
				int times = this.data[0].getTimes();
				
				this.data[0] = data[count - 1];
				
				this.data[this.count - 1] = null;
				this.count--;
				
				return times;
			}

			@Override
			public void clear() {
				//TODO		
				for(int i = 0; i < this.count; i++) {
					this.data[i] = null;
				}
				this.count = 0;
			}
			
			@Override
			public boolean contains(T element) {
				//TODO
				if(element == null) 
					throw new NullPointerException();
				
				for(int i = 0; i < this.count; i++) {
					
					if(this.data[i].getElement().equals(element))
						return true;
				}
				
				return false;
			}

			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return (this.count == 0);
			}

			@Override
			public long size() {
				// TODO Auto-generated method stub
				int total = 0;

				for(int i = 0; i < this.count; i++) {
					total += this.data[i].getTimes();
				}
				
				return total;
			 
			}

			@Override
			public int count(T element) {
				// TODO Auto-generated method stub
				if(element == null) 
					throw new NullPointerException();
				
				int times = 0;

				for(int i = 0; i < this.count; i++) {
					if(this.data[i].getElement().equals(element))	
						times = this.data[i].getTimes();
				}
				
				return times;
			}
			
			private int posElement(T element) {
				int pos = -1;  //puesto que empieza en 1 y no en 0

				for(int i = 0; i < this.count; i++) {
					if(this.data[i].getElement().equals(element))
						pos = i;
				}
				
				return pos;
			}

			@Override
			public Iterator<T> iterator() {
				return new ArrayQueueWithRepIterator<T>(this.data, this.count);
			}
			
			@Override
			public String toString() {
				
				final StringBuffer output = new StringBuffer();
				
				output.append("(");

				for(int i = 0; i < this.count; i++) {
					for(int j = 0; j < this.data[i].getTimes(); j++) {
						output.append(this.data[i].getElement() + " ");
					}
				}
				
				output.append(")");
				
				return output.toString();
			}
	
}
