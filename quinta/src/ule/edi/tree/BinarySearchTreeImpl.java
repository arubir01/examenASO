package ule.edi.tree;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;


/**
 * Ã�rbol binario de bÃºsqueda (binary search tree, BST).
 * 
 * El cÃ³digo fuente estÃ¡ en UTF-8, y la constante 
 * EMPTY_TREE_MARK definida en AbstractTreeADT del
 * proyecto API deberÃ­a ser el sÃ­mbolo de conjunto vacÃ­o: âˆ…
 * 
 * Si aparecen caracteres "raros", es porque
 * el proyecto no estÃ¡ bien configurado en Eclipse para
 * usar esa codificaciÃ³n de caracteres.
 *
 * En el toString() que estÃ¡ ya implementado en AbstractTreeADT
 * se usa el formato:
 * 
 * 		Un Ã¡rbol vacÃ­o se representa como "âˆ…". Un Ã¡rbol no vacÃ­o
 * 		como "{(informaciÃ³n raÃ­z), sub-Ã¡rbol 1, sub-Ã¡rbol 2, ...}".
 * 
 * 		Por ejemplo, {A, {B, âˆ…, âˆ…}, âˆ…} es un Ã¡rbol binario con 
 * 		raÃ­z "A" y un Ãºnico sub-Ã¡rbol, a su izquierda, con raÃ­z "B".
 * 
 * El mÃ©todo render() tambiÃ©n representa un Ã¡rbol, pero con otro
 * formato; por ejemplo, un Ã¡rbol {M, {E, âˆ…, âˆ…}, {S, âˆ…, âˆ…}} se
 * muestra como:
 * 
 * M
 * |  E
 * |  |  âˆ…
 * |  |  âˆ…
 * |  S
 * |  |  âˆ…
 * |  |  âˆ…
 * 
 * Cualquier nodo puede llevar asociados pares (clave,valor) para
 * adjuntar informaciÃ³n extra. Si es el caso, tanto toString() como
 * render() mostrarÃ¡n los pares asociados a cada nodo.
 * 
 * Con {@link #setTag(String, Object)} se inserta un par (clave,valor)
 * y con {@link #getTag(String)} se consulta.
 * 
 * 
 * Con <T extends Comparable<? super T>> se pide que exista un orden en
 * los elementos. Se necesita para poder comparar elementos al insertar.
 * 
 * Si se usara <T extends Comparable<T>> serÃ­a muy restrictivo; en
 * su lugar se permiten tipos que sean comparables no sÃ³lo con exactamente
 * T sino tambiÃ©n con tipos por encima de T en la herencia.
 * 
 * @param <T>
 *            tipo de la informaciÃ³n en cada nodo, comparable.
 */
