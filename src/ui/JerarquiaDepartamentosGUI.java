package ui;

import Arbol.Nodo;
import Departamento.Departamento;
import Arbol.ArbolOrganizacional;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class JerarquiaDepartamentosGUI {

    public void mostrarJerarquia() {
        String inputCode = JOptionPane.showInputDialog("Ingrese el código del departamento raíz:");
        if (inputCode == null || inputCode.trim().isEmpty()) {
            return;
        }

        try {
            int code = Integer.parseInt(inputCode);

            // Obtener el departamento con ese código desde la lista cargada
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

            // Obtener árbol y buscar el nodo raíz
            ArbolOrganizacional<Departamento> arbol = LoadDepartments.getInstance().getArbolOrganizacional();
            Nodo<Departamento> nodoRaiz = arbol.buscar(departamentoRaiz);
            if (nodoRaiz == null) {
                JOptionPane.showMessageDialog(null, "Nodo raíz no encontrado en el árbol.");
                return;
            }

            // Construir y mostrar el JTree
            JTree tree = new JTree(construirTreeNode(nodoRaiz));
            tree.setShowsRootHandles(true);

            JFrame frame = new JFrame("Jerarquía de Departamentos");
            frame.add(new JScrollPane(tree));
            frame.setSize(400, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código inválido. Ingrese un número.");
        }
    }

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
