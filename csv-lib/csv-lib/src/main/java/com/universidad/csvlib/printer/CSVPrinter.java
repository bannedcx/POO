package com.universidad.csvlib.printer;

import com.universidad.csvlib.model.CSVTable;

/**
 * Estrategia de salida/impresion de una tabla CSV. Al igual que
 * CSVParser, es una interfaz pequena y especifica (ISP) que permite
 * agregar nuevas formas de mostrar la tabla (por ejemplo, en formato
 * Markdown o HTML) sin modificar el resto de la libreria (OCP).
 */
public interface CSVPrinter {

    /** Muestra la tabla recibida en pantalla (o en el destino que corresponda). */
    void imprimir(CSVTable tabla);
}
