package ui;

import Arbol.ArbolOrganizacional;
import Departamento.Departamento;
import javax.swing.JOptionPane;

/**
 *
 * @author brand
 */
public class EliminarDepartamento {
    
    ArbolOrganizacional<Departamento> arbolDepartamentos =  LoadDepartments.getInstance().getArbolOrganizacional();

    public void eliminarDep(){
         try {
        int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el codigo del departamento a eliminar:"));
        if (codigo != 0) {
            if (arbolDepartamentos.buscar(codigo) != null) {
                arbolDepartamentos.eliminar(codigo);
                JOptionPane.showMessageDialog(null, "Departamento eliminado exitosamente\nLos hijos fueron reubicados al padre.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el departamento con el codigo ingresado.");
            }
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Codigo invalido. Debe ingresar un numero entero.");
    }
    }
    
    
}
