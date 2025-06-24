/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Departamento;

/**
 *
 * @author Isaac
 */
public class Departamento {
    int code;
    String nombreDepartamento;
    Departamento departamentoPadre;
    int cantidadEmpleados;
    double presupuesto;

    public Departamento(int code, String nombreDepartamento, Departamento departamentoPadre, int cantidadEmpleados, double presupuesto) {
        this.code = code;
        this.nombreDepartamento = nombreDepartamento;
        this.departamentoPadre = departamentoPadre;
        this.cantidadEmpleados = cantidadEmpleados;
        this.presupuesto = presupuesto;
    }

    public Departamento() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Departamento getDepartamentoPadre() {
        return departamentoPadre;
    }

    public void setDepartamentoPadre(Departamento departamentoPadre) {
        this.departamentoPadre = departamentoPadre;
    }

    public int getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    public void setCantidadEmpleados(int cantidadEmpleados) {
        this.cantidadEmpleados = cantidadEmpleados;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    @Override
    public String toString() {
        return "Departamento{" + "code=" + code + ", nombreDepartamento=" + nombreDepartamento + ", departamentoPadre=" + departamentoPadre + ", cantidadEmpleados=" + cantidadEmpleados + ", presupuesto=" + presupuesto + '}';
    }
  
    
}
