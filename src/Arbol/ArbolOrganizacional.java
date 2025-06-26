package Arbol;

import Departamento.Departamento;
import common.ListaEnlazada.ListaEnlazada;

/**
 * Clase que representa un árbol organizacional jerárquico compuesto por nodos que contienen
 * objetos del tipo {@link Departamento}. Permite insertar, eliminar, buscar y recorrer nodos.
 * 
 * @param <T> Tipo genérico que extiende Comparable, aunque actualmente está ligado a {@link Departamento}.
 * 
 * @author brand
 */
public class ArbolOrganizacional<T extends Comparable<T>> {

    /**
     * Nodo raíz del árbol.
     */
    private Nodo<Departamento> raiz;

    /**
     * Cantidad de nodos (departamentos) en el árbol.
     */
    private int size;

    /**
     * Verifica si el árbol está vacío.
     *
     * @return true si no tiene nodos, false en caso contrario.
     */
    public boolean isEmpty() {
        return raiz == null;
    }

    /**
     * Inserta un nuevo departamento en el árbol.
     * Si el árbol está vacío, el nuevo nodo será la raíz.
     * Si no, se busca el nodo padre por su código y se inserta el nuevo nodo como hijo.
     *
     * @param departamento Departamento a insertar.
     * @param padre Departamento padre al que se agregará el nuevo nodo como hijo.
     * @throws Exception Si se intenta insertar en un árbol vacío con un padre no nulo.
     */
    public void insertar(Departamento departamento, Departamento padre) throws Exception {
        Nodo<Departamento> nuevoNodo = new Nodo<>(departamento);

        if (isEmpty()) {
            if (padre == null) {
                raiz = nuevoNodo;
                size++;
            } else {
                throw new Exception("Arbol vacio");
            }
        } else {
            insertarRecursivo(raiz, nuevoNodo, padre != null ? padre.getCode() : 0);
            size++;
        }
    }

    /**
     * Método auxiliar recursivo para insertar un nodo bajo el nodo con el código padre especificado.
     *
     * @param actual Nodo actual durante la búsqueda.
     * @param nuevo Nodo nuevo a insertar.
     * @param codigoPadre Código del nodo padre.
     */
    private void insertarRecursivo(Nodo<Departamento> actual, Nodo<Departamento> nuevo, int codigoPadre) {
        if (actual.getValor().getCode() == codigoPadre) {
            actual.agregarHijo(nuevo);
        }
        for (int i = 0; i < actual.getHijos().tamanno(); i++) {
            Nodo<Departamento> hijo = actual.getHijos().obtener(i);
            insertarRecursivo(hijo, nuevo, codigoPadre);
        }
    }

    /**
     * Busca un nodo en el árbol por el código del departamento.
     *
     * @param codigo Código del departamento.
     * @return Nodo correspondiente o null si no se encuentra.
     */
    public Nodo<Departamento> buscar(int codigo) {
        return buscarRecursivo(raiz, codigo);
    }

    /**
     * Método recursivo auxiliar para buscar un nodo por código.
     *
     * @param actual Nodo actual.
     * @param codigo Código buscado.
     * @return Nodo encontrado o null.
     */
    private Nodo<Departamento> buscarRecursivo(Nodo<Departamento> actual, int codigo) {
        if (actual == null) {
            return null;
        }
        if (actual.getValor().getCode() == codigo) {
            return actual;
        }
        for (int i = 0; i < actual.getHijos().tamanno(); i++) {
            Nodo<Departamento> nodoEncontrado = buscarRecursivo(actual.getHijos().obtener(i), codigo);
            if (nodoEncontrado != null) {
                return nodoEncontrado;
            }
        }
        return null;
    }

    /**
     * Verifica si un nodo con un código específico existe en el árbol.
     *
     * @param codigo Código del departamento.
     * @return true si existe, false si no.
     */
    public boolean contains(int codigo) {
        return buscar(codigo) != null;
    }

    /**
     * Retorna la cantidad total de departamentos en el árbol.
     *
     * @return Número de nodos.
     */
    public int size() {
        return size;
    }

