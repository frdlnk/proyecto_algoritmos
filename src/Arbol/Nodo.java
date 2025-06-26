package Arbol;

import common.ListaEnlazada.ListaEnlazada;

/**
 * Clase que representa un nodo genérico para un árbol n-ario. Cada nodo puede
 * tener múltiples hijos y un valor de tipo {@code T}.
 *
 * @param <T> Tipo de dato almacenado en el nodo. Debe implementar
 * {@link Comparable}.
 *
 * @author brand
 */
public class Nodo<T extends Comparable<T>> {

    /**
     * Valor que contiene el nodo.
     */
    private T valor;

    /**
     * Lista de hijos del nodo. Cada hijo es otro nodo de tipo T.
     */
    private ListaEnlazada<Nodo<T>> hijos;

    /**
     * Constructor que inicializa el nodo con un valor y una lista vacía de
     * hijos.
     *
     * @param valor Valor que contendrá el nodo.
     */
    public Nodo(T valor) {
        this.valor = valor;
        this.hijos = new ListaEnlazada<>();
    }

    /**
     * Obtiene el valor del nodo.
     *
     * @return Valor almacenado en el nodo.
     */
    public T getValor() {
        return valor;
    }

    /**
     * Establece el valor del nodo.
     *
     * @param valor Nuevo valor que se asignará al nodo.
     */
    public void setValor(T valor) {
        this.valor = valor;
    }

    /**
     * Retorna la lista de hijos del nodo.
     *
     * @return Lista enlazada de nodos hijos.
     */
    public ListaEnlazada<Nodo<T>> getHijos() {
        return hijos;
    }

    /**
     * Agrega un nuevo hijo al nodo.
     *
     * @param hijo Nodo que se agregará como hijo.
     */
    public void agregarHijo(Nodo<T> hijo) {
        this.hijos.agregarFin(hijo);
    }

    /**
     * Devuelve una representación en texto del valor del nodo.
     *
     * @return Cadena que representa el valor del nodo.
     */
    @Override
    public String toString() {
        return valor.toString();
    }
}
