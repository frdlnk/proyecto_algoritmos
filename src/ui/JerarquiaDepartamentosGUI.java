package ui;

import Arbol.Nodo;
import Departamento.Departamento;
import Arbol.ArbolOrganizacional;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Clase encargada de mostrar gráficamente la jerarquía de departamentos
 * a partir de un nodo raíz utilizando un JTree dentro de un JDialog.
 *
 * @author Isaac
 */
public class JerarquiaDepartamentosGUI {

    /**
     * Muestra un árbol jerárquico de departamentos a partir de un código
     * de departamento ingresado por el usuario.
     */
    public void mostrarJerarquia() {
        String inputCode = JOptionPane.showInputDialog("Ingrese el código del departamento raíz:");
        if (inputCode == null || inputCode.trim().isEmpty()) {
            return;
        }

        try {
            int code = Integer.parseInt(inputCode);

            // Buscar el departamento correspondiente
            Departamento departamentoRaiz = null;
            for (Departamento d : LoadDepartments.getInstance().getDepartamentos()) {
                if (d.getCode() == code) {
                    departamentoRaiz = d;
                    break;
                }
            }

            if (departamentoRaiz == null) {
                JOptionPane.showMessageDialog(null, "Departamento no encontrado.");
                return;
            }

            // Buscar nodo correspondiente en el árbol
            ArbolOrganizacional<Departamento> arbol = LoadDepartments.getInstance().getArbolOrganizacional();
            Nodo<Departamento> nodoRaiz = arbol.buscar(departamentoRaiz.getCode());
            if (nodoRaiz == null) {
                JOptionPane.showMessageDialog(null, "Nodo raíz no encontrado en el árbol.");
                return;
            }

            // Construcción del árbol gráfico (JTree)
            JTree tree = new JTree(construirTreeNode(nodoRaiz));
            tree.setShowsRootHandles(true);

            // Mostrar el árbol en una ventana emergente
            JDialog dialog = new JDialog();
            dialog.setTitle("Jerarquía de Departamentos");
            dialog.setModal(true);
            dialog.add(new JScrollPane(tree));
            dialog.setSize(400, 500);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código inválido. Ingrese un número.");
        }
    }

    /**
     * Método recursivo que construye un nodo de árbol visual a partir
     * de un nodo lógico del árbol organizacional.
     *
     * @param nodo Nodo del árbol organizacional
     * @return Nodo visual para insertar en el JTree
     */
    private DefaultMutableTreeNode construirTreeNode(Nodo<Departamento> nodo) {
        String textoNodo = String.format("%s (Código: %d)",
                nodo.getValor().getNombreDepartamento(),
                nodo.getValor().getCode()
        );

        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(textoNodo);

        for (int i = 0; i < nodo.getHijos().tamanno(); i++) {
            treeNode.add(construirTreeNode(nodo.getHijos().obtener(i)));
        }

        return treeNode;
    }
}