    /**
     * Elimina un nodo con el código especificado.
     * Si es la raíz, elimina todo el árbol.
     * Si no, elimina el nodo y reubica sus hijos al nivel superior.
     *
     * @param codigo Código del departamento a eliminar.
     */
    public void eliminar(int codigo) {
        if (raiz != null && raiz.getValor().getCode() == codigo) {
            raiz = null;
            size--;
        } else {
            eliminarRecursivo(raiz, codigo);
            size--;
        }
    }

    /**
     * Método recursivo auxiliar para eliminar un nodo del árbol.
     * Reubica los hijos del nodo eliminado en su padre.
     *
     * @param actual Nodo actual.
     * @param codigo Código del nodo a eliminar.
     */
    private void eliminarRecursivo(Nodo<Departamento> actual, int codigo) {
        for (int i = 0; i < actual.getHijos().tamanno(); i++) {
            Nodo<Departamento> hijo = actual.getHijos().obtener(i);

            if (hijo.getValor().getCode() == codigo) {
                ListaEnlazada<Nodo<Departamento>> hijosParaReubicar = hijo.getHijos();
                for (int j = 0; j < hijosParaReubicar.tamanno(); j++) {
                    actual.agregarHijo(hijosParaReubicar.obtener(j));
                }
                actual.getHijos().eliminar(i);
                recalcularTotales(raiz); // Recalcula datos después de eliminar
                return;
            } else {
                eliminarRecursivo(hijo, codigo);
            }
        }
    }

    /**
     * Recalcula recursivamente el total de empleados y presupuesto de cada departamento.
     *
     * @param actual Nodo actual.
     * @return Total de empleados bajo este nodo.
     */
    private int recalcularTotales(Nodo<Departamento> actual) {
        if (actual == null) {
            return 0;
        }

        int totalEmpleados = actual.getValor().getCantidadEmpleados();
        double totalPresupuesto = actual.getValor().getPresupuesto();

        for (int i = 0; i < actual.getHijos().tamanno(); i++) {
            Nodo<Departamento> hijo = actual.getHijos().obtener(i);
            totalEmpleados += recalcularTotales(hijo);
            totalPresupuesto += hijo.getValor().getPresupuesto();
        }

        actual.getValor().setCantidadEmpleados(totalEmpleados);
        actual.getValor().setPresupuesto(totalPresupuesto);

        return totalEmpleados;
    }

    /**
     * Muestra la estructura del árbol en consola, usando sangrías según el nivel de profundidad.
     */
    public void recorrer() {
        recorrerRecursivo(raiz, 0);
    }

    /**
     * Método recursivo auxiliar para recorrer y mostrar el árbol.
     *
     * @param actual Nodo actual.
     * @param nivel Nivel de profundidad actual, usado para sangrías.
     */
    private void recorrerRecursivo(Nodo<Departamento> actual, int nivel) {
        if (actual == null) {
            return;
        }
        for (int i = 0; i < nivel; i++) {
            System.out.print("\t");
        }
        System.out.println(actual.getValor().getNombreDepartamento());
        for (int i = 0; i < actual.getHijos().tamanno(); i++) {
            recorrerRecursivo(actual.getHijos().obtener(i), nivel + 1);
        }
    }

    /**
     * Busca un departamento por su código y retorna el objeto {@link Departamento}.
     *
     * @param codigo Código del departamento.
     * @return Departamento encontrado o null si no existe.
     */
    public Departamento buscarPorCodigo(int codigo) {
        Nodo<Departamento> nodo = buscarNodoPorCodigo(raiz, codigo);
        return nodo != null ? nodo.getValor() : null;
    }

    /**
     * Método recursivo auxiliar para buscar el nodo por código.
     *
     * @param nodo Nodo actual.
     * @param codigo Código del departamento.
     * @return Nodo encontrado o null.
     */
    private Nodo<Departamento> buscarNodoPorCodigo(Nodo<Departamento> nodo, int codigo) {
        if (nodo == null) {
            return null;
        }

        if (nodo.getValor().getCode() == codigo) {
            return nodo;
        }

        for (int i = 0; i < nodo.getHijos().tamanno(); i++) {
            Nodo<Departamento> encontrado = buscarNodoPorCodigo(nodo.getHijos().obtener(i), codigo);
            if (encontrado != null) {
                return encontrado;
            }
        }

        return null;
    }
}
