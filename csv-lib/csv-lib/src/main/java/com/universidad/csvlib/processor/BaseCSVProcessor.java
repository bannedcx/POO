package com.universidad.csvlib.processor;

import com.universidad.csvlib.exception.CSVProcessingException;
import com.universidad.csvlib.model.CSVTable;
import com.universidad.csvlib.parser.CSVParser;
import com.universidad.csvlib.printer.CSVPrinter;

import java.io.File;
import java.util.List;

/**
 * Implementacion base (por defecto) de CSVProcessor.
 *
 * IMPORTANTE: no depende de clases concretas para leer ni imprimir, sino
 * de las abstracciones CSVParser y CSVPrinter, que recibe por
 * constructor (Principio de Inversion de Dependencias - DIP). Esto
 * permite, por ejemplo, cambiar DefaultCSVParser por un parser que lea
 * con punto y coma sin tocar ni una linea de esta clase.
 *
 * Su responsabilidad es orquestar el uso del parser, la tabla y el
 * printer (SRP): no sabe interpretar texto CSV (de eso se encarga el
 * CSVParser) ni sabe formatear una salida por consola (de eso se
 * encarga el CSVPrinter).
 */
public class BaseCSVProcessor implements CSVProcessor {

    protected final CSVParser parser;
    protected final CSVPrinter printer;
    protected CSVTable tabla;

    public BaseCSVProcessor(CSVParser parser, CSVPrinter printer) {
        this.parser = parser;
        this.printer = printer;
    }

    @Override
    public CSVTable cargar(File archivo) throws CSVProcessingException {
        this.tabla = parser.parsear(archivo);
        return this.tabla;
    }

    @Override
    public void agregarFila(List<String> fila) {
        verificarTablaCargada();
        tabla.addRow(fila);
    }

    @Override
    public void agregarFila(int posicion, List<String> fila) {
        verificarTablaCargada();
        tabla.addRow(posicion, fila);
    }

    @Override
    public void eliminarFila(int indice) {
        verificarTablaCargada();
        tabla.removeRow(indice);
    }

    @Override
    public void agregarColumna(String nombre, List<String> valores) {
        verificarTablaCargada();
        tabla.addColumn(nombre, valores);
    }

    @Override
    public void eliminarColumna(String nombre) {
        verificarTablaCargada();
        tabla.removeColumn(nombre);
    }

    @Override
    public void eliminarColumna(int indice) {
        verificarTablaCargada();
        tabla.removeColumn(indice);
    }

    @Override
    public void imprimir() {
        verificarTablaCargada();
        printer.imprimir(tabla);
    }

    @Override
    public CSVTable getTabla() {
        return tabla;
    }

    private void verificarTablaCargada() {
        if (tabla == null) {
            throw new IllegalStateException("Debe cargar un archivo CSV antes de operar sobre la tabla.");
        }
    }
}
