package com.universidad.csvlib.ejemplos;

import com.universidad.csvlib.exception.CSVProcessingException;
import com.universidad.csvlib.model.CSVTable;
import com.universidad.csvlib.processor.CSVProcessor;
import com.universidad.csvlib.processor.CSVProcessorDecorator;

import java.io.File;
import java.util.List;

/**
 * EJEMPLO DE EXTENSION (codigo propio, fuera de la libreria base).
 *
 * Decorador que, luego de cargar el archivo, convierte todos los
 * valores de la tabla a mayusculas. Observese que no modifica ninguna
 * clase de la libreria: solo hereda de CSVProcessorDecorator y
 * sobrescribe el metodo "cargar" para modificar el resultado final.
 */
public class MayusculasProcessorDecorator extends CSVProcessorDecorator {

    public MayusculasProcessorDecorator(CSVProcessor componente) {
        super(componente);
    }

    @Override
    public CSVTable cargar(File archivo) throws CSVProcessingException {
        CSVTable tabla = super.cargar(archivo);
        aMayusculas(tabla);
        return tabla;
    }

    private void aMayusculas(CSVTable tabla) {
        List<List<String>> filas = tabla.getRows();
        for (int f = 0; f < filas.size(); f++) {
            List<String> fila = filas.get(f);
            for (int c = 0; c < fila.size(); c++) {
                tabla.setCell(f, c, fila.get(c).toUpperCase());
            }
        }
    }
}
