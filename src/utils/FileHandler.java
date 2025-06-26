package utils;

import Departamento.Departamento;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase para manejar la lectura y escritura de departamentos desde y hacia un archivo.
 * 
 * @author frdlnk
 */
public class FileHandler {

    private final String fileUri;

    public FileHandler(String fileUri) {
        this.fileUri = fileUri;
    }

    public ArrayList<Departamento> getDepartamentosFromFile() throws IOException {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        try (BufferedReader file = new BufferedReader(new FileReader(fileUri))) {
            String currentLine;
            while ((currentLine = file.readLine()) != null) {
                String[] parts = currentLine.split(",");

                if (parts.length < 5) {
                    continue;
                }

                int code = Integer.parseInt(parts[0].trim());
                String nombreDepartamento = parts[1].trim();
                int departamentoPadreCode = Integer.parseInt(parts[2].trim());
                int cantidadEmpleados = Integer.parseInt(parts[3].trim());
                double presupuesto = Double.parseDouble(parts[4].trim().replace(",", "").replace(" ", ""));

                Departamento departamentoPadre = findDepartamentoByCode(departamentos, departamentoPadreCode);

                Departamento departamento = new Departamento(code, nombreDepartamento, departamentoPadre, cantidadEmpleados, presupuesto);
                departamentos.add(departamento);
            }
        }

        return departamentos;
    }

    public void writeDepartamentosToFile(ArrayList<Departamento> departamentos) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileUri))) {
            for (Departamento departamento : departamentos) {
                int padreCode = departamento.getDepartamentoPadre() != null
                        ? departamento.getDepartamentoPadre().getCode()
                        : 0;

                String line = String.join(",",
                        String.valueOf(departamento.getCode()),
                        departamento.getNombreDepartamento(),
                        String.valueOf(padreCode),
                        String.valueOf(departamento.getCantidadEmpleados()),
                        String.valueOf(departamento.getPresupuesto())
                );

                writer.write(line);
                writer.newLine();
            }
        }
    }

    private Departamento findDepartamentoByCode(ArrayList<Departamento> departamentos, int code) {
        for (Departamento departamento : departamentos) {
            if (departamento.getCode() == code) {
                return departamento;
            }
        }
        return null;
    }
}