public class BinarySearchTreeImpl<T extends Comparable<? super T>> extends
		AbstractBinaryTreeADT<T> {

   BinarySearchTreeImpl<T> father;  //referencia a su nodo padre)

	/**
	 * Devuelve el Ã¡rbol binario de bÃºsqueda izquierdo.
	 */
	protected BinarySearchTreeImpl<T> getLeftBST() {
		//	El atributo leftSubtree es de tipo AbstractBinaryTreeADT<T> pero
		//	aquÃ­ se sabe que es ademÃ¡s de bÃºsqueda binario
		//
		return (BinarySearchTreeImpl<T>) leftSubtree;
	}

	private void setLeftBST(BinarySearchTreeImpl<T> left) {
		this.leftSubtree = left;
	}
	
	/**
	 * Devuelve el Ã¡rbol binario de bÃºsqueda derecho.
	 */
	protected BinarySearchTreeImpl<T> getRightBST() {
		return (BinarySearchTreeImpl<T>) rightSubtree;
	}

	private void setRightBST(BinarySearchTreeImpl<T> right) {
		this.rightSubtree = right;
	}
	
	/**
	 * Ã�rbol BST vacÃ­o
	 */
	public BinarySearchTreeImpl() {
		// TODO HACER QUE THIS SEA EL NODO VACÃ�O
		this.father = null;
			
	}
	
	public BinarySearchTreeImpl(BinarySearchTreeImpl<T> father) {
		// TODO HACER QUE THIS SEA EL NODO VACÃ�O, asignando como padre el parÃ¡metro recibido
		this.father = father;
		
	}


	private BinarySearchTreeImpl<T> emptyBST(BinarySearchTreeImpl<T> father) {
		return new BinarySearchTreeImpl<T>(father);
	}
	
	/**
	 * Inserta los elementos de una colecciÃ³n en el Ã¡rbol.
	 *  si alguno es 'null', NO INSERTA NINGUNO
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements
	 *            valores a insertar.
	 * @return numero de elementos insertados en el arbol (los que ya estÃ¡n no los inserta)
	 */
	public int insert(Collection<T> elements) {
		//	 si alguno es 'null', ni siquiera se comienza a insertar (no inserta ninguno)
		//TODO Implementar el mÃ©todo
		
		Iterator<T> aux = elements.iterator();
		
		//primero compruebo que no haya ningun elemento null
		
		while(aux.hasNext()) {
			
			if(aux.next() == null) throw new IllegalArgumentException(); //se deja de insertar por que uno de los elementos es null
		}
		
		//despues inserto el elemento en la ultima pos
		
		aux = elements.iterator();
	
		while(aux.hasNext()) {
			this.insert(aux.next());
		}
		
		return 0;
	}

	/**
	 * Inserta los elementos de un array en el Ã¡rbol.
	 *  si alguno es 'null', NO INSERTA NINGUNO
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements elementos a insertar.
	 * @return numero de elementos insertados en el arbol (los que ya estÃ¡n no los inserta)
	 */
	public int insert(T ... elements) {
		//	 si alguno es 'null', ni siquiera se comienza a insertar (no inserta ninguno)
	    // TODO Implementar el mÃ©todo
		
		//igual que el anterior pero recorriendo todos los elements
		
		for(T aux = ""; aux < elements.length; aux++) {
			
			if(aux == null) throw new IllegalArgumentException();
		}

		for(T aux = ""; aux < elements.length; aux++) {
			
			this.insert(aux);
			
		}
		
		return 0;
	}
	
	/**
	 * Inserta (como hoja) un nuevo elemento en el Ã¡rbol de bÃºsqueda.
	 * 
	 * Debe asignarse valor a su atributo father (referencia a su nodo padre o null si es la raÃ­z)
	 * 
	 * No se permiten elementos null. Si element es null dispara excepciÃ³n: IllegalArgumentException 
	 *  Si el elemento ya existe en el Ã¡rbol NO lo inserta.
	 * 
	 * @param element
	 *            valor a insertar.
	 * @return true si se pudo insertar (no existia ese elemento en el arbol, false en caso contrario
	 * @throws IllegalArgumentException si element es null           
	 */
	public boolean insert(T element) {
    //	TODO Implementar el mÃ©todo
		
		if(element == null) throw new IllegalArgumentException();
		
		if(contains(element)) return false;
		
		if(isEmpty()) {
			
			setContent(element);
			setRightBST(new BinarySearchTreeImpl<T>(this));
			setLeftBST(new BinarySearchTreeImpl<T>(this));
			
		} else {
			
			if(getContent().compareTo(element) > 0) {
				getLeftBST().insert(element);
				
			} else {
				getRightBST().insert(element);
			}
		}
	
		return true;
	
	}
	

	/**
	 * Busca el elemento en el Ã¡rbol.
	 * 
	 * No se permiten elementos null. 
	 * 
	 * @param element   valor a buscar.
	 * @return true si el elemento estÃ¡ en el Ã¡rbol, false en caso contrario          
	 */
	public boolean contains(T element) {
		// TODO Implementar el mÃ©todo
		
		if(element == null) throw new IllegalArgumentException();
		
		if(isEmpty()) return false;
		
		//esta en la rama izquierda
		if(getContent().compareTo(element) > 0) {
			return getLeftBST().contains(element);
		//esta en la rama derecha	
		} else if(getContent().compareTo(element) < 0) {
			return getRightBST().contains(element);
			
		} else {
			return true;
		}
	}
	
	/**
	 * Elimina los valores en un array del Ã¡rbol.
	 * O todos o ninguno; si alguno es 'null'o no lo contiene el Ã¡rbol, no se eliminarÃ¡ ningÃºn elemento
	 * 
	 * @throws NoSuchElementException si alguno de los elementos a eliminar no estÃ¡ en el Ã¡rbol           
	 */
	public void remove(T ... elements) {
	    // TODO Implementar el mÃ©todo
		
		for(T aux = ""; aux < elements.length; aux++) {
			
			if(aux == null) throw new IllegalArgumentException();
			
			if(!contains(aux)) throw new NoSuchElementException();
		}
		
		for(T aux = ""; aux < elements.length; aux++) {
			
			this.remove(aux);
			
		}
	}
	
	/**
	 * Elimina un elemento del Ã¡rbol.
	 * 
	 * Si el elemento tiene dos hijos, se tomarÃ¡ el criterio de sustituir el elemento por
	 *  el menor de sus mayores y eliminar el menor de los mayores.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no estÃ¡ en el Ã¡rbol           
	 */
	public void remove(T element) {
		// TODO Implementar el mÃ©todo
		
		if(element == null) throw new IllegalArgumentException();
		
		if(!contains(element)) throw new NoSuchElementException();
		
		if(getContent().compareTo(element) == 0) {
			
			//raiz
			if(this.getLeftBST().getContent() == null && this.getRightBST().getContent() == null) {
				
				if(father == null) {

					setContent(null);
					setLeftBST(null);
					setRightBST(null);
					
				} else {
					
					//rama izquierda
					if(father.getLeftBST().getContent() != null && father.getLeftBST().getContent().compareTo(this.getContent()) == 0) {
						
						setContent(null);
						father.setLeftBST(new BinarySearchTreeImpl<T>(this));
						
					//rama derecha
					} else {
						
						setContent(null);
						father.setRightBST(new BinarySearchTreeImpl<T>(this));
						
					}
				}
				
			} else {
				
				if(this.getRightBST().getContent() == null) {

					if(father.getLeftBST().getContent() != null) {
						father.setLeftBST(this.getLeftBST());	
						
					} else {
						father.setRightBST(this.getLeftBST());		
					}
						
					this.getLeftBST().father = father;
					
					setContent(null);
					setLeftBST(null);
					setRightBST(null);
					
				} else {
					BinarySearchTreeImpl<T> min = this.minimo();

					setContent(min.getContent());
					min.remove(min.getContent());
				}
			}
			
		} else if(getContent().compareTo(element) > 0) {
			
			getLeftBST().remove(element);
				
		} else {
			
			getRightBST().remove(element);
			
		}
	}
	
	public BinarySearchTreeImpl<T> min() {

		if(this.getRightBST().getContent() != null && this.getRightBST().getRightBST().getContent() != null) {
			
			return this.getRightBST().min();
			
		} else if(this.getLeftBST().getContent() != null)  {
			
			return this.getLeftBST().min();
			
		} else {
			
			return this;
		}
	}
	
	/**
	 * Importante: Solamente se puede recorrer el Ã¡rbol una vez
	 * 
	 * Etiqueta cada nodo con la etiqueta "height" y el valor correspondiente a la altura del nodo.
	 * 
	 * Por ejemplo, sea un Ã¡rbol "A":
	 * 
	 * {10, {5, {2, âˆ…, âˆ…}, âˆ…}, {20, {15, âˆ…, âˆ…}, {30, âˆ…, âˆ…}}}
	 * 
     * 10
     * |  5
     * |  |  2
     * |  |  |  âˆ…
     * |  |  |  âˆ…
     * |  |  âˆ…
     * |  20
     * |  |  15
     * |  |  |  âˆ…
     * |  |  |  âˆ… 
     * |  |  30
     * |  |  |  âˆ…
     * |  |  |  âˆ…
     * 
	 * 
	 * el Ã¡rbol quedarÃ­a etiquetado:
	 * 
	 *   {10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], âˆ…, âˆ…}, âˆ…},
	 *               {20 [(height, 2)], {15 [(height, 3)], {12 [(height, 4)], âˆ…, âˆ…}, âˆ…}, âˆ…}}
	 * 
	 */
	public void tagHeight() {
	// TODO implementar el mÃ©todo
		
		int lvl = 1;
		
		if(this.getLeftBST().getContent() != null && this.getRightBST().getContent() != null) {

			setTag("height", lvl);
			
			this.getLeftBST().tagHeightAux(lvl + 1);
			this.getRightBST().tagHeightAux(lvl + 1);
			
		} else if(getLeftBST().getContent() == null && getRightBST().getContent() != null) {
			
			setTag("height", lvl);
			
			this.getRightBST().tagHeightAux(lvl + 1);
			
		} else if(getLeftBST().getContent() != null) {
			
			setTag("height", lvl);
			
			this.getLeftBST().tagHeightAux(lvl + 1);
			
		} else {
			
			setTag("height", lvl);
			
		}
	}
		
	/**
	 * Importante: Solamente se puede recorrer el Ã¡rbol una vez
	 * 
	 * Etiqueta cada nodo con el valor correspondiente al nÃºmero de descendientes que tiene en este Ã¡rbol.
	 * 
	 * Por ejemplo, sea un Ã¡rbol "A":
	 * 
	 * {10, {5, {2, âˆ…, âˆ…}, âˆ…}, {20, {15, âˆ…, âˆ…}, {30, âˆ…, âˆ…}}}
	 * 
     * 10
     * |  5
     * |  |  2
     * |  |  |  âˆ…
     * |  |  |  âˆ…
     * |  |  âˆ…
     * |  20
     * |  |  15
     * |  |  |  âˆ…
     * |  |  |  âˆ… 
     * |  |  30
     * |  |  |  âˆ…
     * |  |  |  âˆ…
     * 
	 * 
	 * el Ã¡rbol quedarÃ­a etiquetado:
	 * 
	 *  {10 [(decendents, 5)], 
	 *       {5 [(decendents, 1)], {2 [(decendents, 0)], âˆ…, âˆ…}, âˆ…}, 
	 *       {20 [(decendents, 2)], {15 [(decendents, 0)], âˆ…, âˆ…}, {30 [(decendents, 0)], âˆ…, âˆ…}}}
	 * 
	 * 
	 */
	public void tagDecendents() {
	   // TODO Implementar el mÃ©todo

		if(!isEmpty()) {
			
			int []aux = new int[1];
			tagDecendentsAux(aux);
		}
	}
	
	public void tagDecendentsAux(int []aux) {
		
		if(this.getLeftBST().getContent() != null && this.getRightBST().getContent() != null) {
			
			aux[0] += 2;
			
			int []aux2 = new int[1];
			int []aux3 = new int[1];
			
			this.getLeftBST().tagDecendentsAux(aux2);
			this.getRightBST().tagDecendentsAux(aux3);

			setTag("decendents", aux2[0] + aux3[0] + aux[0]);
			
		} else if(getLeftBST().getContent() == null && getRightBST().getContent() != null) {
			
			this.getRightBST().tagDecendentsAux(aux);
			
			axu[0] ++;
			setTag("decendents", aux[0]);
			
		} else if(getLeftBST().getContent() != null) {
			
			this.getLeftBST().tagDecendentsAux(aux);
			
			aux[0] ++;
			setTag("decendents", aux[0]);
			
		} else {
			
			setTag("decendents", 0);
		}
	}
	
	/**	
	 * Devuelve un iterador que recorre los elementos del arbol por niveles segÃºn 
         * el recorrido en anchura
	 * 
	 * Por ejemplo, con el Ã¡rbol
	 * 
	 * 		{50, {30, {10, âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 * 
	 * y devolverÃ­a el iterador que recorrerÃ­a los nodos en el orden: 50, 30, 80, 10, 40, 60
	 * 
	 * 		
	 * 
	 * @return iterador para el recorrido en anchura
	 */

	public Iterator<T> iteratorWidth() {
		//	TODO Implementar mÃ©todo
		// puede implementarse creando una lista con el recorrido en anchura de los elementos del Ã¡rbol y devolver el iterador de dicha lista
		
		Collection<T> list = new ArrayList<T>();
		
		Queue<BinarySearchTreeImpl> queue = new LinkedList<BinarySearchTreeImpl>();
		
		queue.add(this);
		
		while(queue.peek() != null) {

			list.add((T) queue.element().getContent());
			
			if(queue.element().getLeftBST().getContent() != null) {
				queue.add(cola.element().getLeftBST());
			}
			
			if(queue.element().getRightBST().getContent() != null) {
				queue.add(cola.element().getRightBST());
			}
			
			queue.remove();
		}
		
		return list.iterator();
	}	
	

	/**
	 * Importante: Solamente se puede recorrer el Ã¡rbol una vez
	 * 
	 * Calcula y devuelve el nÃºmero de nodos que son hijos Ãºnicos 
	 *  y etiqueta cada nodo que sea hijo Ãºnico (no tenga hermano hijo del mismo padre) 
	 *   con la etiqueta "onlySon" y el valor correspondiente a su posiciÃ³n segÃºn el 
	 *   recorrido inorden en este Ã¡rbol. 
	 *   
	 *   La raÃ­z no se considera hijo Ãºnico.
	 * 
	 * Por ejemplo, sea un Ã¡rbol "A", que tiene 3 hijos Ãºnicos, los va etiquetando segÃºn 
	 * su recorrido en inorden. 
	 * 
	 * {10, {5, {2, âˆ…, âˆ…}, âˆ…}, {20, {15, âˆ…, âˆ…}, {30, âˆ…, âˆ…}}}
	 * 
     *
	 * el Ã¡rbol quedarÃ­a etiquetado:
	 * 
	 * {10, {5, {2 [(onlySon, 1)], âˆ…, âˆ…}, âˆ…}, 
	 *      {20, {15 [(onlySon, 3)], {12 [(onlySon, 2)], âˆ…, âˆ…}, âˆ…}, âˆ…}}
	 * 
	 */
	public int tagOnlySonInorder() {
		// TODO Implementar el mÃ©todo

		if(isEmpty()) {
			return 0;
		}	
		
		int [] aux = new int[1];
		tagOnlySonInorderAux(aux);
		
		return aux[0];
	}
	
	public void tagOnlySonInorderAux(int []aux) {
		
		if(this.getLeftBST().getContent() != null && this.getRightBST().getContent() != null) {
			
			this.getLeftBST().tagOnlySonInorderAux(aux);
			this.getRightBST().tagOnlySonInorderAux(aux);
			
		} else if(getLeftBST().getContent() == null && getRightBST().getContent() != null) {
			
			this.getRightBST().tagOnlySonInorderAux(aux);
			
			aux[0] ++;
			getRightBST().setTag("onlySon", aux[0]);
			
		} else if(getLeftBST().getContent() != null) {
			
			this.getLeftBST().tagOnlySonInorderAux(aux);
			
			aux[0] ++;
			getLeftBST().setTag("onlySon", aux[0]);
			
		}
	}
}

