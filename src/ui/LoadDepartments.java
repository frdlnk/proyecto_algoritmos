package ui;

import Arbol.ArbolOrganizacional;
import Departamento.Departamento;
import utils.FileHandler;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Clase singleton encargada de cargar la información de departamentos desde un archivo,
 * gestionar la lista de departamentos y construir el árbol organizacional.
 * 
 * Permite seleccionar el archivo, cargar departamentos, y armar la jerarquía en memoria.
 * 
 * @author Isaac
 */
public class LoadDepartments {

    private static LoadDepartments instance;
    private List<Departamento> departamentos;
    private ArbolOrganizacional<Departamento> arbolDepartamentos = new ArbolOrganizacional<>();

    /**
     * Constructor privado para implementar el patrón Singleton.
     */
    private LoadDepartments() {
    }

    /**
     * Obtiene la única instancia de LoadDepartments.
     * 
     * @return instancia única de LoadDepartments
     */
    public static LoadDepartments getInstance() {
        if (instance == null) {
            instance = new LoadDepartments();
        }
        return instance;
    }

    /**
     * Establece el árbol organizacional.
     * 
     * @param arbol árbol organizacional de departamentos
     */
    public void setArbolOrganizacional(ArbolOrganizacional<Departamento> arbol) {
        this.arbolDepartamentos = arbol;
    }

    /**
     * Método principal para iniciar la carga de departamentos desde un archivo seleccionado por el usuario.
     * Si la carga es exitosa, construye el árbol organizacional.
     */
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

    /**
     * Inserta los departamentos cargados en el árbol organizacional,
     * relacionando padres e hijos según la información de cada departamento.
     */
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

        // Actualizar referencias al padre real para cada departamento
        for (Departamento d : departamentos) {
            if (d.getDepartamentoPadre() != null) {
                int codigoPadre = d.getDepartamentoPadre().getCode();
                Departamento padreReal = mapaDepartamentos.get(codigoPadre);
                d.setDepartamentoPadre(padreReal);
            }
        }

        // Insertar departamentos en el árbol
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

    /**
     * Muestra un diálogo para que el usuario seleccione un archivo.
     * 
     * @return archivo seleccionado o null si se cancela
     */
    private File seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione el archivo de departamentos");
        int seleccionUsuario = fileChooser.showOpenDialog(null);
        return (seleccionUsuario == JFileChooser.APPROVE_OPTION) ? fileChooser.getSelectedFile() : null;
    }

    /**
     * Carga la lista de departamentos desde el archivo especificado.
     * 
     * @param rutaArchivo ruta del archivo a cargar
     * @return lista de departamentos o null si ocurre un error
     */
    private List<Departamento> cargarDepartamentosDesdeArchivo(String rutaArchivo) {
        FileHandler fileHandler = new FileHandler(rutaArchivo);
        try {
            return fileHandler.getDepartamentosFromFile();
        } catch (IOException | NumberFormatException ex) {
            mostrarMensajeError("Error al cargar el archivo: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Muestra un mensaje informativo en pantalla.
     * 
     * @param mensaje texto del mensaje
     */
    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    /**
     * Muestra un mensaje de error en pantalla.
     * 
     * @param mensaje texto del mensaje de error
     */
    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Obtiene la lista de departamentos cargados.
     * 
     * @return lista de departamentos
     */
    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    /**
     * Obtiene el árbol organizacional construido.
     * 
     * @return árbol organizacional de departamentos
     */
    public ArbolOrganizacional<Departamento> getArbolOrganizacional() {
        return arbolDepartamentos;
    }

}
