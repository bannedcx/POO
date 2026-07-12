package com.universidad.csvlib.processor;

import com.universidad.csvlib.exception.CSVProcessingException;
import com.universidad.csvlib.model.CSVTable;

import java.io.File;
import java.util.List;

/**
 * Clase abstracta que implementa el patron Decorator sobre CSVProcessor.
 *
 * Este es el mecanismo principal que la libreria ofrece para que un
 * usuario externo "extienda sus capacidades o modifique el resultado
 * final de las funciones... mediante la utilizacion de codigo propio"
 * SIN alterar el codigo base: en vez de modificar BaseCSVProcessor,
 * cualquiera puede crear una subclase de CSVProcessorDecorator, envolver
 * un CSVProcessor existente y sobrescribir unicamente los metodos que
 * le interesa cambiar. Los demas metodos siguen funcionando igual
 * porque se delegan al objeto envuelto.
 *
 * Esto es el Principio de Abierto/Cerrado (OCP) llevado a la practica.
 */
public abstract class CSVProcessorDecorator implements CSVProcessor {

    protected final CSVProcessor componente;

    protected CSVProcessorDecorator(CSVProcessor componente) {
        this.componente = componente;
    }

    @Override
    public CSVTable cargar(File archivo) throws CSVProcessingException {
        return componente.cargar(archivo);
    }

    @Override
    public void agregarFila(List<String> fila) {
        componente.agregarFila(fila);
    }

    @Override
    public void agregarFila(int posicion, List<String> fila) {
        componente.agregarFila(posicion, fila);
    }

    @Override
    public void eliminarFila(int indice) {
        componente.eliminarFila(indice);
    }

    @Override
    public void agregarColumna(String nombre, List<String> valores) {
        componente.agregarColumna(nombre, valores);
    }

    @Override
    public void eliminarColumna(String nombre) {
        componente.eliminarColumna(nombre);
    }

    @Override
    public void eliminarColumna(int indice) {
        componente.eliminarColumna(indice);
    }

    @Override
    public void imprimir() {
        componente.imprimir();
    }

    @Override
    public CSVTable getTabla() {
        return componente.getTabla();
    }
}
