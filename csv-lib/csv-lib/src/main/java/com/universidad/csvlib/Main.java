package com.universidad.csvlib;

import com.universidad.csvlib.ejemplos.LoggerProcessorDecorator;
import com.universidad.csvlib.ejemplos.MayusculasProcessorDecorator;
import com.universidad.csvlib.ejemplos.ParserPuntoYComa;
import com.universidad.csvlib.parser.DefaultCSVParser;
import com.universidad.csvlib.printer.ConsoleCSVPrinter;
import com.universidad.csvlib.processor.BaseCSVProcessor;
import com.universidad.csvlib.processor.CSVProcessor;

import java.io.File;
import java.util.List;

/**
 * Clase de demostracion. Muestra el uso basico de la libreria y como se
 * puede extender su comportamiento usando decoradores y nuevas
 * estrategias de parseo, sin modificar el codigo base.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        File archivo = new File("src/main/resources/alumnos.csv");

        System.out.println("=== 1. Uso basico de la libreria ===");
        CSVProcessor procesador = new BaseCSVProcessor(new DefaultCSVParser(), new ConsoleCSVPrinter());
        procesador.cargar(archivo);
        procesador.imprimir();

        System.out.println("\n=== 2. Agregar y eliminar filas/columnas ===");
        procesador.agregarFila(List.of("4", "Diana Cordova", "Sistemas"));
        procesador.agregarColumna("Activo", List.of("Si", "Si", "No", "Si"));
        procesador.eliminarFila(1);
        procesador.imprimir();

        System.out.println("\n=== 3. Extension mediante Decorator (mayusculas + logging) ===");
        CSVProcessor procesadorExtendido =
                new LoggerProcessorDecorator(
                        new MayusculasProcessorDecorator(
                                new BaseCSVProcessor(new DefaultCSVParser(), new ConsoleCSVPrinter())));

        procesadorExtendido.cargar(archivo);
        procesadorExtendido.agregarFila(List.of("5", "elena ruiz", "marketing"));
        procesadorExtendido.imprimir();

        System.out.println("\n=== 4. Extension mediante nueva estrategia de parseo (punto y coma) ===");
        CSVProcessor procesadorPuntoYComa =
                new BaseCSVProcessor(new ParserPuntoYComa(), new ConsoleCSVPrinter());
        procesadorPuntoYComa.cargar(new File("src/main/resources/alumnos_pyc.csv"));
        procesadorPuntoYComa.imprimir();
    }
}
