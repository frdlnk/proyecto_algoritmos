/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.ListaEnlazada;

/**
 *
 * @author Isaac
 */
public class ListaEnlazada<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantidad;

    public ListaEnlazada() {
        this.inicio = null;
        this.fin = null;
        this.cantidad = 0;
    }
    
    public int tamanno() {
        return this.cantidad;
    }
    
    public T getInicio() {
        // return this.inicio == null ? null : this.inicio.getDato();
        if (this.inicio == null) {
            return null;
        }
        return this.inicio.getDato();
    }
    
    public T getFin() {
        if (this.fin == null) {
            return null;
        }
        return this.fin.getDato();
    }
    
    public void agregarInicio(T dato) {
        // Si la lista está vacía la cabeza y el nodo apuntan
        //      al nuevo elemento
        // Se crea un método privado que usan ambos agregar
        if (isEmpty()) {
            this.agregarVacio(dato);
        } else {
            this.inicio = new Nodo<>(dato, inicio);
            this.cantidad++;
        }
    }
    
    public void agregarFin(T dato) {
        // Si la lista está vacía la cabeza y el nodo apuntan
        //      al nuevo elemento
        // Se crea un método privado que usan ambos agregar
        if (isEmpty()) {
            this.agregarVacio(dato);
        } else {
            Nodo<T> nuevo = new Nodo<>(dato, null);
            this.fin.setSiguiente(nuevo);
            this.fin = nuevo;
            this.cantidad++;
        }
    }
    
    private void agregarVacio(T dato) {
        // Crea un nuevo elemento y coloca ambos punteros a este
        this.inicio = this.fin = new Nodo<>(dato, null);
        cantidad++;
    }
    
    public boolean isEmpty() {
        return inicio == null;
    }
    
    // Eliminar elementos de la lista
    // Coloca ambos punteros en null
    // La cantidad de elementos es 0
    public void clear() {
        this.inicio = null;
        this.fin = null;
        this.cantidad = 0;
    }
    
    // Eliminar el primer elemento
    // Valida que la lista no esté vacía, en tal caso retorna null
    // Si hay un elemento lo retorna
    // El inicio pasa a ser el siguiente del inicio
    public T eliminarInicio() {
        if (this.isEmpty()) {
            return null;
        }
        Nodo<T> eliminado = this.inicio;
        this.inicio = this.inicio.getSiguiente();
        // Si el inicio es null, el fin también (la lista está vacía)
        if (this.inicio == null) {
            this.fin = null;
        }
        // Reducimos la cantidad
        this.cantidad--;        
        return eliminado.getDato();
    }
    
    // Eliminar el último elemento
    // Si la lista está vacía retorna null
    // Sino debe recorrer hasta el penúltimo elemento y buscar este 
    //   para retornar el siguiente
    // El fin pasa a ser el penúltimo
    public T eliminarFin() {
        // Si la lista está vacía retorna null
        if (isEmpty()) {
            return null;
        }
        // Si el inicio es igual al fin lo retorna
        if (this.inicio == this.fin) {
            Nodo<T> eliminado = this.inicio;
            this.inicio = null;
            this.fin = null;
            this.cantidad--;
            return eliminado.getDato();
        }
        // Debe recorrer desde el inicio hasta el penúltimo
        Nodo<T> eliminado = this.fin;
        Nodo<T> penultimo = this.inicio;
        while (penultimo != null) {
            // Revisa si nodo siguiente apunta a fin
            if (penultimo.getSiguiente() == fin) {
                this.fin = penultimo;
                // Se siguiente el último del fin
                this.fin.setSiguiente(null);
                break;
            }
            penultimo = penultimo.getSiguiente();
        }
        cantidad--;
        return eliminado.getDato();
    }
    
    
    
    @Override
    public String toString() {
        String hilera = "";
        // Concatenar el dato separado por salto de línea \n
        // Obtenemos el valor actual en una variable temporal
        // En un ciclo preguntamos si tiene valor
        // Dentro del ciclo obtenemos el siguiente valor
        Nodo<T> temp = this.inicio;
        while (temp != null) {
            hilera += temp.getDato() + "\n";
            temp = temp.getSiguiente();
        }
        
        // Recorrido con for
//        for (Nodo<T> temp2 = this.inicio;       // Declaración
//                temp2 != null;                  // Condición de parada
//                temp2 = temp2.getSiguiente()) { // Incremento
//            hilera += temp2.getDato() + "\n";
//        }

        return hilera;
    }
    
    // Obtiene el elemento en la posicion indicada
    // Si el indice es menor a 0 o mayor o igual a la cantidad 
    //  retorna null
    // Variable contador para comparar la posición
    public T obtener(int indice) {
        // Valida que el indice esté en los límites [0,...,cantidad[
        if ((indice < 0) || (indice >= cantidad)) {
            return null;
        }
        // Recorre los elementos incrementando la posición
        Nodo<T> nodo = inicio;
        for (int i = 0; i < cantidad; i++) {
            // Si el contador es igual indice retorna el valor
            if (i == indice) {
                return nodo.getDato();
            }
            nodo = nodo.getSiguiente();
        }
        return null;
    }
    
    // Obtener posición por elemento (indexOf)
    // Si el elemento no existe retorna -1
    public int indexOf(T dato) {
        int indice = 0;
        Nodo nodo = inicio;
        while (nodo != null) {
            if (nodo.getDato().equals(dato)) {
                return indice;
            }
            nodo = nodo.getSiguiente();
            indice++;
        }
        return -1;
    }
    
    // Valida si un elemento existe (contains)
    // Si no existe retorna false
    // Reutiliza el llamado a indexOf
    //     si indexOf != -1  true
    //     sino false
    public boolean contains(T dato) {
        return this.indexOf(dato) != -1;
        // if (this.indexOf(dato) != -1) {
        //     return true;
        // }
        // return false;
    }
    
    // Modifica el contenido de una posición
    // Si el indice es menor a 0 o mayor o igual a la cantidad 
    //  retorna null
    public T set(int indice, T dato) {
        // Valida que el indice esté en los límites [0,...,cantidad[
        if ((indice < 0) || (indice >= cantidad)) {
            return null;
        }
        // Recorremos la lista
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
    
    // Elimina el elemento de la posición indicada
    // Si el indice es menor a 0 o mayor o igual a la cantidad 
    //  retorna null
    // Si la lista está vacía retorna null
    // Retorna el valor eliminado
    public T eliminar(int indice) {
        // Verifica si lista está vacía
        if (isEmpty()) {
            return null;
        }
        // Valida que el indice esté en los límites [0,...,cantidad[
        if ((indice < 0) || (indice >= cantidad)) {
            return null;
        }
        // Si el indice es 0 llama a eliminarInicio
        if (indice == 0) {
            return eliminarInicio();
        }
        // Si el indice es el último elemento (cantidad - 1) llama a
        //  eliminarFin
        if (indice == (cantidad - 1)) {
            return eliminarFin();
        }
        // Recorre desde la posición 1 al penúltimo (cantidad - 2)
        // Inicializa el nodo en el siguiente del inicio
        Nodo<T> nodo = inicio;
        for (int i = 1; i < cantidad - 1; i++) {
            if (i == indice) {
                // Obtiene el valor del siguiente elemento
                T dato = nodo.getSiguiente().getDato();
                Nodo<T> siguiente = nodo.getSiguiente().getSiguiente();
                nodo.setSiguiente(siguiente);
                this.cantidad--;
                return dato;
            }
            nodo = nodo.getSiguiente();
        }
        return null;
    }
}