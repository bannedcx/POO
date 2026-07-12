package com.universidad.csvlib.printer;

import com.universidad.csvlib.model.CSVTable;

import java.util.List;

/**
 * Implementacion por defecto de CSVPrinter: imprime la tabla en la
 * consola con columnas alineadas, similar a una tabla de base de datos.
 *
 * Unica responsabilidad: formatear y mostrar (SRP). No sabe nada de como
 * se leyo el archivo ni de como se agregan/quitan filas o columnas.
 */
public class ConsoleCSVPrinter implements CSVPrinter {

    @Override
    public void imprimir(CSVTable tabla) {
        List<String> headers = tabla.getHeaders();
        int[] anchos = calcularAnchos(tabla);

        imprimirLinea(anchos);
        imprimirFila(headers, anchos);
        imprimirLinea(anchos);

        for (List<String> fila : tabla.getRows()) {
            imprimirFila(fila, anchos);
        }
        imprimirLinea(anchos);
    }

    private int[] calcularAnchos(CSVTable tabla) {
        List<String> headers = tabla.getHeaders();
        int[] anchos = new int[headers.size()];

        for (int i = 0; i < headers.size(); i++) {
            anchos[i] = headers.get(i).length();
        }
        for (List<String> fila : tabla.getRows()) {
            for (int i = 0; i < fila.size() && i < anchos.length; i++) {
                anchos[i] = Math.max(anchos[i], fila.get(i).length());
            }
        }
        return anchos;
    }

    private void imprimirFila(List<String> valores, int[] anchos) {
        StringBuilder sb = new StringBuilder("|");
        for (int i = 0; i < anchos.length; i++) {
            String valor = i < valores.size() ? valores.get(i) : "";
            sb.append(" ").append(pad(valor, anchos[i])).append(" |");
        }
        System.out.println(sb);
    }

    private void imprimirLinea(int[] anchos) {
        StringBuilder sb = new StringBuilder("+");
        for (int ancho : anchos) {
            sb.append("-".repeat(ancho + 2)).append("+");
        }
        System.out.println(sb);
    }

    private String pad(String valor, int ancho) {
        return String.format("%-" + ancho + "s", valor);
    }
}
