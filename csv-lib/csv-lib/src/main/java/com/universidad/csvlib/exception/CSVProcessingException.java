package com.universidad.csvlib.exception;

/**
 * Excepcion propia de la libreria. Se lanza cuando ocurre un error durante
 * la lectura, escritura o manipulacion de un archivo CSV.
 *
 * Tener una excepcion propia evita que las clases que usan la libreria
 * dependan de excepciones genericas de Java (por ejemplo IOException),
 * manteniendo la libreria desacoplada de detalles de bajo nivel.
 */
public class CSVProcessingException extends Exception {

    public CSVProcessingException(String mensaje) {
        super(mensaje);
    }

    public CSVProcessingException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
