# Fast-Puzzle

Aplicación de escritorio en Java (Swing) que implementa un puzzle por piezas. El jugador elige nombre y tamaño del tablero, se carga una imagen aleatoria, se divide en subimágenes y debe reconstruirse antes de que termine el tiempo. Incluye historial general/selectivo y guardado de resultados.

## Características

- Interfaz gráfica con menú, barra de herramientas y panel de juego.
- Creación de partidas con nombre de jugador y divisiones horizontales/verticales.
- Selección aleatoria de imagen desde un directorio configurable.
- División automática de imagen en piezas (`subimagenes/`).
- Mecánica de intercambio de piezas con ratón.
- Temporizador con barra de progreso.
- Puntuación por partida y persistencia en `resultados.dat`.
- Consulta de clasificación general e historial por jugador.

## Estructura del proyecto

- `src/FastPuzzle/FastPuzzle.java`: clase principal y flujo UI.
- `src/FastPuzzle/Partida.java`: modelo de partida (jugador, fecha, puntuación, imagen, divisiones).
- `src/FastPuzzle/Imagenes.java`: división de imágenes en subimágenes.
- `src/FastPuzzle/panelSubImagenes.java`: lógica del tablero y comprobación de solución.
- `src/FastPuzzle/SubImagen.java`: componente visual de cada pieza.
- `src/FastPuzzle/Barra_Temporal.java`: barra de tiempo.
- `src/FastPuzzle/FicheroPartidas.java`: lectura/escritura de partidas serializadas.

## Requisitos

- Java 8+ (recomendado Java 11 o superior).
- Entorno NetBeans/Ant o ejecución directa del JAR generado.

## Cómo ejecutar

### Opción 1: desde NetBeans

1. Abrir el proyecto.
2. Ejecutar la clase principal `FastPuzzle.FastPuzzle`.

### Opción 2: desde terminal (JAR)

```bash
java -jar .\dist\Fast-Puzzle.jar
```

## Cómo jugar

1. Pulsar **NUEVA PARTIDA**.
2. Introducir:
   - Nombre del jugador (máx. 15 caracteres).
   - Subdivisiones verticales y horizontales (máx. 30 piezas en total).
3. Seleccionar piezas con clic para intercambiarlas.
4. Completar la imagen antes de que se llene la barra temporal.
5. Consultar resultados en **CLASIFICACIÓN GENERAL** o **HISTORIAL**.

## Persistencia de datos

- Los resultados se guardan en `resultados.dat` como objetos serializados de `Partida`.
- El directorio por defecto de imágenes es `imagenes/`.
- Puede cambiarse con la opción **CAMBIAR DIRECTORIO DE IMAGENES** (carpeta con imágenes válidas).

## Recursos incluidos

- Carpeta `imagenes/` con imágenes de ejemplo.
- Carpeta `iconos/` para botones del menú.
- Imagen de portada `UIB.jpg`.

## Autor

Jaume Calafat Cerdó – Proyecto académico de Programación II, Universitat de les Illes Balears