package ule.edi.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * Un mundo es un Ã¡rbol binario. 
 * En cada nodo de un mundo se almacena una lista de entidades, cada una con su tipo y
 * cardinalidad. Ver {@link Entity}.
 * 
 * Si se codifica "bajar por la izquierda" como "0" y
 * "bajar por la derecha" como "1", el camino desde un 
 * nodo N hasta un nodo M (en uno de sus sub-Ã¡rboles) serÃ¡ la
 * cadena de 0s y 1s que indica cÃ³mo llegar desde N hasta M.
 *
 * Se define tambiÃ©n el camino vacÃ­o desde un nodo N hasta
 * Ã©l mismo, como cadena vacÃ­a.
 * 
 * Por ejemplo, el mundo:
 * 
 * {[F(1)], {[F(1)], {[D(2), P(1)], âˆ…, âˆ…}, {[C(1)], âˆ…, âˆ…}}, âˆ…}
 * 
 * o lo que es igual:
 * 
 * [F(1)]
 * |  [F(1)]
 * |  |  [D(2), P(1)]
 * |  |  |  âˆ…
 * |  |  |  âˆ…
 * |  |  [C(1)]
 * |  |  |  âˆ…
 * |  |  |  âˆ…
 * |  âˆ…
 * 
 * contiene un bosque (forest) en "", otro en "0", dos dragones y una princesa en "00" y
 * un castillo en "01".
 * @param <T>
 * 
 */
public class World extends AbstractBinaryTreeADT<LinkedList<Entity>> {
	
	/**
	 * Devuelve el mundo al que se llega al avanzar a la izquierda.
	 * 
	 * @return
	 */
	protected World getWorldLeft() {
		
		return (World) leftSubtree;
	}

	protected void setWorldLeft(World left) {
		
		this.leftSubtree = left;
	}
	
	/**
	 * Devuelve el mundo al que se llega al avanzar a la derecha.
	 * 
	 * @return
	 */
	protected World getWorldRight() {
		
		return (World) rightSubtree;
	}

	protected void setWorldRight(World right) {
		
		this.rightSubtree = right;
	}
	
	
	public static World createEmptyWorld() {
		return new World();
	}
	
	/**
	 * Inserta la entidad indicada en este Ã¡rbol.
	 * 
	 * La inserciÃ³n se produce en el nodo indicado por la direcciÃ³n; todos
	 * los nodos recorridos para alcanzar aquÃ©l que no estÃ©n creados se
	 * inicializarÃ¡n con una entidad 'unknown'.
	 * 
	 * La direcciÃ³n se supondrÃ¡ correcta, compuesta de cero o mÃ¡s 0s y 1s.
	 * 
	 * Dentro de la lista del nodo indicado, la inserciÃ³n de nuevas entidades
	 * se realizarÃ¡ al final, como Ãºltimo elemento.
	 * 
	 * Por ejemplo, en un Ã¡rbol vacÃ­o se pide insertar un 'dragÃ³n' en la
	 * direcciÃ³n "00". El resultado final serÃ¡:
	 * 
     * [U(1)]
     * |  [U(1)]
     * |  |  [D(1)]
     * |  |  |  âˆ…
     * |  |  |  âˆ…
     * |  |  âˆ…
     * |  âˆ…
     * 
     * La direcciÃ³n "" indica la raÃ­z, de forma que insertar un 'guerrero' en
     * "" en el Ã¡rbol anterior genera:
     * 
     * [U(1), W(1)]
     * |  [U(1)]
     * |  |  [D(1)]
     * |  |  |  âˆ…
     * |  |  |  âˆ…
     * |  |  âˆ…
     * |  âˆ…
     * 
     * La inserciÃ³n tiene en cuenta la cardinalidad, de forma que al volver a
     * insertar un guerrero en "" se tiene:
     * 
     * [U(1), W(2)]
     * |  [U(1)]
     * |  |  [D(1)]
     * |  |  |  âˆ…
     * |  |  |  âˆ…
     * |  |  âˆ…
     * |  âˆ…
     *  
	 * @param address direcciÃ³n donde insertar la entidad.
	 * @param e entidad a insertar.
	 */
	public void insert(String address, Entity e) {
		
		//TODO implementar el metodo
		
		int size = address.length();
		
		if(this.getContent() == null) {
			
			setWorldLeft(createEmptyWorld());
			setWorldRight(createEmptyWorld());
			
			if(size == 0) {
				LinkedList<Entity> list = new LinkedList<Entity>();
				list.add(e);
				setContent(list);
				
			} else {
				LinkedList<Entity> list = new LinkedList<Entity>();
				list.add(new Entity(Entity.UNKNOWN));
				setContent(list);
				
				if(address.charAt(0) == '0') {
					getWorldLeft().insert(address.substring(1), e);
					
				} else {
					getWorldRight().insert(address.substring(1), e);
				}
			}
			
		} else {
			//si esta
			if(size == 0) {
				
				LinkedList<Entity> list = this.getContent();
				
				boolean found = false;
				Iterator<Entity> iter = list.iterator();
					
				while(iter.hasNext()) {
						
					Entity element = it.next();
					if(element.equals(e)) {
						element.setCount(element.getCount() + e.getCount());
						found = true;
					}
				}
				
				if(encontrado == false) {
					list.add(e);
				}
				
			} else {
				if(address.charAt(0) == '0') {
					
					getWorldLeft().insert(address.substring(1), e);
					
				} else {
					
					getWorldRight().insert(address.substring(1), e);
				}
			}
		}
	}

