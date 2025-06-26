/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.ListaEnlazada;

/**
 * Representa un nodo de una lista enlazada simple.
 * Cada nodo contiene un dato genérico y una referencia al siguiente nodo.
 *
 * @param <T> tipo de dato que almacena el nodo.
 * 
 * @author Matias
 */
public class Nodo<T> {
    private T dato;
    private Nodo<T> siguiente;

    /**
     * Crea un nodo con un dato y una referencia al siguiente nodo.
     * 
     * @param dato el valor que contiene el nodo.
     * @param siguiente la referencia al siguiente nodo en la lista.
     */
    public Nodo(T dato, Nodo<T> siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }

    /**
     * Retorna el dato almacenado en el nodo.
     * 
     * @return el dato contenido en este nodo.
     */
    public T getDato() {
        return dato;
    }

    /**
     * Establece un nuevo dato en el nodo.
     * 
     * @param dato el nuevo valor a almacenar.
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Retorna el siguiente nodo enlazado.
     * 
     * @return el nodo siguiente o null si no hay más nodos.
     */
    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el nodo siguiente al actual.
     * 
     * @param siguiente referencia al nuevo nodo siguiente.
     */
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}
