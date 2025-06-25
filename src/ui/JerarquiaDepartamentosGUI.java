/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Isaac
 */
import Arbol.ArbolOrganizacional;
import Arbol.Nodo;
import Departamento.Departamento;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class JerarquiaDepartamentosGUI {
    private ArbolOrganizacional<Departamento> arbolDepartamentos;

    public JerarquiaDepartamentosGUI(ArbolOrganizacional<Departamento> arbolDepartamentos) {
        this.arbolDepartamentos = arbolDepartamentos;
    }

    public void mostrarJerarquia() {

        String inputCode = JOptionPane.showInputDialog("Ingrese el código del departamento raíz:");
        if (inputCode == null || inputCode.trim().isEmpty()) return;

        try {
            int code = Integer.parseInt(inputCode);
            
            Nodo<Departamento> nodoRaiz = arbolDepartamentos.buscar(new Departamento(code, "", null, 0, 0.0));
            if (nodoRaiz == null) {
                JOptionPane.showMessageDialog(null, "Departamento no encontrado.");
                return;
            }

         
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

