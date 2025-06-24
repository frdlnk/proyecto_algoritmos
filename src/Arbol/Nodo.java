

package Arbol;

import common.ListaEnlazada.ListaEnlazada;

/**
 *
 * @author brand
 */
public class Nodo<T extends Comparable<T>> {
        
    private T valor;
    private ListaEnlazada<Nodo<T>> hijos;

    public Nodo(T valor) {
        this.valor = valor;
        this.hijos = new ListaEnlazada<>();
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public ListaEnlazada<Nodo<T>> getHijos() {
        return hijos;
    }

    public void agregarHijo(Nodo<T> hijo) {
        this.hijos.agregarFin(hijo);
    }
    

    @Override
    public String toString() {
        return valor.toString();
    }
}

