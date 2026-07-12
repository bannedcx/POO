
REALIZADO POR: Juan Chaustre, Santiago Carrasquero y Eric Vargas

csv-lib
Librería Java extensible para el procesamiento de archivos CSV mediante los métodos de un objeto, desarrollada siguiendo los principios SOLID.

Requisitos
Java 17 o superior
Maven 3.8+

Estructura del proyecto
csv-lib/
├── pom.xml
├── README.md
└── src/main/java/com/universidad/csvlib/
    ├── Main.java                          (clase de demostración)
    ├── model/
    │   ├── CSVTable.java                  (interfaz de la tabla de datos)
    │   └── CSVTableImpl.java              (implementación por defecto)
    ├── parser/
    │   ├── CSVParser.java                 (interfaz - estrategia de lectura)
    │   └── DefaultCSVParser.java          (parser por defecto, separador coma)
    ├── printer/
    │   ├── CSVPrinter.java                (interfaz - estrategia de impresión)
    │   └── ConsoleCSVPrinter.java         (impresión por consola)
    ├── processor/
    │   ├── CSVProcessor.java              (interfaz principal de la librería)
    │   ├── BaseCSVProcessor.java          (implementación base)
    │   └── CSVProcessorDecorator.java     (base para extender sin modificar)
    ├── exception/
    │   └── CSVProcessingException.java
    └── ejemplos/                          (ejemplos de extensión, NO son parte del núcleo)
        ├── MayusculasProcessorDecorator.java
        ├── LoggerProcessorDecorator.java
        └── ParserPuntoYComa.java
Compilar y ejecutar
mvn compile
mvn exec:java
Uso básico de la librería
CSVProcessor procesador = new BaseCSVProcessor(new DefaultCSVParser(), new ConsoleCSVPrinter());

// Cargar un archivo
procesador.cargar(new File("alumnos.csv"));

// Agregar / eliminar filas
procesador.agregarFila(List.of("4", "Diana Cordova", "Sistemas"));
procesador.eliminarFila(1);

// Agregar / eliminar columnas
procesador.agregarColumna("Activo", List.of("Si", "Si", "No", "Si"));
procesador.eliminarColumna("Carrera");

// Imprimir en pantalla
procesador.imprimir();
Cómo extender la librería SIN modificar su código base
La librería fue diseñada para que nunca sea necesario tocar sus clases para agregarle comportamiento nuevo. Existen dos puntos de extensión:

1. Nueva forma de leer archivos → implementar CSVParser
Si necesitás soportar otro formato (otro separador, otra codificación, con comillas, etc.), creá tu propia clase que implemente CSVParser (o que extienda DefaultCSVParser, como en el ejemplo):

public class ParserPuntoYComa extends DefaultCSVParser {
    public ParserPuntoYComa() {
        super(";");
    }
}
Y usala igual que cualquier otro parser:

CSVProcessor procesador = new BaseCSVProcessor(new ParserPuntoYComa(), new ConsoleCSVPrinter());
2. Nueva forma de mostrar la tabla → implementar CSVPrinter
Igual que con el parser, para agregar una salida en otro formato (Markdown, HTML, JSON, etc.) alcanza con implementar la interfaz CSVPrinter y pasarla al BaseCSVProcessor.

3. Modificar o enriquecer el comportamiento de cualquier método → extender CSVProcessorDecorator
Este es el punto de extensión más potente: permite envolver un CSVProcessor ya existente y sobrescribir únicamente los métodos que te interesa modificar, dejando el resto igual.

Ejemplo — convertir todos los datos a mayúsculas después de cargarlos:

public class MayusculasProcessorDecorator extends CSVProcessorDecorator {
    public MayusculasProcessorDecorator(CSVProcessor componente) {
        super(componente);
    }

    @Override
    public CSVTable cargar(File archivo) throws CSVProcessingException {
        CSVTable tabla = super.cargar(archivo);
        // ... transformar tabla ...
        return tabla;
    }
}
Uso, incluso combinando varios decoradores entre sí:

CSVProcessor procesador = new LoggerProcessorDecorator(
                                new MayusculasProcessorDecorator(
                                    new BaseCSVProcessor(new DefaultCSVParser(), new ConsoleCSVPrinter())));
En ningún caso hace falta editar BaseCSVProcessor.java ni ninguna otra clase de la librería: todo el código nuevo vive fuera de ella (en este repositorio, dentro del paquete ejemplos, a modo de muestra).

desarrollo de librería extensible en Java siguiendo principios SOLID.
