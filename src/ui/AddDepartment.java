package ui;

import Arbol.ArbolOrganizacional;
import Arbol.Nodo;
import Departamento.Departamento;
import javax.swing.JOptionPane;

/**
 *
 * @author brand
 */
public class AddDepartment {

    ArbolOrganizacional<Departamento> arbolDepartamentos = LoadDepartments.getInstance().getArbolOrganizacional();

    public void addDep() throws Exception {

        try {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo departamento:");
            if (nombre == null || nombre.trim().isEmpty()) {
                return;
            }

            // Validación simple
            if (nombre.contains(",")) {
                JOptionPane.showMessageDialog(null, "El nombre no puede contener comas.");
                return;
            }

            String padreStr = JOptionPane.showInputDialog(null, "Ingrese el codigo del departamento padre:");
            if (padreStr == null || padreStr.trim().isEmpty()) {
                return;
            }
            int codigoPadre = Integer.parseInt(padreStr);

            Nodo<Departamento> nodoPadre = arbolDepartamentos.buscar(codigoPadre);
            if (nodoPadre == null) {
                JOptionPane.showMessageDialog(null, "Departamento padre no encontrado.");
                return;
            }

            // Generar código automático
            int hijosActuales = nodoPadre.getHijos().tamanno();
            if (hijosActuales >= 9) {
                JOptionPane.showMessageDialog(null, "Este departamento ya tiene 9 subdepartamentos. No se puede agregar mas.");
                return;
            }
            int nuevoCodigo = Integer.parseInt(codigoPadre + "" + (hijosActuales + 1));

            // Datos de empleados y presupuesto
            String empleadosStr = JOptionPane.showInputDialog(null, "Ingrese cantidad de empleados (solo si es hoja):");
            int empleados = (empleadosStr == null || empleadosStr.isEmpty()) ? 0 : Integer.parseInt(empleadosStr);

            String presupuestoStr = JOptionPane.showInputDialog(null, "Ingrese presupuesto (solo si es hoja):");
            double presupuesto = (presupuestoStr == null || presupuestoStr.isEmpty()) ? 0.0 : Double.parseDouble(presupuestoStr);

            Departamento nuevo = new Departamento(nuevoCodigo, nombre, nodoPadre.getValor(), empleados, presupuesto);
            arbolDepartamentos.insertar(nuevo, nodoPadre.getValor());

            JOptionPane.showMessageDialog(null, "Departamento agregado exitosamente con código: " + nuevoCodigo);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en los datos numericos ingresados.");
        }

    }
}
