package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class LinkedQueueWithRepImpl<T> implements QueueWithRep<T> {

	// Atributos
	private QueueWithRepNode<T> front;
	int count;
	
	
	// Clase interna
	@SuppressWarnings("hiding")
	public class QueueWithRepNode<T> {
		T elem;
		int num;
		QueueWithRepNode<T> next;
		
		public QueueWithRepNode (T elem, int num){
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
		
		public void setNext(QueueWithRepNode<T> next) {
			this.next = next;
		}
		
		public QueueWithRepNode<T> getNext() {
			return this.next;
		}
		
	}
	
	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class LinkedQueueWithRepIterator<T> implements Iterator<T> {
		
		private QueueWithRepNode<T> current;
       	
		public LinkedQueueWithRepIterator(QueueWithRepNode<T> nodo) {
			this.current = nodo;
		}
		
		@Override
		public boolean hasNext() {
			return(this.current != null);
		}

		@Override
		public T next() throws NoSuchElementException {
			if(!hasNext()) 
				throw new NoSuchElementException();
			
			T result = this.current.getElement();
			
			current = current.getNext();
			return result;
		}

	}
	////// FIN ITERATOR
	
	public LinkedQueueWithRepImpl() {
		this.count = 0;
		this.front = null;
	}

	/////////////
	@Override
	public void add(T element) {
		//todo
		if(element == null) 
			throw new NullPointerException();
		
		if(!(contains(element))) {
			QueueWithRepNode<T> node = new QueueWithRepNode<T>(element, 1);
			node.setNext(this.front);
			this.front = node;
			this.count++;
			
		} else {
			QueueWithRepNode<T> aux = this.front;
			while(aux.next != null) {
				if(aux.elem.equals(element)) {
					nodo.setTimes(nodo.getTimes() + 1);
				}
			}
		}
	}
	
	@Override
	public void add(T element, int times) {
		//todo
		if(element == null) 
			throw new NullPointerException();
		
		if(times <= 0)
			throw new IllegalArgumentException();
		
		if(!(contains(element))) {
			QueueWithRepNode<T> node = new QueueWithRepNode<T>(element, times);
			node.setNext(this.front);
			this.front = node;
			this.count++;
			
		} else {
			QueueWithRepNode<T> aux = this.front;
			
			while(aux.next != null) {

				if(aux.elem.equals(element)) {
					nodo.setTimes(nodo.getTimes() + times);
				}
			}
		}
	}


	@Override
	public int remove() throws EmptyCollectionException {
		//todo
		if(isEmpty() == true)
			throw new EmptyCollectionException("");
		
		int times = 0;
		
		QueueWithRepNode<T> aux = this.front;
		QueueWithRepNode<T> last = null;
		
		while(empieza != null) {
			last = aux;
			aux = last.getNext();
		}
		
		times = last.getTimes();
		
		last = this.front;
			
		this.front = this.front.getNext();
		this.front.setNext(last);
		this.count --;
		
		last.setNext(null);
		
		return times;
	}

	@Override
	public void remove(T element, int times) {
		//todo
		if(element == null) 
			throw new NullPointerException();
		
		if(times < 0)
			throw new IllegalArgumentException();
		
		boolean found = false;

		QueueWithRepNode<T> aux = this.front;
		
		while(aux != null && found == false) {
			if(aux.getElement().equals(element)) {
				found = true;
			} else {
				aux = aux.next;
			}
		}
		
		if(found == false) {
			throw new NoSuchElementException();
			
		} else {
			if(aux.getTimes() <= times) {
				throw new IllegalArgumentException();
			}	
			aux.setTimes(aux.getTimes() - times);
		}	
	}

	
	@Override
	public boolean contains(T element) {
		//todo
		if(element == null) 
			throw new NullPointerException();
		
		QueueWithRepNode<T> aux = this.front;
			
		while(aux.next != null) {

			if(aux.elem.equals(element)) {
				return true;
			}
		}
		return false;
		
	}

	@Override
	public long size() {
		//todo
		long length = 0;
		QueueWithRepNode<T> aux = this.front;
			
		while(aux.next != null) {

			length += 1;
		}
		return length;
	}

	@Override
	public boolean isEmpty() {
		//todo
		return (this.count == 0);
		
	}

	@Override
	public void clear() {
		//todo
		
		QueueWithRepNode<T> aux = this.front;
		while(this.front != null) {
			this.front = front.getNext();
			aux = null;
		}
		
		this.count = 0;
		this.front = null;
	}

	@Override
	public int count(T element) {
		//todo
		if(element == null) 
			throw new NullPointerException();
		
		int length = 0;
		QueueWithRepNode<T> aux = this.front;
			
		while(aux.next != null) {

			if(aux.elem.equals(element)) {
				length = aux.elem.getTimes();
			}
		}

		return length;
	
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO 
		return new LinkedQueueWithRepIterator<T>(this.front);
	}
	
	public QueueWithRepNode<T> verNodo(T element) {
		QueueWithRepNode<T> aux = this.front;
		while(aux != null) {
			if(aux.getElement().equals(element)) {
				return aux;
			}
			aux = front.next;
		}
		return aux;
	}


	@Override
	public String toString() {
		
		StringBuffer output = new StringBuffer();
		
		output.append("(");
		
		QueueWithRepNode<T> aux = this.front;
		
		while(aux != null) {
			for(int i = 0; i < aux.getTimes(); i++) {
				output.append(aux.getElement() + " ");
			}
			aux = aux.next;
		}
		
		// TODO Ir añadiendo en buffer las cadenas para la representación de la cola. Ejemplo: (A, A, A, B )
		
		
		output.append(")");
		return output.toString();
		
	}

}
