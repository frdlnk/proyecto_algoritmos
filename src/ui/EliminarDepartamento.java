package ui;

import Arbol.ArbolOrganizacional;
import Departamento.Departamento;
import javax.swing.JOptionPane;

/**
 * Clase encargada de eliminar un departamento del árbol organizacional.
 * Si el departamento tiene hijos, estos serán reubicados automáticamente
 * al departamento padre del eliminado.
 * 
 * Autor: brand
 */
public class EliminarDepartamento {

    // Árbol organizacional cargado desde el singleton
    ArbolOrganizacional<Departamento> arbolDepartamentos = LoadDepartments.getInstance().getArbolOrganizacional();

    /**
     * Solicita al usuario el código de un departamento a eliminar y realiza
     * la operación si el código es válido. Se muestra una notificación del
     * resultado de la operación.
     */
    public void eliminarDep() {
        try {
            String entrada = JOptionPane.showInputDialog(null, "Ingrese el código del departamento a eliminar:");
            if (entrada == null || entrada.trim().isEmpty()) {
                return;
            }

            int codigo = Integer.parseInt(entrada);

            // No se permite eliminar la raíz (usualmente código 0 o similar)
            if (codigo != 0) {
                if (arbolDepartamentos.buscar(codigo) != null) {
                    arbolDepartamentos.eliminar(codigo);
                    JOptionPane.showMessageDialog(null,
                        "Departamento eliminado exitosamente.\nLos hijos fueron reubicados al padre.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el departamento con el código ingresado.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede eliminar el nodo raíz del árbol.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código inválido. Debe ingresar un número entero.");
        }
    }
}
