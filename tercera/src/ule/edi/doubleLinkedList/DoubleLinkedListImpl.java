package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedListImpl<T> implements DoubleList<T> {
	
	
	//	referencia al primer nodo de la lista
	private DoubleNode<T> front;
	
	//	referencia al Último nodo de la lista
	private DoubleNode<T> last;
	
	
	private class DoubleNode<T> {
		
		DoubleNode(T element) {
			this.elem = element;
			this.next = null;
			this.prev = null;
		}
		
		T elem;
		
		DoubleNode<T> next;
	    DoubleNode<T> prev;
	    
	    public void setNextElement(DoubleNode<T> elem) {
	    	this.next = elem;
	    }
	    
	    public void setPrevElement(DoubleNode<T> elem) {
	    	this.prev = elem;
	    }
	    
	    public T getElement() {
	    	return this.elem;
	    }
	    
	    public DoubleNode<T> getNextElement() {
	    	 return this.next;
	    }
	    
	    public DoubleNode<T> getPrevElement() {
	    	return this.prev;
	    }
	}

///// ITERADOR normal //////////

	@SuppressWarnings("hiding")
	private class DobleLinkedListIterator<T> implements Iterator<T> {
		  // añadir atributos
		private DoubleNode<T> current;
       	
		public DobleLinkedListIterator(DoubleNode<T> nodo) {
			// todo
			this.current = nodo;
		}
		
		@Override
		public boolean hasNext() {
			return (this.current != null);
		}

		@Override
		public T next(){
			T result = this.current.getElement();
			
			this.current = current.getNextElement();
			return result;
			
		}
	}
	
	////// FIN ITERATOR
	
	
	/// TODO :  AÑADIR OTRAS CLASES PARA LOS OTROS 3 ITERADORES

	@SuppressWarnings("hiding")
	private class DobleLinkedListReverseIterator<T> implements Iterator<T> {
		  // añadir atributos
		private DoubleNode<T> current;

		public DobleLinkedListReverseIterator(DoubleNode<T> nodo) {
			// todo
			this.current = nodo;
		}
		
		@Override
		public boolean hasNext() {
			return(this.current != null);
		}

		@Override
		public T next() {
			
			T result = this.current.getElement();
			
			this.current = current.getPrevElement();
			return result;
			
		}
	}

	@SuppressWarnings("hiding")
	private class DobleLinkedListParIterator<T> implements Iterator<T> {
		  // añadir atributos
		private DoubleNode<T> current;

		public DobleLinkedListParIterator(DoubleNode<T> nodo) {
			// todo
			this.current = nodo;
		}
		
		@Override
		public boolean hasNext() {
			return(this.current != null);
		}

		@Override
		public T next() {
			
			T result = this.current.getElement();
			
			this.current = current.getNextElement();
			if(this.current != null) this.current = current.getNextElement();
			
			return result;
			
		}
	}

	@SuppressWarnings("hiding")
	private class DobleLinkedListProgressIterator<T> implements Iterator<T> {
		  // añadir atributos
		private DoubleNode<T> current;
		int pos;
		
		public DobleLinkedListProgressIterator(DoubleNode<T> nodo) {
			// todo
			this.current = nodo;
			this.pos = 1;
		}
		
		@Override
		public boolean hasNext() {
			return(this.current != null);
		}

		@Override
		public T next() {
			T result = this.current.getElement();
			
			int i = 0;
			while(this.current != null && i < this.pos) {
				this.current = current.getNextElement();
				i ++;
			}
			
			this.pos++;
			return result;
			
		}
	}
	
	
    /////
	
	@SafeVarargs
	public DoubleLinkedListImpl(T...v ) {
		for (T elem:v) {
			this.insertLast(elem);
		}
	}


	@Override
	public boolean isEmpty() {
		if(this.front == null) return true;
		return false;
	}


	@Override
	public void clear() {
		DoubleNode<T> aux = this.front;
		
		while(aux.elem != this.last.elem) {
			
			aux.elem = aux.next.elem;
			aux.elem = null;
			aux.prev.elem = null;
			
		}
		
		this.front = null;
		this.last = null;
	}


	@Override
	public void insertFirst(T elem) {
		if(elem == null)
			throw new NullPointerException();
		
		DoubleNode<T> new = new DoubleNode<T>(elem);
		
		if(this.front == null) {
			
			this.front = new;
			this.last = new;
			
		} else {
			
			DoubleNode<T> aux = this.last;
			
			while(aux.prev != null){
				
				aux = aux.prev;
				
			}
			new.next = aux;
			this.front = new;
		}
	}


	@Override
	public void insertLast(T elem) {
		if(elem == null) throw new NullPointerException();
		
		DoubleNode<T> aux = new DoubleNode<T>(elem);		
		
		if(this.last == null) {
			this.front = aux;
			this.last = aux;
			
		} else {
			
			DoubleNode<T> aux = this.front;
			
			while(aux.next != null){
				
				aux = aux.next;
				
			}
			
			new.prev = aux;
			this.last = new;
		}
	}


	@Override
	public T removeFirst() throws EmptyCollectionException{
		
		T result;
		
		if(isEmpty()) throw new EmptyCollectionException("");
		
		DoubleNode<T> elemento = this.front;
		
		if(this.front == this.last) {
			this.front = null;
			this.last = null;
			
		} else {
			
			DoubleNode<T> first = this.last;
			
			while(first.prev.prev != null){
				
				first = first.prev;
				
			}
			
			result = first.prev.elem;
			first.prev = null;
			this.front = first;
		}
		
		return result;
	}


	@Override
	public T removeLast() throws EmptyCollectionException{
		
		T result;
		
		if(isEmpty())
			throw new EmptyCollectionException("");
		
		DoubleNode<T> new = this.last;
		
		if(this.front == this.last) {
			
			this.front = null;
			this.last = null;
			
		} else {
			
			DoubleNode<T> last = this.front;
			
			while(last.next.next != null){
				
				last = last.next;
				
			}
			
			result = last.next.elem;
			last.next = null;
			this.last = last;
			
		}
		
		return result;
	}


	@Override
	public void insertPos(T elem, int position) {
		
		if(position <= 0)
			throw new IllegalArgumentException();
		
		if(elem == null)
			throw new NullPointerException();
		
		if(position == 1) {
			this.insertFirst(elem);
			
		} else if(position > size()) {
			this.insertLast(elem);
			
		} else {

			DoubleNode<T> new = new DoubleNode<T>(elem);
			DoubleNode<T> aux = this.front;
			
			for(int i = 1; i <= position; i++) {
				
				if(i == position) {
					
					new.next.elem = aux.elem;
					new.prev.elem = aux.prev.elem;
				
					aux.setPrevElement(new);
					aux.prev.elem = new.elem;
					new.elem = elem;

				}
				
				aux = aux.getNextElement();
			}
			
		}
	}


	@Override
	public void insertBefore(T elem, T target) {
		
		if(elem == null || target == null)
			throw new NullPointerException();
		
		if(!contains(target))
			throw new NoSuchElementException();
		
		DoubleNode<T> new = new DoubleNode<T>(elem);
		DoubleNode<T> aux = this.front;
		
		for(int i = 1; i <= this.size(); i++) {
			
			if(target.equals(aux.getElement())) {
				if(i == 1) {
					this.insertFirst(elem);
					
				} else {

					new.next.elem = aux.elem;
					new.prev.elem = aux.prev.elem;
					aux.prev.elem = new.elem;
					new.elem = elem;

				}
				break;
			}
			
			aux.elem = aux.next.elem;
		}
	}


	@Override
	public T getElemPos(int position) {
		
		if(position <= 0 || position > size())
			throw new IllegalArgumentException();

		DoubleNode<T> aux = this.front;
		
		for(int i = 1; i < position; i++) {
			aux = aux.next;
		}
		
		return aux.elem;
	}


	@Override
	public int getPosFirst(T elem) {
		
		if(elem == null) throw new NullPointerException();
		
		if(!contains(elem)) throw new NoSuchElementException();

		DoubleNode<T> aux = this.front;
		int i = 1;
		
		while(elem.equals(aux.getElement()) == false) {
			
			aux.elem = aux.next.elem;
			i++;
			
		}
		
		return i;
	}


	@Override
	public int getPosLast(T elem) {
		if(elem == null) throw new NullPointerException();
		
		if(!contains(elem)) throw new NoSuchElementException();

		DoubleNode<T> aux = this.last;
		int i = this.size();
		
		while(elem.equals(aux.elem) == false) {
			
			aux.elem = aux.prev.elem;
			i--;
			
		}
		
		return i;
	}


	@Override
	public T removePos(int pos) {
		
		if(pos <= 0 || pos > size())
			throw new IllegalArgumentException();
		
		DoubleNode<T> aux;
		
		if(pos == 1) {
			aux = this.front;
			
			if(this.front == this.last) {
				
				this.front = null;
				this.last = null;
				
			} else {
				
				aux.elem = null;
				this.front = aux.next.elem;
			}
			
		} else if(pos == size()) {
			
			aux = this.last;
			aux.elem = null;
			this.last = aux.prev.elem;
			
		} else {
			aux = this.front;

			for(int i = 1; i <= pos; i++) {
			
				if(i == pos) {
					
					DoubleNode<T> previous = aux.getPrevElement();
					DoubleNode<T> next = aux.getNextElement();
				
					previous.next.elem = next.elem;
					next.prev.elem = previous.elem;
					
					break;
				}
				
				aux.elem = aux.next.elem;
			}
		}
		
		return aux.elem;
	}


	@Override
	public int removeAll(T elem) {
		
		if(elem == null)
			throw new NullPointerException();
		
		if(!contains(elem)) throw new NoSuchElementException();
		
		int pos = 1;
		int times = 0;
		
		DoubleNode<T> aux = this.front;

		while(aux.elem != null) {
			
			if(elem.equals(aux.elem)) {
				
				aux.elem = aux.next.elem;
				
				removePos(pos);
				
				times ++;
				
			} else {
				
				aux.elem = aux.next.elem;
				pos++;
			}
		}
		
		return times;
	}


	@Override
	public boolean contains(T elem) {
		if(elem == null)
			throw new NullPointerException();

		DoubleNode<T> aux = this.front;
		
		for(int i = 1; i <= size(); i++) {
			
			if(elem.equals(aux.elem)) return true;

			aux.elem = aux.next.elem;
		}
		
		return false;
	}


	@Override
	public int size() {
		int total = 0;
		DoubleNode<T> aux = this.front;
		
		while(aux.elem != null) {
			aux.elem = aux.next.elem;
			total ++;
		}
		
		return total;
	}


	@Override
	public String toStringReverse() {
		
		StringBuilder output = new StringBuilder();
		DoubleNode<T> aux = this.last;
		
		output.append("(");
		
		while(aux.elem != null) {
			
			output.append(aux.elem + " ");
			aux.elem = aux.prev.elem;
		}
		
		output.append(")");
		return output.toString();
	}

	@Override
	public int maxRepeated() {
		
		int repeated = 1;
		int max = 1;

		DoubleNode<T> rep;
		DoubleNode<T> aux = this.front;

		for(int i = 1; i < size(); i++) {
			
			rep.elem = aux.next.elem;
			
			for(int w = i + 1; w <= size(); w++) {
				
				if(aux.elem.equals(rep.elem)) {
					
					repeated++;
					
				}
				
				rep.elem = rep.next.elem;
			}
			
			aux = aux.next.elem;
			
			if(repeated > max) {
				
				max = repeated;
				repeated = 0;
			}
		}
		
		return max;
	}

	@Override
	public String toStringFromUntil(int from, int until) {
		
		if(from <= 0 || until <= 0 || until <= from) throw new IllegalArgumentException();
		
		StringBuilder output = new StringBuilder();
		
		if(from  > this.size()) {
			output.append("()");
			
		} else if(until > this.size()) {

			DoubleNode<T> aux = this.front;

			output.append("(");
			
			for(int i = 1; i <= size(); i++) {
				
				if(i >= from) output.append(aux.elem + " ");
				aux.elem = elemento.next.elem;
			}
			
			output.append(")");
			
		} else {

			DoubleNode<T> aux = this.front;
			
			output.append("(");
			
			for(int i = 1; i <= size(); i++) {
				
				if(i >= from && i <= until) output.append(aux.elem + " ");
				aux.elem = aux.next.elem;
			}
			
			output.append(")");
			
		}
		
		return output.toString();
	}
	
	@Override
	public String toString() {
		
		StringBuilder output = new StringBuilder();
		DoubleNode<T> aux = this.front;
		
		output.append("(");
		
		while(aux.elem != null) {
			
			output.append(aux.elem + " ");
			aux.elem = aux.next.elem;
		}
		
		output.append(")");
		
		return output.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new DobleLinkedListIterator<T>(this.front);
	}

	@Override
	public Iterator<T> reverseIterator() {
		return new DobleLinkedListReverseIterator<T>(this.last);
	}

	@Override
	public Iterator<T> evenPositionsIterator() {
		return new DobleLinkedListParIterator<T>(this.front);
	}


	@Override
	public Iterator<T> progressIterator() {
		return new DobleLinkedListProgressIterator<T>(this.front);
	}

	@Override
	public DoubleList<T> reverse() {
		
		DoubleList<T> list = new DoubleLinkedListImpl<T>();
		
		DoubleNode<T> aux = this.last;
		
		while(aux.elem != null) {
			
			list.insertLast(aux.elem);
			aux.elem = aux.prev.elem;
		}
		
		return list;
	}
	
	@Override
	public boolean isEquals(DoubleList<T> other) {
		
		if(other == null) 
			throw new NullPointerException();
		
		if(this.size() != other.size()) {
			return false;
			
		} else {	

			Iterator<T> iterator = this.iterator();
			Iterator<T> aux = other.iterator();
			
			while(aux.hasNext() == true) {

				T next1 = iteratorv.next();
				T next2 = aux.next();
				
				if(next1.equals(next2) == false) {
					return false;
				}
			}
		}
		
		return true;
	}


	@Override
	public boolean containsAll(DoubleList<T> other) {
		
		if(other == null) 
			throw new NullPointerException();

		Iterator<T> iterator;
		Iterator<T> aux = other.iterator();
		
		boolean contains = false;
		
		while(aux.hasNext() == true) {
			
			T next2 = aux.next();
			
			iterator = this.iterator();

			while(contiene == false && iterator.hasNext() == true) {
				T next1 = iterator.next();

				if(next1.equals(next2)) {
					contains = true;
				}
			}
			
			if(contains == false) {
				return false;
				
			} else {
				contains = false;
			}
		}
		
		return true;
	}


	@Override
	public boolean isSubList(DoubleList<T> other) {
		
		if(other == null) 
			throw new NullPointerException();
		
		if(other.isEmpty() == true) {
			return true;
			
		} else {
			
			Iterator<T> iterator = this.iterator();
			Iterator<T> aux = other.iterator();

			T next1 = iterator.next();
			T next2 = aux.next();
			
			boolean equals = false;
			
			while(aux.hasNext() == true && iterator.hasNext() == true) {
				
				if(equals == true) {
					
					next2 = aux.next();
				}
				
				if(next1.equals(next2)) {
					
					equals = true;
					
				} else {
					
					aux = other.iterator();
					next2 = aux.next();
					
					equals = false;
				}

				next1 = iterator.next();
			}
			
			return equals;
		}
	}
	
	public String evenPositionsIteratorPrueba() {
		
		StringBuilder output = new StringBuilder();
		Iterator<T> aux = this.evenPositionsIterator();
		
		while(aux.hasNext() == true) {

			T next1 = aux.next();
			output.append(next1 + " ");
		}
		
		return output.toString();
	}
	
	public String reverseIteratorPrueba() {
		
		StringBuilder output = new StringBuilder();
		Iterator<T> aux = this.reverseIterator();
		
		while(aux.hasNext() == true) {

			T next1 = aux.next();
			output.append(next1 + " ");
		}
		
		return output.toString();
	}
	
	public String progressIteratorPrueba() {
		
		StringBuilder output = new StringBuilder();
		Iterator<T> aux = this.progressIterator();
		
		while(aux.hasNext() == true) {

			T next = aux.next();
			output.append(next + " ");
			
		}
		
		return output.toString();
	}
}
