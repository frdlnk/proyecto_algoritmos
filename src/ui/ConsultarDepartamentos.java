/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import Arbol.ArbolOrganizacional;
import Departamento.Departamento;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Isaac
 */
public class ConsultarDepartamentos {

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

    private static Departamento buscarDepartamento(ArbolOrganizacional<Departamento> arbol, int codigo) {

        return arbol.buscarPorCodigo(codigo);
    }

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

    private static int contarHijosDirectos(Departamento depto) {
        return depto.getHijos().size();
    }

    private static int calcularNivelJerarquico(ArbolOrganizacional<Departamento> arbol, Departamento depto) {
        int nivel = 0;
        Departamento actual = depto;

        while (actual.getDepartamentoPadre() != null) {
            nivel++;
            actual = actual.getDepartamentoPadre();
        }

        return nivel;
    }

    private static int contarSubdepartamentos(Departamento depto) {
        int total = 0;
        for (Departamento hijo : depto.getHijos()) {
            total += 1 + contarSubdepartamentos(hijo);
        }
        return total;
    }
}
