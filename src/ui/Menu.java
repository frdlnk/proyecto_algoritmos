package ui;

import javax.swing.JOptionPane;

public class Menu {

    private static boolean salir = false;
    private static final LoadDepartments loadDepartments = LoadDepartments.getInstance();
    private static final JerarquiaDepartamentosGUI departamentosJerarquia = new JerarquiaDepartamentosGUI();
    private static final EliminarDepartamento eliminarDepartamento = new EliminarDepartamento();
    
    public static void startMenu() {
        while (!salir) {
            String[] opciones = {
                "1. Cargar departamentos de un archivo",
                "2. Agregar un departamento",
                "3. Eliminar un departamento",
                "4. Consultar los datos de un departamento",
                "5. Mostrar la jerarquía de un departamento",
                "6. Salir"
            };

            String seleccion = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione una opción:",
                    "Menú de Departamentos",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            if (seleccion == null || seleccion.startsWith("6")) {
                salir = true;
                JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                continue;
            }

            switch (seleccion.charAt(0)) {
                case '1':
                    loadDepartments.loadDepartmentsFromFile();
                    break;

                case '2':
                case '3':
                    eliminarDepartamento.eliminarDep();
                    break;
                case '4':
                    JOptionPane.showMessageDialog(null, "Funcionalidad aún no implementada.");
                    break;

                case '5':
                    if (loadDepartments.getDepartamentos() == null || loadDepartments.getDepartamentos().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe cargar primero un archivo de departamentos.");
                    } else {
                        departamentosJerarquia.mostrarJerarquia();
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
        }
    }
}
