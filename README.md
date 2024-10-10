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
=======
# CompuWork_A3
S30 - Caso de Estudio CompuWork - Actividad 3

# Sistema de Gestión de Empleados para CompuWork

## Objetivo del Sistema
El sistema desarrollado para **CompuWork** tiene como objetivo principal administrar los datos de los empleados, organizar los departamentos de la empresa y generar reportes detallados de desempeño. Este sistema se diseñó para ser flexible, escalable y fácil de mantener, utilizando los principios de la Programación Orientada a Objetos (POO) y modelado con UML, garantizando su modularidad y reutilización de componentes.

## Requerimientos Funcionales

### Gestión de Empleados
- El sistema permite la creación, actualización y eliminación de registros de empleados.
- Funcionalidades para asignar empleados a departamentos específicos y cambiar esta asignación según sea necesario.
- Gestión de diferentes tipos de empleados, como permanentes y temporales, cada uno con atributos específicos.

### Organización de Departamentos
- El sistema permite la creación, modificación y eliminación de departamentos dentro de la empresa.
- Asignación y visualización de empleados asignados a cada departamento.

### Generación de Reportes de Desempeño
- El sistema genera reportes detallados de desempeño para cada empleado, utilizando métricas predefinidas.
- Permite la generación de reportes tanto a nivel individual como a nivel de departamento.

### Interfaz de Usuario
- Proporciona una interfaz de usuario intuitiva y fácil de usar para la gestión de empleados, departamentos y la visualización de reportes.

## Requerimientos No Funcionales

### Escalabilidad
- El sistema está diseñado para escalar y manejar un número creciente de empleados y departamentos sin afectar el rendimiento.

### Seguridad
- Cumple con estándares de seguridad, protegiendo la información sensible de los empleados mediante encriptación de datos y medidas contra accesos no autorizados.

### Rendimiento
- Capaz de generar reportes de desempeño en tiempo real sin demoras perceptibles.

### Mantenibilidad
- El diseño modular, basado en principios de POO, facilita futuras actualizaciones y mantenimiento.

### Compatibilidad
- Compatible con diferentes navegadores web y dispositivos, incluyendo computadoras de escritorio y dispositivos móviles.

### Disponibilidad
- El sistema estará disponible 24/7 con un tiempo de inactividad mínimo, asegurando su accesibilidad en todo momento.

## Instrucciones - Actividad 3: Integración y Validación

### Integración de Componentes
Los estudiantes deberán integrar las clases implementadas en una aplicación funcional que permita la gestión de empleados y departamentos, así como la generación de reportes de desempeño. Para los componentes visuales, los estudiantes tienen libertad para escoger cómo representar gráficamente la aplicación, ya sea usando librerías propias del lenguaje de programación o un framework.

### Pruebas Unitarias
Desarrollar pruebas unitarias para validar el correcto funcionamiento de las clases y métodos implementados, utilizando un framework de pruebas como JUnit.

### Documentación del Proceso
Documentar el proceso de desarrollo, explicando la integración de componentes y las pruebas realizadas para validar el sistema.

### Repositorios
Los estudiantes deberán crear un repositorio en GitHub y realizar la entrega del proyecto mediante un enlace al repositorio.

## Cómo Entregar la Actividad
- Los estudiantes deben continuar con el mismo grupo formado en la actividad previa.
- Desarrollar la implementación de los diagramas realizados en la actividad 1, con el código subido a un repositorio de GitHub.
- Entregar el enlace del repositorio y un documento en formato Word con normas APA.
- El documento debe incluir portada con los integrantes del grupo, introducción, y una conclusión sobre la experiencia en el desarrollo de la actividad y cómo apoya en el mundo laboral.
- La entrega debe realizarse mediante la plataforma **Canvas**; solo el líder del grupo debe hacer la entrega.



