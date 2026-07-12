package com.universidad.csvlib.processor;

import com.universidad.csvlib.exception.CSVProcessingException;
import com.universidad.csvlib.model.CSVTable;

import java.io.File;
import java.util.List;

/**
 * Es el "objeto" principal de la libreria: representa un archivo CSV
 * cargado en memoria y expone los metodos que pide el enunciado
 * (cargar, agregar/quitar filas y columnas, imprimir).
 *
 * Al ser una interfaz -y no una clase concreta- cualquier implementacion
 * (la base o una decorada) puede usarse indistintamente en cualquier
 * parte del programa (Principio de Sustitucion de Liskov - LSP), y es
 * el punto de extension sobre el que se apoya el patron Decorator
 * (ver CSVProcessorDecorator) para agregar comportamiento sin modificar
 * el codigo existente (OCP).
 */
public interface CSVProcessor {

    /** Carga un archivo CSV y lo deja disponible como tabla interna. */
    CSVTable cargar(File archivo) throws CSVProcessingException;

    /** Agrega una fila al final de la tabla actualmente cargada. */
    void agregarFila(List<String> fila);

    /** Agrega una fila en una posicion especifica. */
    void agregarFila(int posicion, List<String> fila);

    /** Elimina la fila en el indice indicado. */
    void eliminarFila(int indice);

    /** Agrega una columna nueva con sus valores. */
    void agregarColumna(String nombre, List<String> valores);

    /** Elimina una columna por nombre. */
    void eliminarColumna(String nombre);

    /** Elimina una columna por indice. */
    void eliminarColumna(int indice);

    /** Imprime en pantalla la tabla actual. */
    void imprimir();

    /** Devuelve la tabla interna, por si se necesita inspeccionarla directamente. */
    CSVTable getTabla();
}
