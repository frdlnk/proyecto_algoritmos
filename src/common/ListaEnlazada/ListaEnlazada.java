/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.ListaEnlazada;

/**
 * Implementación genérica de una lista enlazada simple.
 * Proporciona métodos para insertar, eliminar, acceder y modificar elementos.
 * 
 * @param <T> Tipo de dato almacenado en la lista.
 * 
 * @author Matias
 */
public class ListaEnlazada<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantidad;

    /**
     * Constructor que inicializa una lista vacía.
     */
    public ListaEnlazada() {
        this.inicio = null;
        this.fin = null;
        this.cantidad = 0;
    }

    /**
     * Retorna la cantidad de elementos en la lista.
     * 
     * @return número de elementos.
     */
    public int tamanno() {
        return this.cantidad;
    }

    /**
     * Obtiene el primer elemento de la lista.
     * 
     * @return el primer dato o null si la lista está vacía.
     */
    public T getInicio() {
        return (this.inicio == null) ? null : this.inicio.getDato();
    }

    /**
     * Obtiene el último elemento de la lista.
     * 
     * @return el último dato o null si la lista está vacía.
     */
    public T getFin() {
        return (this.fin == null) ? null : this.fin.getDato();
    }

    /**
     * Agrega un nuevo dato al inicio de la lista.
     * 
     * @param dato el dato a agregar.
     */
    public void agregarInicio(T dato) {
        if (isEmpty()) {
            this.agregarVacio(dato);
        } else {
            this.inicio = new Nodo<>(dato, inicio);
            this.cantidad++;
        }
    }

    /**
     * Agrega un nuevo dato al final de la lista.
     * 
     * @param dato el dato a agregar.
     */
    public void agregarFin(T dato) {
        if (isEmpty()) {
            this.agregarVacio(dato);
        } else {
            Nodo<T> nuevo = new Nodo<>(dato, null);
            this.fin.setSiguiente(nuevo);
            this.fin = nuevo;
            this.cantidad++;
        }
    }

    /**
     * Agrega un único elemento a una lista vacía.
     * 
     * @param dato el dato a agregar.
     */
    private void agregarVacio(T dato) {
        this.inicio = this.fin = new Nodo<>(dato, null);
        cantidad++;
    }

    /**
     * Verifica si la lista está vacía.
     * 
     * @return true si no contiene elementos, false en caso contrario.
     */
    public boolean isEmpty() {
        return inicio == null;
    }

    /**
     * Elimina todos los elementos de la lista.
     */
    public void clear() {
        this.inicio = null;
        this.fin = null;
        this.cantidad = 0;
    }

    /**
     * Elimina el primer elemento de la lista.
     * 
     * @return el dato eliminado o null si la lista está vacía.
     */
    public T eliminarInicio() {
        if (this.isEmpty()) {
            return null;
        }
        Nodo<T> eliminado = this.inicio;
        this.inicio = this.inicio.getSiguiente();
        if (this.inicio == null) {
            this.fin = null;
        }
        this.cantidad--;
        return eliminado.getDato();
    }

    /**
     * Elimina el último elemento de la lista.
     * 
     * @return el dato eliminado o null si la lista está vacía.
     */
    public T eliminarFin() {
        if (isEmpty()) {
            return null;
        }
        if (this.inicio == this.fin) {
            Nodo<T> eliminado = this.inicio;
            this.inicio = null;
            this.fin = null;
            this.cantidad--;
            return eliminado.getDato();
        }
        Nodo<T> eliminado = this.fin;
        Nodo<T> penultimo = this.inicio;
        while (penultimo != null) {
            if (penultimo.getSiguiente() == fin) {
                this.fin = penultimo;
                this.fin.setSiguiente(null);
                break;
            }
            penultimo = penultimo.getSiguiente();
        }
        cantidad--;
        return eliminado.getDato();
    }

    /**
     * Retorna el dato ubicado en el índice especificado.
     * 
     * @param indice posición del elemento.
     * @return el dato en la posición o null si el índice es inválido.
     */
    public T obtener(int indice) {
        if (indice < 0 || indice >= cantidad) {
            return null;
        }

        Nodo<T> nodo = inicio;
        for (int i = 0; i < cantidad; i++) {
            if (i == indice) {
                return nodo.getDato();
            }
            nodo = nodo.getSiguiente();
        }
        return null;
    }

    /**
     * Retorna el índice del primer dato que coincide con el valor dado.
     * 
     * @param dato dato a buscar.
     * @return índice del dato o -1 si no se encuentra.
     */
    public int indexOf(T dato) {
        int indice = 0;
        Nodo<T> nodo = inicio;
        while (nodo != null) {
            if (nodo.getDato().equals(dato)) {
                return indice;
            }
            nodo = nodo.getSiguiente();
            indice++;
        }
        return -1;
    }

    /**
     * Verifica si el dato especificado existe en la lista.
     * 
     * @param dato el dato a buscar.
     * @return true si existe, false si no.
     */
    public boolean contains(T dato) {
        return this.indexOf(dato) != -1;
    }

    /**
     * Reemplaza el dato en la posición especificada con uno nuevo.
     * 
     * @param indice posición donde se desea reemplazar.
     * @param dato nuevo dato a establecer.
     * @return dato anterior o null si el índice es inválido.
     */
    public T set(int indice, T dato) {
        if (indice < 0 || indice >= cantidad) {
            return null;
        }

        Nodo<T> nodo = inicio;
        for (int i = 0; i < cantidad; i++) {
            if (i == indice) {
                T datoPrevio = nodo.getDato();
                nodo.setDato(dato);
                return datoPrevio;
            }
            nodo = nodo.getSiguiente();
        }
        return null;
    }

    /**
     * Elimina el elemento ubicado en la posición indicada.
     * 
     * @param indice índice del elemento a eliminar.
     * @return el dato eliminado o null si el índice es inválido.
     */
    public T eliminar(int indice) {
        if (isEmpty() || indice < 0 || indice >= cantidad) {
            return null;
        }

        if (indice == 0) {
            return eliminarInicio();
        }

        if (indice == cantidad - 1) {
            return eliminarFin();
        }

        Nodo<T> nodo = inicio;
        for (int i = 1; i < cantidad - 1; i++) {
            if (i == indice) {
                T dato = nodo.getSiguiente().getDato();
                Nodo<T> siguiente = nodo.getSiguiente().getSiguiente();
                nodo.setSiguiente(siguiente);
                cantidad--;
                return dato;
            }
            nodo = nodo.getSiguiente();
        }
        return null;
    }

    /**
     * Devuelve una representación en forma de cadena de la lista.
     * 
     * @return los elementos separados por saltos de línea.
     */
    @Override
    public String toString() {
        StringBuilder hilera = new StringBuilder();
        Nodo<T> temp = this.inicio;
        while (temp != null) {
            hilera.append(temp.getDato()).append("\n");
            temp = temp.getSiguiente();
        }
        return hilera.toString();
    }
}
