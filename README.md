# Proyecto CompuWork - Sistema de Gestión de Recursos Humanos

## Descripción General

El proyecto **CompuWork** es un sistema diseñado para la gestión eficiente de recursos humanos dentro de una organización. Este sistema permite la administración de empleados, la creación y modificación de departamentos, y la generación de reportes de desempeño tanto a nivel individual como departamental. El proyecto sigue una arquitectura basada en el patrón Modelo-Vista-Controlador (MVC), con un enfoque en la escalabilidad y la mantenibilidad.

## Estructura del Proyecto

El proyecto está estructurado siguiendo estándares profesionales, utilizando Maven para la gestión de dependencias, pruebas unitarias con JUnit y Mockito, y control de versiones con Git. A continuación se describe la estructura principal del proyecto:

```bash
CompuWork/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── IU_DIGITAL/
│   │   │           ├── Controlador/
│   │   │           ├── Modelo/
│   │   │           └── Vista/
│   │   └── resources/
│   └── test/
│       ├── java/
│       └── com/
│           └── IU_DIGITAL/
├── pom.xml
├── README.md
└── .gitignore
```

### Componentes principales

- **Controladores**: Implementan la lógica de negocio para gestionar empleados, departamentos y reportes.
    - `ControladorEmpleados.java`
    - `ControladorDepartamentos.java`
    - `ControladorReportes.java`

- **Modelos**: Representan las entidades del sistema (empleados, departamentos, reportes).
    - `Empleado.java`, `EmpleadoPermanente.java`, `EmpleadoTemporal.java`, `Departamento.java`, `ReporteDesempenio.java`

- **Vistas**: Interfaz gráfica desarrollada en **Swing** para la interacción con el usuario.
    - `PanelEmpleados.java`, `PanelDepartamentos.java`, `PanelReportes.java`

## Integración de Componentes

El sistema está diseñado siguiendo el patrón MVC, lo que garantiza una clara separación entre la lógica de negocio, los datos y la interfaz de usuario:

1. **Modelo**: Define las entidades clave como `Empleado`, `Departamento`, y `ReporteDesempenio`. Cada entidad incluye atributos y métodos necesarios para gestionar su información y comportamiento dentro del sistema.

2. **Controladores**: Cada controlador está asociado a una parte específica de la lógica de negocio:
    - El `ControladorEmpleados` maneja la creación, modificación y eliminación de empleados.
    - El `ControladorDepartamentos` administra la organización de los departamentos, incluyendo la asignación de empleados.
    - El `ControladorReportes` se encarga de generar reportes basados en el desempeño de empleados y departamentos.

3. **Vista**: La interfaz de usuario fue desarrollada utilizando **Java Swing** y está dividida en diferentes paneles para gestionar empleados, departamentos y reportes. Cada panel está conectado a su respectivo controlador, asegurando que las acciones realizadas en la interfaz se reflejen en el modelo subyacente.

### Flujo de trabajo

El flujo de trabajo típico implica que un usuario puede:
- Crear y modificar departamentos y empleados a través de los paneles correspondientes.
- Visualizar y modificar la lista de empleados asignados a cada departamento.
- Generar reportes de desempeño desde el `PanelReportes`, basado en datos de empleados o departamentos.

## Pruebas realizadas

Para garantizar la calidad del sistema, se implementaron pruebas unitarias utilizando **JUnit** y **Mockito**. A continuación, se describen algunas de las pruebas clave:

1. **Pruebas de creación de empleados y departamentos**:
    - Se probaron los métodos de creación de empleados (`crearEmpleado`) y departamentos (`crearDepartamento`) para asegurarse de que los objetos se creen correctamente y se agreguen a las listas correspondientes.
    - Ejemplo de prueba:
   ```java
   @Test
   public void testCrearEmpleado() {
       Empleado empleado = controladorEmpleados.crearEmpleado("123", "Juan", "Ingeniero", depto, 30, "Masculino", true, null, 0, "Beneficios", 50000);
       assertNotNull(empleado);
   }
   ```

2. **Pruebas de asignación de empleados a departamentos**:
    - Se verificó que los empleados se asignen correctamente a los departamentos, y se lanzan excepciones apropiadas si un empleado ya está asignado a un departamento.
    - Excepción manejada: `EmpleadoYaAsignadoException`.

3. **Pruebas de generación de reportes**:
    - Se probaron los métodos que generan reportes para empleados y departamentos. Se verificó que los reportes se creen correctamente y contengan la información adecuada.
    - Ejemplo de prueba:
   ```java
   @Test
   public void testGenerarReporteEmpleado() throws ReporteNoGeneradoException {
       ReporteDesempenio reporte = controladorReportes.generarReporteEmpleado(empleado, "Buena performance", 90);
       assertNotNull(reporte);
   }
   ```

4. **Cobertura de las vistas**:
    - Se verificaron las funcionalidades de la interfaz gráfica para asegurar que las interacciones con los usuarios, como la creación de empleados, departamentos y la generación de reportes, se reflejan correctamente en la lógica del sistema.

## Conclusión

El desarrollo del sistema **CompuWork** ha seguido una metodología clara y estructurada, garantizando la modularidad y escalabilidad del sistema. Las pruebas unitarias y la correcta integración de componentes aseguran que el sistema sea confiable y cumpla con los requisitos funcionales planteados. Este proyecto no solo es un ejercicio técnico, sino que también ofrece un enfoque robusto y escalable que puede ser adaptado a necesidades empresariales reales en el ámbito de la gestión de recursos humanos.
