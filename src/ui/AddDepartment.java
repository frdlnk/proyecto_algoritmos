package ui;

import Arbol.ArbolOrganizacional;
import Arbol.Nodo;
import Departamento.Departamento;
import javax.swing.JOptionPane;

/**
 * Clase encargada de gestionar la creación e inserción de nuevos
 * departamentos en el árbol organizacional mediante una interfaz de usuario.
 * 
 * Se accede al árbol de departamentos por medio del singleton LoadDepartments.
 * 
 * Autor original: brand
 */
public class AddDepartment {

    // Árbol organizacional compartido por la aplicación
    ArbolOrganizacional<Departamento> arbolDepartamentos = LoadDepartments.getInstance().getArbolOrganizacional();

    /**
     * Método que lanza un conjunto de ventanas de diálogo para permitir al usuario
     * crear un nuevo departamento e insertarlo en el árbol organizacional.
     * 
     * @throws Exception si ocurre un error durante la inserción en el árbol.
     */
    public void addDep() throws Exception {
        try {
            // Solicitar nombre del nuevo departamento
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo departamento:");
            if (nombre == null || nombre.trim().isEmpty()) {
                return;
            }

            // Validación: el nombre no debe contener comas
            if (nombre.contains(",")) {
                JOptionPane.showMessageDialog(null, "El nombre no puede contener comas.");
                return;
            }

            // Solicitar el código del departamento padre
            String padreStr = JOptionPane.showInputDialog(null, "Ingrese el código del departamento padre:");
            if (padreStr == null || padreStr.trim().isEmpty()) {
                return;
            }
            int codigoPadre = Integer.parseInt(padreStr);

            // Buscar nodo del departamento padre
            Nodo<Departamento> nodoPadre = arbolDepartamentos.buscar(codigoPadre);
            if (nodoPadre == null) {
                JOptionPane.showMessageDialog(null, "Departamento padre no encontrado.");
                return;
            }

            // Verificar si ya hay 9 hijos para este nodo padre
            int hijosActuales = nodoPadre.getHijos().tamanno();
            if (hijosActuales >= 9) {
                JOptionPane.showMessageDialog(null, "Este departamento ya tiene 9 subdepartamentos. No se puede agregar más.");
                return;
            }

            // Generar un nuevo código concatenando el código del padre con el número de hijo siguiente
            int nuevoCodigo = Integer.parseInt(codigoPadre + "" + (hijosActuales + 1));

            // Solicitar información adicional si el nodo es hoja (cantidad de empleados y presupuesto)
            String empleadosStr = JOptionPane.showInputDialog(null, "Ingrese cantidad de empleados (solo si es hoja):");
            int empleados = (empleadosStr == null || empleadosStr.isEmpty()) ? 0 : Integer.parseInt(empleadosStr);

            String presupuestoStr = JOptionPane.showInputDialog(null, "Ingrese presupuesto (solo si es hoja):");
            double presupuesto = (presupuestoStr == null || presupuestoStr.isEmpty()) ? 0.0 : Double.parseDouble(presupuestoStr);

            // Crear y agregar el nuevo departamento
            Departamento nuevo = new Departamento(nuevoCodigo, nombre, nodoPadre.getValor(), empleados, presupuesto);
            arbolDepartamentos.insertar(nuevo, nodoPadre.getValor());

            JOptionPane.showMessageDialog(null, "Departamento agregado exitosamente con código: " + nuevoCodigo);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en los datos numéricos ingresados.");
        }
    }
}
