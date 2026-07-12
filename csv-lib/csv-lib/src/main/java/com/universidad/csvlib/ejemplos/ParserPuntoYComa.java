package com.universidad.csvlib.ejemplos;

import com.universidad.csvlib.parser.DefaultCSVParser;

/**
 * EJEMPLO DE EXTENSION (codigo propio, fuera de la libreria base).
 *
 * Nueva estrategia de lectura para archivos separados por punto y coma
 * (formato comun en configuraciones regionales en español, como Excel
 * en Latinoamerica/España). No fue necesario modificar DefaultCSVParser
 * ni ninguna otra clase de la libreria: alcanza con reutilizar la logica
 * ya existente indicando otro separador (OCP).
 */
public class ParserPuntoYComa extends DefaultCSVParser {

    public ParserPuntoYComa() {
        super(";");
    }
}
