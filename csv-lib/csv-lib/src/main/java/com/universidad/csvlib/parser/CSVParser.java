package com.universidad.csvlib.parser;

import com.universidad.csvlib.exception.CSVProcessingException;
import com.universidad.csvlib.model.CSVTable;

import java.io.File;

/**
 * Estrategia de lectura de un archivo CSV. Define UNICAMENTE el contrato
 * para transformar un archivo en un CSVTable.
 *
 * Esta interfaz es la pieza clave del Principio de Abierto/Cerrado (OCP):
 * la libreria queda "cerrada" a modificaciones (no hay que tocar el
 * codigo existente) pero "abierta" a extension, ya que cualquiera puede
 * crear una nueva implementacion (por ejemplo, para archivos separados
 * por punto y coma, con comillas, con otra codificacion, etc.) y usarla
 * sin cambiar una sola linea de la libreria.
 */
public interface CSVParser {

    /**
     * Lee el archivo indicado y construye la representacion CSVTable
     * correspondiente.
     */
    CSVTable parsear(File archivo) throws CSVProcessingException;
}
