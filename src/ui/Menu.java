package ui;


import javax.swing.JOptionPane;

public class Menu {

    private static boolean salir = false;
    
    private static LoadDepartments loadDepartments = new LoadDepartments();
    
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

            if (seleccion == null) {
                salir = true;
            } else {
                switch (seleccion.charAt(0)) {
                    case '1':
                           loadDepartments.loadDepartmentsFromFile();
                        break;
                    case '2':
                        JOptionPane.showMessageDialog(null, "Estás en: Agregar un departamento");
                        break;
                    case '3':
                        JOptionPane.showMessageDialog(null, "Estás en: Eliminar un departamento");
                        break;
                    case '4':
                        JOptionPane.showMessageDialog(null, "Estás en: Consultar los datos de un departamento");
                        break;
                    case '5':
                        JOptionPane.showMessageDialog(null, "Estás en: Mostrar la jerarquía de un departamento");
                        break;
                    case '6':
                        salir = true;
                        JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                        break;
                }
            }
        }
    
        
    }
}