/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import Arbol.ArbolOrganizacional;
import Departamento.Departamento;
import javax.swing.JOptionPane;

/**
 * Clase para consultar información detallada de un departamento específico
 * dentro del árbol organizacional.
 * 
 * El usuario ingresa un código y se muestra un resumen detallado del departamento.
 * 
 * Autor: Isaac
 */
public class ConsultarDepartamentos {

    /**
     * Inicia el proceso de consulta de departamento solicitando al usuario
     * un código y mostrando la información correspondiente si se encuentra.
     *
     * @param arbol El árbol organizacional con los departamentos cargados.
     */
    public void consultarDepartamento(ArbolOrganizacional<Departamento> arbol) {
        String inputCode = JOptionPane.showInputDialog("Ingrese el código del departamento a consultar:");
        if (inputCode == null || inputCode.trim().isEmpty()) {
            return;
        }

        try {
            int codigo = Integer.parseInt(inputCode);
            Departamento depto = buscarDepartamento(arbol, codigo);

            if (depto == null) {
                JOptionPane.showMessageDialog(null, "Departamento no encontrado.");
                return;
            }

            String mensaje = generarReporteDepartamento(arbol, depto);
            JOptionPane.showMessageDialog(null, mensaje, "Información del Departamento", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código inválido. Ingrese un número.");
        }
    }

    /**
     * Busca un departamento por código dentro del árbol organizacional.
     *
     * @param arbol El árbol organizacional.
     * @param codigo El código del departamento a buscar.
     * @return El objeto Departamento si se encuentra, o null si no existe.
     */
    private static Departamento buscarDepartamento(ArbolOrganizacional<Departamento> arbol, int codigo) {
        return arbol.buscarPorCodigo(codigo);
    }

    /**
     * Genera un resumen textual de los datos de un departamento incluyendo
     * su código, nombre, cantidad de empleados, presupuesto, hijos directos,
     * nivel jerárquico y total de subdepartamentos.
     *
     * @param arbol El árbol organizacional.
     * @param depto El departamento del cual se genera el reporte.
     * @return Una cadena con toda la información relevante.
     */
    private static String generarReporteDepartamento(ArbolOrganizacional<Departamento> arbol, Departamento depto) {
        StringBuilder reporte = new StringBuilder();

        reporte.append(String.format("Código: %d\n", depto.getCode()));
        reporte.append(String.format("Nombre: %s\n", depto.getNombreDepartamento()));
        reporte.append(String.format("Empleados: %d\n", depto.getCantidadEmpleados()));
        reporte.append(String.format("Presupuesto: %.2f\n", depto.getPresupuesto()));

        reporte.append(String.format("Departamentos hijos directos: %d\n", contarHijosDirectos(depto)));
        reporte.append(String.format("Nivel en jerarquía: %d\n", calcularNivelJerarquico(arbol, depto)));
        reporte.append(String.format("Total departamentos bajo su gestión: %d\n", contarSubdepartamentos(depto)));

        return reporte.toString();
    }

    /**
     * Cuenta el número de hijos directos del departamento.
     *
     * @param depto El departamento padre.
     * @return Número de hijos directos.
     */
    private static int contarHijosDirectos(Departamento depto) {
        return depto.getHijos().size();
    }

    /**
     * Calcula el nivel jerárquico de un departamento dentro del árbol.
     * La raíz tiene nivel 0, sus hijos nivel 1, y así sucesivamente.
     *
     * @param arbol El árbol organizacional.
     * @param depto El departamento del cual se calculará el nivel.
     * @return El nivel jerárquico del departamento.
     */
    private static int calcularNivelJerarquico(ArbolOrganizacional<Departamento> arbol, Departamento depto) {
        int nivel = 0;
        Departamento actual = depto;

        while (actual.getDepartamentoPadre() != null) {
            nivel++;
            actual = actual.getDepartamentoPadre();
        }

        return nivel;
    }

    /**
     * Cuenta todos los subdepartamentos descendientes (en profundidad)
     * de un departamento dado.
     *
     * @param depto El departamento padre.
     * @return Total de subdepartamentos bajo su gestión (todos los niveles).
     */
    private static int contarSubdepartamentos(Departamento depto) {
        int total = 0;
        for (Departamento hijo : depto.getHijos()) {
            total += 1 + contarSubdepartamentos(hijo); // 1 por el hijo directo + recursión
        }
        return total;
    }
}
