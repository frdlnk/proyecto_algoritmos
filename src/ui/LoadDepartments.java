package ui;

import Departamento.Departamento;
import utils.FileHandler;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoadDepartments {

    public void loadDepartmentsFromFile() {
        File selectedFile = seleccionarArchivo();

        if (selectedFile == null) {
            mostrarMensaje("Carga cancelada por el usuario.");
            return;
        }

        List<Departamento> departamentos = cargarDepartamentosDesdeArchivo(selectedFile.getAbsolutePath());

        if (departamentos != null) {
            mostrarDepartamentos(departamentos);
            mostrarMensaje("Departamentos cargados exitosamente.");
        }
    }

    private File seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione el archivo de departamentos");

        int seleccionUsuario = fileChooser.showOpenDialog(null);
        if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
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

    private void mostrarDepartamentos(List<Departamento> departamentos) {
        for (Departamento departamento : departamentos) {
            System.out.println(departamento);
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
