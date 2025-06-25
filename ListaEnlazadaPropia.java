/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.ListaEnlazada;

/**
 *
 * @author matia
 */

import Departamento.Departamento;

public class ListaEnlazadaPropia {

    private Nodo inicio;

    private class Nodo {
        Departamento dato;
        Nodo siguiente;

        Nodo(Departamento dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public ListaEnlazadaPropia() {
        this.inicio = null;
    }

    public void insertar(Departamento departamento) {
        Nodo nuevo = new Nodo(departamento);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo actual = inicio;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public boolean eliminar(Departamento departamento) {
        if (inicio == null) return false;

        if (inicio.dato.getCode() == departamento.getCode()) {
            inicio = inicio.siguiente;
            return true;
        }

        Nodo actual = inicio;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato.getCode() == departamento.getCode()) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public Departamento buscar(int codigo) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.dato.getCode() == codigo) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public void recorrer() {
        Nodo actual = inicio;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }
}

