package ui;

import Arbol.ArbolOrganizacional;
import Departamento.Departamento;
import utils.FileHandler;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class LoadDepartments {

    private static LoadDepartments instance;
    private List<Departamento> departamentos;
    private ArbolOrganizacional<Departamento> arbolDepartamentos = new ArbolOrganizacional<>();

    private LoadDepartments() {
    }

    public static LoadDepartments getInstance() {
        if (instance == null) {
            instance = new LoadDepartments();
        }
        return instance;
    }

    public void setArbolOrganizacional(ArbolOrganizacional<Departamento> arbol) {
        this.arbolDepartamentos = arbol;
    }

    public void loadDepartmentsFromFile() {
        File selectedFile = seleccionarArchivo();

        if (selectedFile == null) {
            mostrarMensaje("Carga cancelada por el usuario.");
            return;
        }

        departamentos = cargarDepartamentosDesdeArchivo(selectedFile.getAbsolutePath());

        if (departamentos != null) {
            mostrarMensaje("Departamentos cargados exitosamente.");
            insertarEnArbol();
        }
    }

    private void insertarEnArbol() {
        if (arbolDepartamentos == null) {
            mostrarMensajeError("Árbol organizacional no ha sido inicializado.");
            return;
        }

        if (departamentos == null || departamentos.isEmpty()) {
            mostrarMensajeError("No hay departamentos para insertar.");
            return;
        }

        Map<Integer, Departamento> mapaDepartamentos = new HashMap<>();
        for (Departamento d : departamentos) {
            mapaDepartamentos.put(d.getCode(), d);
        }

        for (Departamento d : departamentos) {
            if (d.getDepartamentoPadre() != null) {
                int codigoPadre = d.getDepartamentoPadre().getCode();
                Departamento padreReal = mapaDepartamentos.get(codigoPadre);
                d.setDepartamentoPadre(padreReal);
            }
        }

        for (Departamento d : departamentos) {
            try {
                Departamento padre = d.getDepartamentoPadre();

                if (padre != null && padre.equals(d)) {
                    mostrarMensajeError("Error: Departamento " + d.getCode()
                            + " no puede ser padre de sí mismo");
                    continue;
                }

                arbolDepartamentos.insertar(d, padre);
            } catch (Exception e) {
                mostrarMensajeError("Error al insertar '" + d.getNombreDepartamento()
                        + "' (Código: " + d.getCode() + "): "
                        + e.getMessage());
                e.printStackTrace();
            }
        }

        mostrarMensaje("Árbol organizacional construido exitosamente. Total nodos: "
                + arbolDepartamentos.size());
    }

    private File seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione el archivo de departamentos");
        int seleccionUsuario = fileChooser.showOpenDialog(null);
        return (seleccionUsuario == JFileChooser.APPROVE_OPTION) ? fileChooser.getSelectedFile() : null;
    }

    private List<Departamento> cargarDepartamentosDesdeArchivo(String rutaArchivo) {
        FileHandler fileHandler = new FileHandler(rutaArchivo);
        try {
            return fileHandler.getDepartamentosFromFile();
        } catch (IOException | NumberFormatException ex) {
            mostrarMensajeError("Error al cargar el archivo: " + ex.getMessage());
            return null;
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public ArbolOrganizacional<Departamento> getArbolOrganizacional() {
        return arbolDepartamentos;
    }

}
