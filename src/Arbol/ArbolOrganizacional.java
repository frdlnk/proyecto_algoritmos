package Arbol;

import Departamento.Departamento;
import common.ListaEnlazada.ListaEnlazada;

/**
 *
 * @author brand
 */
public class ArbolOrganizacional<T extends Comparable<T>> {

    private Nodo<Departamento> raiz;
    private int size;

    public boolean isEmpty() {
        return raiz == null;
    }

    public void insertar(Departamento departamento, int codigoPadre) throws Exception {
        Nodo<Departamento> nuevoNodo = new Nodo<>(departamento);

        if (isEmpty()) {

            if (codigoPadre == 0) {
                raiz = nuevoNodo;
                size++;
            } else {
                throw new Exception("Arbol vacio");
            }

        } else {
            insertarRecursivo(raiz, nuevoNodo, codigoPadre);
            size++;
        }
    }

    private void insertarRecursivo(Nodo<Departamento> actual, Nodo<Departamento> nuevo, int codigoPadre) {
        if (actual.getValor().getCode() == codigoPadre) {
            actual.getValor().agregarHijo(nuevo.getValor());
            actual.agregarHijo(nuevo);
        }
        for (int i = 0; i < actual.getHijos().tamanno(); i++) { //hasta el tamano de la lista enlazada
            Nodo<Departamento> hijo = actual.getHijos().obtener(i);
            insertarRecursivo(hijo, nuevo, codigoPadre);
        }
    }

    public Nodo<Departamento> buscar(int codigo) {
        return buscarRecursivo(raiz, codigo);
    }

    private Nodo<Departamento> buscarRecursivo(Nodo<Departamento> actual, int codigo) {
        if (actual == null) {
            return null;
        }
        if (actual.getValor().getCode() == codigo) {
            return actual;
        }
        for (int i = 0; i < actual.getHijos().tamanno(); i++) { //hasta el tamano de la lista enlazada
            Nodo<Departamento> nodoEncontrado = buscarRecursivo(actual.getHijos().obtener(i), codigo);
            if (nodoEncontrado != null) {
                return nodoEncontrado;
            }
        }

        return null;
    }

    public boolean contains(int codigo) {
        return buscar(codigo) != null;
    }

    public int size() {
        return size;
    }

    public void eliminar(int codigo) {
        if (raiz.getValor().getCode() == codigo) {
            raiz = null;
            size--;
        }
        eliminarRecursivo(raiz, codigo);
        size--;
    }

    //Se puede optimizar creando un metodo en el nodo donde elimine un elemento de la listaEnlazada
    private void eliminarRecursivo(Nodo<Departamento> actual, int codigo) {
        for (int i = 0; i < actual.getHijos().tamanno(); i++) {
            Nodo<Departamento> hijo = actual.getHijos().obtener(i);

            if (hijo.getValor().getCode() == codigo) {
                ListaEnlazada<Nodo<Departamento>> hijosParaReubicar = hijo.getHijos();
                for (int j = 0; j < hijosParaReubicar.tamanno(); j++) {
                    actual.agregarHijo(hijosParaReubicar.obtener(j));
                }
                actual.getHijos().eliminar(i);
                recalcularTotales(raiz); //recalcula los empleados y el presupuesto despues de eliminar

            } else {
                eliminarRecursivo(hijo, codigo);
            }

        }
    }

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

    public void recorrer() {
        recorrerRecursivo(raiz, 0);
    }

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

}