	/**
	 * Indica cuÃ¡ntas entidades del tipo hay en este mundo (en el Ã¡rbol completo).
	 * 
	 * @param type tipo de entidad.
	 * @return cuÃ¡ntas entidades de ese tipo hay en este Ã¡rbol.
	 */
	
	public long countEntity(int type) {
		// TODO Implementar el mÃ©todo	
		
		if(this.getContent() == null) {
			return 0;
			
		} else {
			
			LinkedList<Entity> list = this.getContent();
			Iterator<Entity> iter = list.iterator();
			
			long counter = 0;
			
			while(iter.hasNext()) {
					
				Entity element = iter.next();
				
				if(element.is(type)) {
					
					counter = element.getCount();
				}
			}
			
			counter += getWorldLeft().countEntity(type) + getWorldRight().countEntity(type):
			
			return counter;
		}
	}
	
	
	

	/**
	 * Indica cuantas princesas accesibles hay en el Ã¡rbol.
	 *  AdemÃ¡s introduce en una lista de Strings las direcciones a los nodos en las que se encuentran dichas princesas. 
	 *  Se considera princesa accesible :
     *      - a toda princesa que en su camino desde la raÃ­z hasta el nodo que la contiene 
     *      no hay ningÃºn dragÃ³n, 
     *      - o si hay algÃºn dragÃ³n en el camino desde la raÃ­z hasta la princesa,  se cumpla que 
     *      desde el Ãºltimo dragÃ³n hasta la princesa haya al menos un castillo que la proteja 
     *      (podrÃ­an estar en el mismo nodo, tanto la princesa, como el castillo y el dragÃ³n).
     *      
     *      Ejemplo: Dado el Ã¡rbol arbol1, siendo
     *       arbol1.toString()={[U(1)], {[U(1)], âˆ…, {[D(3)], {[P(4)], âˆ…, âˆ…}, âˆ…}},
     *                                  {[U(1)], {[P(7)], âˆ…, âˆ…}, {[C(1), D(1)], âˆ…, {[P(1)], âˆ…, âˆ…}}}}
     *     ( se ha insertado:  un dragÃ³n y un castillo en â€œ11â€�, 7 princesas en â€œ10â€�, un dragÃ³n en â€œ01â€�, 
     *     4 princesas en â€œ010â€� y 1 princesa en â€œ111â€�) 
     *     
     *     Al llamar a arbol1.countAccesiblePrincess(lista) siendo lista una lista vacÃ­a, 
     *     devolverÃ¡ 8 y la lista contendrÃ¡ (â€œ10â€�, â€œ111â€�).
	 * @param List<String> donde dejarÃ¡ las direcciones a los nodos que contienen princesas accesibles.
	 * @return el nÃºmero de princesas accesibles situadas 
	 */
	public long countAccesiblePrincess(List<String> lista){
		// TODO IMPLEMENTAR EL MÃ‰TODO
		
		//arbol vacio
		
		if(this.getContent() == null) {
			return 0;
			
		} else {
			
			LinkedList<Entity> list = this.getContent();
			Iterator<Entity> iter = list.iterator();
			
			long counter = 0;
			long princess = 0;
			
			boolean dragon = false, castle = false;
			
			while(iter.hasNext()) {
					
				Entity element = iter.next();
				
				if(element.is(Entity.DRAGON)) {
					dragon = true;
					
				} else if(element.is(Entity.CASTLE)) {
					castle = true;
					
				} else if(element.is(Entity.PRINCESS)) {
					princess = element.getCount();
				}
			}

			if(dragon == false || (dragon == true && castle == true)) {
				counter = princess;		
				
			} else if(dragon == true) {
				return 0;
			}
			
			counter += getWorldLeft().countAccesiblePrincess(list) + getWorldRight().countAccesiblePrincess(list);
			
			return counter;
		}
	}	
	
	
	
	@Override
	public String toString() {
		
		//si no esta vacio
		
		if (!isEmpty()) {
			//	Construye el resultado de forma eficiente
			StringBuffer output = new StringBuffer();
				
			//	RaÃ­z: Ordena la lista de entidades alfabeticamente
			
			Collections.sort(content);
			output.append("{" + content.toString());
			
			if (!tags.isEmpty()) {
				output.append(" [");
				
				List<String> setKey = new LinkedList<String>(tags.keySet());
				
				Collections.sort(setKey);
				
				for (String k = ""; k < setKey.size(); k++) {
					
					output.append("(" + k + ", " + tags.get(k) + "), ");
					
				}
				
				output.delete(result.length() - 2, result.length());
				output.append("]");
			}
			
			//	Y cada sub-Ã¡rbol
			for (int i = 0; i < getMaxDegree(); i++) {
				
				output.append(", " + getSubtree(i).toString());
				
			}
			//	Cierra la "}" de este Ã¡rbol
			output.append("}");
			
			return output.toString();
			
		} else {
			//si esta vacio 
			return AbstractTreeADT.EMPTY_TREE_MARK;
			
		}
	}

	
}
