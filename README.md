# Design Patterns (Java)

Repositorio de **aprendizaje personal** sobre **patrones de diseño**, explicados y practicados con **Java**. El material está pensado tanto para quien lo mantiene como para cualquier persona que quiera revisar ejemplos ejecutables, ordenados por dificultad y con retos que combinan varios patrones.

## ¿Qué hay aquí?

- **Ejemplos por patrón** (`concept`): una idea corta documentada en Javadoc y un ejemplo mínimo que muestra los componentes típicos del patrón.
- **Ejercicios guiados por patrón** (`exercise` / carpeta equivalente dentro de cada patrón): escenarios con requisitos concretos para implementar ese patrón en un dominio nuevo.
- **Ejercicios integradores** (`creational/exercise`): retos que mezclan lo ya visto (*Builder*, *Factory Method*, *Abstract Factory*, etc.), similares a cómo aparecerían en un proyecto real.

No es un tutorial lineal tipo blog: la **fuente de verdad** está en el código comentado; este README sirve como **mapa** del repositorio y **ruta de estudio** sugerida.

## Organización del código

Las fuentes viven bajo `src/main/java`:

| Área | Ruta | Rol |
|------|------|-----|
| **Builder** — concepto | `creational/builder/concept/` | Ejemplo ilustrativo (PC “paso a paso”). |
| **Builder** — ejercicios | `creational/builder/exercise/` | Por ejemplo mensajes de negocio y constructor de consultas SQL. |
| **Factory Method** — concepto | `creational/factory_method/concept/` | Fábrica de notificaciones (Email / SMS / etc.). |
| **Factory Method** — ejercicio | `creational/factory_method/exercise/` | Logística: transportes por tipo. |
| **Abstract Factory** — concepto | `creational/abstract_factory/concept/` | Familias de muebles (p. ej. victoriano vs. moderno). |
| **Abstract Factory** — ejercicio | `creational/abstract_factory/excersice/` | UI multiplataforma (familias de widgets). *(Nota: el nombre de la carpeta tiene un typo habitual; `excersice` → `exercise`. Mantener hasta que se renombre en el repo.)* |
| **Retos combinados** | `creational/exercise/` | **RPG**: Builder + Factory Method. **Automotriz**: Builder + Factory Method + Abstract Factory. |

Estructura resumida:

```text
src/main/java/
├── Main.java                         # Punto de entrada genérico (actualmente vacío)
└── creational/
    ├── builder/
    │   ├── concept/
    │   └── exercise/
    ├── factory_method/
    │   ├── concept/
    │   └── exercise/
    ├── abstract_factory/
    │   ├── concept/
    │   └── excersice/
    └── exercise/
        ├── MainRPG.java               # Builder + Factory Method
        └── MainAutomotive.java        # Los tres patrones creacionales
```

## Orden sugerido de estudio

1. **Builder** — `MainConceptBuilder` → `MainMessage`, `MainQuery`
2. **Factory Method** — `MainConceptFactoryMethod` → `MainTransportFactory`
3. **Reto combinado** — `MainRPG` (Builder + Factory Method)
4. **Abstract Factory** — `MainAbstractFactory` → `MainUIFactory`
5. **Reto combinado amplio** — `MainAutomotive` (los tres patrones)

Esa progresión refleja la idea original del repo: consolidar cada patrón y luego **mezclarlo** con lo anterior antes de pasar al siguiente nivel.

## Herramientas y ejecución

El proyecto **no incluye** (por ahora) Maven ni Gradle en el árbol de archivos típico: es código Java directo pensado para abrir en un IDE y ejecutar la clase que quieras como **main**.

El estilo del código usa **entrada compacta en Java moderno** (por ejemplo `void main()` en la clase y salida por consola mediante utilidades tipo `IO.println`). Para compilar desde terminal hace falta un **JDK reciente** (se recomienda **21 o superior**) y, según tu versión de Java y del IDE, puede ser necesario activar **opciones preview** (`--enable-preview`) al compilar y al ejecutar. Si algo no compila, revisa que el proyecto en el IDE esté alineado con la misma versión del JDK y con las mismas opciones del lenguaje.

Ejemplo orientativo desde la raíz del repositorio (ajusta rutas de paquete y nombre de clase; añade `--enable-preview` si tu JDK lo exige):

```bash
javac -d out src/main/java/creational/builder/concept/MainConceptBuilder.java
java -cp out creational.builder.concept.MainConceptBuilder
```

## Alcance actual

Por ahora el contenido implementado está centrado en **patrones creacionales** dentro de `creational/`. Si el repo crece con estructurales o de comportamiento, conviene repetir la misma idea: carpeta por patrón → `concept` + `exercise` → más adelante `exercise/` o similar para combinaciones.

## Licencia y uso

Uso informal para **estudio**. Si reusas fragmentos en otro proyecto, adapta los nombres y la documentación a tu contexto.