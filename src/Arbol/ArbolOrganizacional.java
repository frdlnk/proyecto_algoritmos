package Arbol;

import org.w3c.dom.Node;

/**
 *
 * @author brand
 */
public class ArbolOrganizacional<T extends Comparable<T>> {

    private Nodo<T> raiz;
    private int size;

    public boolean isEmpty() {
        return raiz == null;
    }

    public void insertar(T valor, T valorPadre) throws Exception {
        Nodo<T> nuevoNodo = new Nodo<>(valor);

        if (isEmpty()) {

            if (valorPadre == null) {
                raiz = nuevoNodo;
                size++;
            } else {
                throw new Exception("Arbol vacio");
            }

        } else {
            insertarRecursivo(raiz, nuevoNodo, valor);
            size++;
        }
    }

    private void insertarRecursivo(Nodo<T> actual, Nodo<T> nuevo, T padre) {
        if (actual.getValor().equals(padre)) {
            actual.agregarHijo(nuevo);
        }
        for (int i = 0; i < actual.getHijos().tamanno(); i++) { //hasta el tamano de la lista enlazada
            Nodo<T> hijo = actual.getHijos().obtener(i);
            insertarRecursivo(hijo, nuevo, padre);
        }
    }

    public Nodo<T> buscar(T valor) {
        return buscarRecursivo(raiz, valor);
    }

    private Nodo<T> buscarRecursivo(Nodo<T> actual, T valor) {
        if (actual == null) {
            return null;
        }
        if (actual.getValor().equals(valor)) {
            return actual;
        }
        for (int i = 0; i < actual.getHijos().tamanno(); i++) { //hasta el tamano de la lista enlazada
            Nodo<T> NodoEncontrado = buscarRecursivo(actual.getHijos().obtener(i), valor);
            if (NodoEncontrado != null) {
                return NodoEncontrado;
            }
        }

        return null;
    }

    public boolean contains(T valor) {
        return buscar(valor) != null;
    }
    
    
    public int size(){
        return size;
    }
    
    public void eliminar(T valor){
        if (raiz.getHijos().equals(valor)) {
            raiz = null;
            size--;
        }
        eliminarRecursivo(raiz, valor);
        size--;
    }
    
    //Se puede optimizar creando un metodo en el nodo donde elimine un elemento de la listaEnlazada
     private void eliminarRecursivo(Nodo<T> actual, T valor) { 
        for (int i = 0; i < actual.getHijos().tamanno(); i++) {
            Nodo<T> hijo = actual.getHijos().obtener(i);
            
            if (hijo.getValor().equals(valor)) {
                actual.getHijos().eliminar(i);  
            } else {
                eliminarRecursivo(hijo, valor); 
            }
            
        }
    }
     
     
}
