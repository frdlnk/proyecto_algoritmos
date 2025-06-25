/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Departamento;

/**
 *
 * @author Isaac
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Departamento implements Comparable<Departamento> {

    private final int code;
    private final String nombreDepartamento;
    private Departamento departamentoPadre;
    private int cantidadEmpleados;
    private double presupuesto;

    public Departamento(int code, String nombreDepartamento, Departamento departamentoPadre, int cantidadEmpleados, double presupuesto) {
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

    public int getCode() {
        return code;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public Departamento getDepartamentoPadre() {
        return departamentoPadre;
    }

    public int getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setDepartamentoPadre(Departamento departamentoPadre) {
        this.departamentoPadre = departamentoPadre;
    }

    public void setCantidadEmpleados(int cantidadEmpleados) {
        this.cantidadEmpleados = Math.max(0, cantidadEmpleados);
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = Math.max(0.0, presupuesto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Departamento that = (Departamento) obj;
        return this.code == that.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

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

    public boolean tieneCodigo(int codigoBuscado) {
        return this.code == codigoBuscado;
    }

    @Override
    public int compareTo(Departamento otro) {
        return Integer.compare(this.code, otro.code);
    }

    //ISAAC
    private List<Departamento> hijos = new ArrayList<>();

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
    public List<Departamento> getHijos() {
        return new ArrayList<>(hijos);  
    }
    
}
