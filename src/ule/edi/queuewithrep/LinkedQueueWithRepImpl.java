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
			Iterator<T> iterador = this.iterator();
			while(iterador.hasNext() == true) {

				T siguiente = iterador.next();
				if(siguiente.equals(element)) {
					
					QueueWithRepNode<T> nodo = verNodo(siguiente);
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
			Iterator<T> iterador = this.iterator();
			while(iterador.hasNext() == true) {

				T siguiente = iterador.next();
				if(siguiente.equals(element)) {
					
					QueueWithRepNode<T> nodo = verNodo(siguiente);
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
		
		QueueWithRepNode<T> empieza = this.front;
		QueueWithRepNode<T> anterior = null;
		
		while(empieza != null) {
			anterior = empieza;
			empieza = anterior.getNext();
		}
		
		times = anterior.getTimes();
		
		anterior = this.front;
			
		this.front = this.front.getNext();
		this.front.setNext(anterior);
		this.count --;
		
		anterior.setNext(null);
		
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

		QueueWithRepNode<T> empieza = this.front;
		
		while(empieza != null && found == false) {
			if(empieza.getElement().equals(element)) {
				found = true;
			} else {
				empieza = front.getNext();
			}
		}
		
		if(found == false) {
			throw new NoSuchElementException();
			
		} else {
			if(empieza.getTimes() <= times) {
				throw new IllegalArgumentException();
			}	
			empieza.setTimes(empieza.getTimes() - times);
		}	
	}

	
	@Override
	public boolean contains(T element) {
		//todo
		if(element == null) 
			throw new NullPointerException();
		
		Iterator<T> iterador = this.iterator();
		while(iterador.hasNext() == true) {

			if(iterador.next().equals(element)) {
				return true;
			}
		}
		return false;
		
	}

	@Override
	public long size() {
		//todo
		long longitud = 0;
		
		Iterator<T> iterador = this.iterator();
		while(iterador.hasNext() != false) {
			longitud += verNodo(iterador.next()).getTimes();
		}
		return longitud;
	}

	@Override
	public boolean isEmpty() {
		//todo
		return (this.count == 0);
		
	}

	@Override
	public void clear() {
		//todo
		
		QueueWithRepNode<T> empieza = this.front;
		while(this.front != null) {
			this.front = front.getNext();
			empieza = null;
		}
		
		this.count = 0;
		this.front = null;
	}

	@Override
	public int count(T element) {
		//todo
		if(element == null) 
			throw new NullPointerException();
		
		int longitud = 0;
		Iterator<T> iterador = this.iterator();
		while(iterador.hasNext() == true) {

			T siguiente = iterador.next();
			if(siguiente.equals(element)) {
				longitud = verNodo(siguiente).getTimes();
			}
		}
		return longitud;
	
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO 
		return new LinkedQueueWithRepIterator<T>(this.front);
	}
	
	public QueueWithRepNode<T> verNodo(T element) {
		QueueWithRepNode<T> empieza = this.front;
		while(empieza != null) {
			if(empieza.getElement().equals(element)) {
				return empieza;
			}
			empieza = front.getNext();
		}
		return empieza;
	}


	@Override
	public String toString() {
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("(");
		
		QueueWithRepNode<T> empieza = this.front;
		
		while(empieza != null) {
			for(int w = 0; w < empieza.getTimes(); w++) {
				buffer.append(empieza.getElement() + " ");
			}
			empieza = empieza.getNext();
		}
		
		// TODO Ir añadiendo en buffer las cadenas para la representación de la cola. Ejemplo: (A, A, A, B )
		
		
		buffer.append(")");
		return buffer.toString();
	}

}