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

            ArbolOrganizacional<Departamento> arbol = LoadDepartments.getInstance().getArbolOrganizacional();
            Nodo<Departamento> nodoRaiz = arbol.buscar(departamentoRaiz.getCode()); //SE AGREGO EL GETCODE
            if (nodoRaiz == null) {
                JOptionPane.showMessageDialog(null, "Nodo raíz no encontrado en el árbol.");
                return;
            }

            JTree tree = new JTree(construirTreeNode(nodoRaiz));
            tree.setShowsRootHandles(true);

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
