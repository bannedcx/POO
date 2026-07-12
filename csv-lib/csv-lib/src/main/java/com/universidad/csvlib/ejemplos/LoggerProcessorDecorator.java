package com.universidad.csvlib.ejemplos;

import com.universidad.csvlib.processor.CSVProcessor;
import com.universidad.csvlib.processor.CSVProcessorDecorator;

import java.util.List;

/**
 * EJEMPLO DE EXTENSION (codigo propio, fuera de la libreria base).
 *
 * Decorador que imprime un mensaje de log antes de cada operacion de
 * escritura sobre la tabla (agregar/eliminar fila o columna). Puede
 * combinarse con otros decoradores, como MayusculasProcessorDecorator,
 * envolviendolos entre si (composicion de decoradores).
 */
public class LoggerProcessorDecorator extends CSVProcessorDecorator {

    public LoggerProcessorDecorator(CSVProcessor componente) {
        super(componente);
    }

    @Override
    public void agregarFila(List<String> fila) {
        System.out.println("[LOG] Agregando fila: " + fila);
        super.agregarFila(fila);
    }

    @Override
    public void eliminarFila(int indice) {
        System.out.println("[LOG] Eliminando fila en indice: " + indice);
        super.eliminarFila(indice);
    }

    @Override
    public void agregarColumna(String nombre, List<String> valores) {
        System.out.println("[LOG] Agregando columna: " + nombre);
        super.agregarColumna(nombre, valores);
    }

    @Override
    public void eliminarColumna(String nombre) {
        System.out.println("[LOG] Eliminando columna: " + nombre);
        super.eliminarColumna(nombre);
    }
}
