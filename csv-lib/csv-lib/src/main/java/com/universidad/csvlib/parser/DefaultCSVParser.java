package com.universidad.csvlib.parser;

import com.universidad.csvlib.exception.CSVProcessingException;
import com.universidad.csvlib.model.CSVTable;
import com.universidad.csvlib.model.CSVTableImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementacion por defecto de CSVParser. Interpreta archivos separados
 * por coma, usando la primera linea como cabecera.
 *
 * Su unica responsabilidad es transformar texto plano en un CSVTable
 * (SRP): no imprime nada ni conoce al objeto CSVProcessor que la usa.
 */
public class DefaultCSVParser implements CSVParser {

    private final String separador;

    public DefaultCSVParser() {
        this(",");
    }

    /** Permite reutilizar la misma logica de lectura con otro separador. */
    public DefaultCSVParser(String separador) {
        this.separador = separador;
    }

    @Override
    public CSVTable parsear(File archivo) throws CSVProcessingException {
        if (archivo == null || !archivo.exists()) {
            throw new CSVProcessingException("El archivo no existe: " + archivo);
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String primeraLinea = lector.readLine();
            if (primeraLinea == null) {
                throw new CSVProcessingException("El archivo CSV esta vacio: " + archivo.getName());
            }

            List<String> headers = new ArrayList<>(Arrays.asList(primeraLinea.split(separador, -1)));
            List<List<String>> filas = new ArrayList<>();

            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.isBlank()) {
                    continue;
                }
                filas.add(new ArrayList<>(Arrays.asList(linea.split(separador, -1))));
            }

            return new CSVTableImpl(headers, filas);

        } catch (IOException e) {
            throw new CSVProcessingException("Error leyendo el archivo CSV: " + archivo.getName(), e);
        }
    }
}
