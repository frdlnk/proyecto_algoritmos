/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Departamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa un Departamento dentro de una estructura organizacional.
 * Contiene atributos como código, nombre, departamento padre, número de empleados, presupuesto
 * y una lista de departamentos hijos.
 * 
 * Implementa {@link Comparable} para permitir ordenamiento por código.
 * 
 * @author Isaac
 */
public class Departamento implements Comparable<Departamento> {

    /** Código único del departamento. */
    private final int code;

    /** Nombre del departamento. */
    private final String nombreDepartamento;

    /** Referencia al departamento padre (puede ser null). */
    private Departamento departamentoPadre;

    /** Número de empleados asignados al departamento. */
    private int cantidadEmpleados;

    /** Presupuesto asignado al departamento. */
    private double presupuesto;

    /** Lista de departamentos hijos. */
    private List<Departamento> hijos = new ArrayList<>();

    /**
     * Constructor para crear un nuevo departamento.
     *
     * @param code Código único (debe ser positivo).
     * @param nombreDepartamento Nombre del departamento (no puede ser nulo ni vacío).
     * @param departamentoPadre Departamento padre (puede ser null).
     * @param cantidadEmpleados Número de empleados (no negativo).
     * @param presupuesto Presupuesto asignado (no negativo).
     * @throws IllegalArgumentException Si el código es negativo o el nombre es inválido.
     */
    public Departamento(int code, String nombreDepartamento, Departamento departamentoPadre,
                        int cantidadEmpleados, double presupuesto) {
        if (code < 0) {
            throw new IllegalArgumentException("Código debe ser positivo.");
        }
        if (nombreDepartamento == null || nombreDepartamento.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede estar vacío.");
        }

        this.code = code;
        this.nombreDepartamento = nombreDepartamento.trim();
        this.departamentoPadre = departamentoPadre;
        this.cantidadEmpleados = Math.max(0, cantidadEmpleados);
        this.presupuesto = Math.max(0.0, presupuesto);
    }

    /**
     * Obtiene el código del departamento.
     * 
     * @return Código entero.
     */
    public int getCode() {
        return code;
    }

    /**
     * Obtiene el nombre del departamento.
     * 
     * @return Nombre como cadena.
     */
    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    /**
     * Retorna el departamento padre, si existe.
     * 
     * @return Departamento padre o null.
     */
    public Departamento getDepartamentoPadre() {
        return departamentoPadre;
    }

    /**
     * Retorna la cantidad de empleados.
     * 
     * @return Número de empleados.
     */
    public int getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    /**
     * Retorna el presupuesto asignado.
     * 
     * @return Presupuesto en formato decimal.
     */
    public double getPresupuesto() {
        return presupuesto;
    }

    /**
     * Establece un nuevo departamento padre.
     * 
     * @param departamentoPadre El nuevo padre.
     */
    public void setDepartamentoPadre(Departamento departamentoPadre) {
        this.departamentoPadre = departamentoPadre;
    }

    /**
     * Asigna una nueva cantidad de empleados (no negativa).
     * 
     * @param cantidadEmpleados Nueva cantidad de empleados.
     */
    public void setCantidadEmpleados(int cantidadEmpleados) {
        this.cantidadEmpleados = Math.max(0, cantidadEmpleados);
    }

    /**
     * Establece un nuevo presupuesto (no negativo).
     * 
     * @param presupuesto Nuevo presupuesto.
     */
    public void setPresupuesto(double presupuesto) {
        this.presupuesto = Math.max(0.0, presupuesto);
    }

    /**
     * Verifica si el código de este departamento es igual al buscado.
     * 
     * @param codigoBuscado Código a verificar.
     * @return true si coincide, false si no.
     */
    public boolean tieneCodigo(int codigoBuscado) {
        return this.code == codigoBuscado;
    }

    /**
     * Compara departamentos por su código.
     *
     * @param otro Departamento con el que se desea comparar.
     * @return Resultado de la comparación (negativo, 0, positivo).
     */
    @Override
    public int compareTo(Departamento otro) {
        return Integer.compare(this.code, otro.code);
    }

    /**
     * Verifica si dos departamentos son iguales por su código.
     * 
     * @param obj Objeto a comparar.
     * @return true si tienen el mismo código, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Departamento that = (Departamento) obj;
        return this.code == that.code;
    }

    /**
     * Genera un código hash basado en el código del departamento.
     * 
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    /**
     * Representación textual del departamento.
     * 
     * @return Cadena con información básica del departamento.
     */
    @Override
    public String toString() {
        return String.format(
                "Departamento[code=%d, nombre=%s, padre=%s, empleados=%d, presupuesto=%.2f]",
                code,
                nombreDepartamento,
                (departamentoPadre != null ? departamentoPadre.getNombreDepartamento() : "Ninguno"),
                cantidadEmpleados,
                presupuesto
        );
    }

    /**
     * Agrega un departamento como hijo del actual.
     *
     * @param hijo Departamento hijo a agregar.
     * @throws IllegalArgumentException Si el hijo es nulo, es el mismo que el actual, o ya fue agregado.
     */
    public void agregarHijo(Departamento hijo) {
        if (hijo == null) {
            throw new IllegalArgumentException("El departamento hijo no puede ser nulo");
        }
        if (hijo.equals(this)) {
            throw new IllegalArgumentException("Un departamento no puede ser hijo de sí mismo");
        }
        if (hijos.contains(hijo)) {
            throw new IllegalArgumentException("Este departamento ya es hijo");
        }

        hijo.setDepartamentoPadre(this);
        hijos.add(hijo);
    }

    /**
     * Obtiene una lista de los departamentos hijos.
     * 
     * @return Copia de la lista de hijos.
     */
    public List<Departamento> getHijos() {
        return new ArrayList<>(hijos);
    }
}
