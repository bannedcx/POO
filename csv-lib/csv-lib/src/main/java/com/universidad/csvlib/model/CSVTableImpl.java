package com.universidad.csvlib.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion basica de CSVTable que guarda los datos en memoria
 * usando listas de String. Su unica responsabilidad es almacenar y dar
 * acceso ordenado a los datos de la tabla (Principio de Responsabilidad
 * Unica - SRP): no sabe leer archivos ni imprimir en pantalla, de eso se
 * encargan otras clases de la libreria.
 */
public class CSVTableImpl implements CSVTable {

    private final List<String> headers;
    private final List<List<String>> rows;

    public CSVTableImpl(List<String> headers, List<List<String>> rows) {
        this.headers = new ArrayList<>(headers);
        this.rows = new ArrayList<>();
        for (List<String> fila : rows) {
            this.rows.add(new ArrayList<>(fila));
        }
    }

    @Override
    public List<String> getHeaders() {
        return new ArrayList<>(headers);
    }

    @Override
    public List<List<String>> getRows() {
        return new ArrayList<>(rows);
    }

    @Override
    public void addRow(List<String> fila) {
        addRow(rows.size(), fila);
    }

    @Override
    public void addRow(int posicion, List<String> fila) {
        List<String> filaNormalizada = normalizarFila(fila);
        rows.add(posicion, filaNormalizada);
    }

    @Override
    public void removeRow(int indice) {
        validarIndiceFila(indice);
        rows.remove(indice);
    }

    @Override
    public void addColumn(String nombreColumna, List<String> valores) {
        headers.add(nombreColumna);
        for (int i = 0; i < rows.size(); i++) {
            String valor = (valores != null && i < valores.size()) ? valores.get(i) : "";
            rows.get(i).add(valor);
        }
    }

    @Override
    public void removeColumn(String nombreColumna) {
        int indice = headers.indexOf(nombreColumna);
        if (indice == -1) {
            throw new IllegalArgumentException("La columna '" + nombreColumna + "' no existe.");
        }
        removeColumn(indice);
    }

    @Override
    public void removeColumn(int indice) {
        if (indice < 0 || indice >= headers.size()) {
            throw new IndexOutOfBoundsException("Indice de columna invalido: " + indice);
        }
        headers.remove(indice);
        for (List<String> fila : rows) {
            if (indice < fila.size()) {
                fila.remove(indice);
            }
        }
    }

    @Override
    public String getCell(int fila, int columna) {
        validarIndiceFila(fila);
        return rows.get(fila).get(columna);
    }

    @Override
    public void setCell(int fila, int columna, String valor) {
        validarIndiceFila(fila);
        rows.get(fila).set(columna, valor);
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return headers.size();
    }

    private void validarIndiceFila(int indice) {
        if (indice < 0 || indice >= rows.size()) {
            throw new IndexOutOfBoundsException("Indice de fila invalido: " + indice);
        }
    }

    /** Asegura que la fila tenga exactamente una celda por cada columna existente. */
    private List<String> normalizarFila(List<String> fila) {
        List<String> resultado = new ArrayList<>(fila);
        while (resultado.size() < headers.size()) {
            resultado.add("");
        }
        return resultado;
    }
}
