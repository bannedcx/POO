package com.universidad.csvlib.model;

import java.util.List;

/**
 * Representa, en memoria, el contenido de un archivo CSV: una cabecera
 * (nombres de columna) y un conjunto de filas de datos. Por defecto todos
 * los valores se manejan como String, tal como pide el enunciado.
 *
 * Esta interfaz es pequena y se limita a describir "una tabla de datos",
 * sin mezclar responsabilidades de lectura de archivos ni de impresion
 * (Principio de Segregacion de Interfaces - ISP). Cualquier implementacion
 * de CSVTable puede ser usada por el resto de la libreria sin conocer
 * detalles internos (Principio de Sustitucion de Liskov - LSP).
 */
public interface CSVTable {

    /** Devuelve la lista de nombres de columna (cabecera). */
    List<String> getHeaders();

    /** Devuelve todas las filas de datos, cada una como lista de Strings. */
    List<List<String>> getRows();

    /** Agrega una fila al final de la tabla. */
    void addRow(List<String> fila);

    /** Agrega una fila en una posicion especifica. */
    void addRow(int posicion, List<String> fila);

    /** Elimina la fila ubicada en el indice indicado. */
    void removeRow(int indice);

    /** Agrega una nueva columna con un nombre y sus valores para cada fila existente. */
    void addColumn(String nombreColumna, List<String> valores);

    /** Elimina una columna a partir de su nombre. */
    void removeColumn(String nombreColumna);

    /** Elimina una columna a partir de su indice. */
    void removeColumn(int indice);

    /** Obtiene el valor de una celda especifica. */
    String getCell(int fila, int columna);

    /** Modifica el valor de una celda especifica. */
    void setCell(int fila, int columna, String valor);

    /** Cantidad de filas de datos (sin contar la cabecera). */
    int getRowCount();

    /** Cantidad de columnas. */
    int getColumnCount();
}
