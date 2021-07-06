# Trabajo Práctico N°2 - Programación 3

## Descripción
El trábajo práctico consiste en implementar una aplicación denominada “Conexiones telefónicas” cuyo objetivo es planificar conexiones telefónicas 
entre localidades ubicadas en regiones despobladas. Dado un conjunto de localidades, se debe proporcionar el **Árbol Generador Mínimo (AGM)** entre ellas e informar el costo de la solución.

La aplicación debe permitir al usuario registrar una serie de localidades, incluyendo el nombre, provincia, cantidad de habitantes, latitud y longitud de cada una.
Además, se deben proporcionar los siguientes costos:
- Costo en pesos por kilómetro de una conexión entre dos localidades.
- Costo fijo que se agrega si la conexión involucra localidades de dos provincias distintas.
- Costo fijo que se agrega si la conexión tiene más de 200 km.

## Tecnologías y herramientas utilizadas
- [Eclipse IDE](https://www.eclipse.org/eclipseide/)
- [Java](https://www.java.com/es/download/)
- [JUnit](https://junit.org/junit5/)
- [WindowBuilder](https://www.eclipse.org/windowbuilder/)

## Estructura del proyecto
```bash
├── grafo
│   └── Arista.java 
│   └── Grafo.java
|   └── GrafoConPesos.java
|   └── Prim.java 
├── interfaz
│   └── AristaGrafica.java
|   └── Coordenada.java
|   └── Localidad.java
|   └── Mapa.java
|   └── VentanaPrincipal.java
├── tests
    ├── 
```

## Autoras
- Micaela Vera
- Liz Moreno López
- Verónica Juárez
