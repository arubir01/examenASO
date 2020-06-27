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
			return(this.current != null);
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
			
			this.pos ++;
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
		DoubleNode<T> elemento = this.front;
		
		while(elemento != this.last) {
			elemento = elemento.getNextElement();
			
			elemento.getPrevElement().setNextElement(null);
			elemento.setPrevElement(null);
		}
		this.front = null;
		this.last = null;
	}


	@Override
	public void insertFirst(T elem) {
		if(elem == null)
			throw new NullPointerException();
		
		DoubleNode<T> elemento = new DoubleNode<T>(elem);
		
		if(this.front == null) {
			this.front = elemento;
			this.last = elemento;
			
		} else {
			
			this.front.setPrevElement(elemento);
			elemento.setNextElement(this.front);
			
			this.front = elemento;
		}
	}


	@Override
	public void insertLast(T elem) {
		if(elem == null)
			throw new NullPointerException();
		
		DoubleNode<T> elemento = new DoubleNode<T>(elem);		
		
		if(this.last == null) {
			this.front = elemento;
			this.last = elemento;
			
		} else {
			
			this.last.setNextElement(elemento);
			elemento.setPrevElement(this.last);
			
			this.last = elemento;
		}
	}


	@Override
	public T removeFirst() throws EmptyCollectionException{
		if(isEmpty())
			throw new EmptyCollectionException("");
		
		DoubleNode<T> elemento = this.front;
		
		if(this.front == this.last) {
			this.front = null;
			this.last = null;
			
		} else {
			
			elemento.getNextElement().setPrevElement(null);
			this.front = elemento.getNextElement();
		}
		
		return elemento.getElement();
	}


	@Override
	public T removeLast() throws EmptyCollectionException{
		if(isEmpty())
			throw new EmptyCollectionException("");
		
		DoubleNode<T> elemento = this.last;
		
		if(this.front == this.last) {
			this.front = null;
			this.last = null;
			
		} else {
			
			elemento.getPrevElement().setNextElement(null);
			this.last = elemento.getPrevElement();
		}
		
		return elemento.getElement();
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

			DoubleNode<T> insertar = new DoubleNode<T>(elem);
			DoubleNode<T> elemento = this.front;
			
			for(int i = 1; i <= position; i++) {
				
				if(i == position) {
					insertar.setNextElement(elemento);
					insertar.setPrevElement(elemento.getPrevElement());
				
					elemento.setPrevElement(insertar);
					insertar.getPrevElement().setNextElement(insertar);
				}
				elemento = elemento.getNextElement();
			}
			
		}
	}


	@Override
	public void insertBefore(T elem, T target) {
		if(elem == null || target == null)
			throw new NullPointerException();
		
		if(!contains(target))
			throw new NoSuchElementException();
		
		DoubleNode<T> insertar = new DoubleNode<T>(elem);
		DoubleNode<T> elemento = this.front;
		
		for(int i = 1; i <= this.size(); i++) {
			if(target.equals(elemento.getElement())) {
				if(i == 1) {
					this.insertFirst(elem);
					
				} else {
					insertar.setNextElement(elemento);
					insertar.setPrevElement(elemento.getPrevElement());
				
					elemento.setPrevElement(insertar);
					insertar.getPrevElement().setNextElement(insertar);
				}
				break;
			}
			
			elemento = elemento.getNextElement();
		}
	}


	@Override
	public T getElemPos(int position) {
		if(position <= 0 || position > size())
			throw new IllegalArgumentException();

		DoubleNode<T> elemento = this.front;
		
		for(int i = 1; i < position; i++) {
			elemento = elemento.getNextElement();
		}
		
		return elemento.getElement();
	}


	@Override
	public int getPosFirst(T elem) {
		if(elem == null)
			throw new NullPointerException();
		
		if(!contains(elem))
			throw new NoSuchElementException();

		DoubleNode<T> elemento = this.front;
		int i = 1;
		
		while(elem.equals(elemento.getElement()) == false) {
			elemento = elemento.getNextElement();
			i++;
		}
		return i;
	}


	@Override
	public int getPosLast(T elem) {
		if(elem == null)
			throw new NullPointerException();
		
		if(!contains(elem))
			throw new NoSuchElementException();

		DoubleNode<T> elemento = this.last;
		int i = this.size();
		
		while(elem.equals(elemento.getElement()) == false) {
			elemento = elemento.getPrevElement();
			i--;
		}
		return i;
	}


	@Override
	public T removePos(int pos) {
		if(pos <= 0 || pos > size())
			throw new IllegalArgumentException();
		
		DoubleNode<T> elemento;
		
		if(pos == 1) {
			elemento = this.front;
			
			if(this.front == this.last) {
				this.front = null;
				this.last = null;
				
			} else {
				
				elemento.getNextElement().setPrevElement(null);
				this.front = elemento.getNextElement();
			}
			
		} else if(pos == size()) {
			elemento = this.last;
			
			elemento.getPrevElement().setNextElement(null);
			this.last = elemento.getPrevElement();
			
		} else {
			elemento = this.front;

			for(int i = 1; i <= pos; i++) {
			
				if(i == pos) {
					DoubleNode<T> anterior = elemento.getPrevElement();
					DoubleNode<T> posterior = elemento.getNextElement();
				
					anterior.setNextElement(posterior);
					posterior.setPrevElement(anterior);
				
					break;
				}
				elemento = elemento.getNextElement();
			}
		}
		
		return elemento.getElement();
	}


	@Override
	public int removeAll(T elem) {
		if(elem == null)
			throw new NullPointerException();
		
		if(!contains(elem))
			throw new NoSuchElementException();
		
		int pos = 1;
		int apariciones = 0;
		DoubleNode<T> elemento = this.front;

		while(elemento != null) {
			if(elem.equals(elemento.getElement())) {
				elemento = elemento.getNextElement();
				
				removePos(pos);
				apariciones ++;
				
			} else {
				elemento = elemento.getNextElement();
				pos++;
			}
		}
		
		return apariciones;
	}


	@Override
	public boolean contains(T elem) {
		if(elem == null)
			throw new NullPointerException();

		DoubleNode<T> elemento = this.front;
		
		for(int i = 1; i <= size(); i++) {
			if(elem.equals(elemento.getElement()))
				return true;

			elemento = elemento.getNextElement();
		}
		
		return false;
	}


	@Override
	public int size() {
		int total = 0;
		DoubleNode<T> elemento = this.front;
		
		while(elemento != null) {
			elemento = elemento.getNextElement();
			total ++;
		}
		
		return total;
	}


	@Override
	public String toStringReverse() {
		StringBuilder texto = new StringBuilder();
		DoubleNode<T> elemento = this.last;
		
		texto.append("(");
		
		while(elemento != null) {
			texto.append(elemento.getElement() + " ");
			elemento = elemento.getPrevElement();
		}
		texto.append(")");
		return texto.toString();
	}

	@Override
	public int maxRepeated() {
		int repeticiones = 1;
		int maxApariciones = 1;

		DoubleNode<T> comparar;
		DoubleNode<T> elemento = this.front;

		for(int i = 1; i < size(); i++) {
			comparar = elemento.getNextElement();
			for(int w = i + 1; w <= size(); w++) {
				
				if(elemento.getElement().equals(comparar.getElement())) {
					repeticiones++;
				}
				comparar = comparar.getNextElement();
			}
			elemento = elemento.getNextElement();
			
			if(repeticiones > maxApariciones) {
				maxApariciones = repeticiones;
				repeticiones = 0;
			}
		}
		
		return maxApariciones;
	}

	@Override
	public String toStringFromUntil(int from, int until) {
		if(from <= 0 || until <= 0 || until <= from)
			throw new IllegalArgumentException();
		
		StringBuilder texto = new StringBuilder();
		
		if(from  > this.size()) {
			texto.append("()");
			
		} else if(until > this.size()) {

			DoubleNode<T> elemento = this.front;

			texto.append("(");
			for(int i = 1; i <= size(); i++) {
				
				if(i >= from) texto.append(elemento.getElement() + " ");
				elemento = elemento.getNextElement();
			}
			texto.append(")");
			
		} else {

			DoubleNode<T> elemento = this.front;

			texto.append("(");
			for(int i = 1; i <= size(); i++) {
				
				if(i >= from && i <= until) texto.append(elemento.getElement() + " ");
				elemento = elemento.getNextElement();
			}
			texto.append(")");
			
		}
		
		return texto.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder texto = new StringBuilder();
		DoubleNode<T> elemento = this.front;
		
		texto.append("(");
		
		while(elemento != null) {
			texto.append(elemento.getElement() + " ");
			elemento = elemento.getNextElement();
		}
		texto.append(")");
		return texto.toString();
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
		DoubleList<T> lista = new DoubleLinkedListImpl<T>();
		
		DoubleNode<T> elemento = this.last;
		
		while(elemento != null) {
			lista.insertLast(elemento.getElement());
			elemento = elemento.getPrevElement();
		}
		
		return lista;
	}
	
	@Override
	public boolean isEquals(DoubleList<T> other) {
		if(other == null) 
			throw new NullPointerException();
		
		if(this.size() != other.size()) {
			return false;
			
		} else {	

			Iterator<T> iterador = this.iterator();
			Iterator<T> iterador2 = other.iterator();
			
			while(iterador2.hasNext() == true) {

				T siguiente1 = iterador.next();
				T siguiente2 = iterador2.next();
				
				if(siguiente1.equals(siguiente2) == false) {
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

		Iterator<T> iterador;
		Iterator<T> iterador2 = other.iterator();
		
		boolean contiene = false;
		
		while(iterador2.hasNext() == true) {
			T siguiente2 = iterador2.next();
			
			iterador = this.iterator();

			while(contiene == false && iterador.hasNext() == true) {
				T siguiente1 = iterador.next();

				if(siguiente1.equals(siguiente2)) {
					contiene = true;
				}
			}
			
			if(contiene == false) {
				return false;
				
			} else {
				contiene = false;
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
			Iterator<T> iterador = this.iterator();
			Iterator<T> iterador2 = other.iterator();

			T siguiente = iterador.next();
			T siguiente2 = iterador2.next();
			
			boolean igual = false;
			
			while(iterador2.hasNext() == true && iterador.hasNext() == true) {
				
				if(igual == true) {
					siguiente2 = iterador2.next();
				}
				
				if(siguiente.equals(siguiente2)) {
					igual = true;
					
				} else {
					iterador2 = other.iterator();
					siguiente2 = iterador2.next();
					
					igual = false;
				}

				siguiente = iterador.next();
			}
			return igual;
		}
	}
	
	public String evenPositionsIteratorPrueba() {
		StringBuilder texto = new StringBuilder();
		
		Iterator<T> iterador2 = this.evenPositionsIterator();
		while(iterador2.hasNext() == true) {

			T siguiente = iterador2.next();
			texto.append(siguiente + " ");
		}
		return texto.toString();
	}
	
	public String reverseIteratorPrueba() {
		StringBuilder texto = new StringBuilder();
		
		Iterator<T> iterador2 = this.reverseIterator();
		while(iterador2.hasNext() == true) {

			T siguiente = iterador2.next();
			texto.append(siguiente + " ");
		}
		return texto.toString();
	}
	
	public String progressIteratorPrueba() {
		StringBuilder texto = new StringBuilder();
		
		Iterator<T> iterador2 = this.progressIterator();
		while(iterador2.hasNext() == true) {

			T siguiente = iterador2.next();
			texto.append(siguiente + " ");
		}
		return texto.toString();
	}
}
