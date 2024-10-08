package com.IU_DIGITAL.Controlador;

// Importaciones necesarias para los modelos de datos y las pruebas
import com.IU_DIGITAL.Modelo.*;
import com.IU_DIGITAL.Modelo.Excepciones.ReporteNoGeneradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

// Clase de pruebas para ControladorReportes
public class ControladorReportesTest {
    private ControladorEmpleados controladorEmpleados; // Controlador de empleados
    private ControladorReportes controladorReportes; // Controlador de reportes
    private Empleado empleado; // Empleado de prueba
    private Departamento departamento; // Departamento de prueba

    // Configura el entorno antes de cada prueba
    @BeforeEach
    void setUp() {
        controladorEmpleados = new ControladorEmpleados(); // Inicializa el controlador de empleados
        controladorReportes = new ControladorReportes(controladorEmpleados); // Inicializa el controlador de reportes

        // Crea un departamento de prueba
        departamento = new Departamento("001", "Desarrollo");

        // Crea un empleado de prueba
        empleado = new EmpleadoConcreto("E001", "Juan Perez", "Desarrollador",
                TipoEmpleado.PERMANENTE,
                departamento, 30, "Masculino", new Date());
        controladorEmpleados.agregarEmpleado(empleado); // Agrega el empleado al controlador
    }

    // Prueba la generacion de un reporte para un empleado
    @Test
    void testGenerarReporteEmpleado() {
        try {
            ReporteDesempenio reporte = controladorReportes.generarReporteEmpleado(empleado, "Excelente desempeño", 5);
            assertNotNull(reporte); // Verifica que el reporte no sea nulo
            assertEquals("RPT-001", reporte.getReportId()); // Verifica que el ID del reporte sea correcto
            assertEquals(empleado, reporte.getEmpleado()); // Verifica que el empleado del reporte sea el correcto
            assertEquals("Excelente desempeño", reporte.getEvaluacion()); // Verifica la evaluacion del reporte
            assertEquals(5, reporte.getCalificacion()); // Verifica la calificacion del reporte
        } catch (ReporteNoGeneradoException e) {
            fail("No se esperaba una excepcion: " + e.getMessage()); // Fallo si se lanza una excepcion inesperada
        }
    }

    // Prueba la generacion de un reporte con empleado nulo
    @Test
    void testGenerarReporteEmpleado_NullEmpleado() {
        Exception exception = assertThrows(ReporteNoGeneradoException.class, () -> {
            controladorReportes.generarReporteEmpleado(null, "Buena evaluacion", 4); // Intenta generar un reporte sin empleado
        });
        assertEquals("El empleado no puede ser nulo.", exception.getMessage()); // Verifica el mensaje de excepcion
    }

    // Prueba la generacion de un reporte para un departamento
    @Test
    void testGenerarReporteDepartamento() {
        try {
            ReporteDesempenio reporte = controladorReportes.generarReporteDepartamento(departamento, "Buena gestion", 4);
            assertNotNull(reporte); // Verifica que el reporte no sea nulo
            assertEquals("RPT-002", reporte.getReportId()); // Verifica que el ID del reporte sea correcto
            assertEquals(departamento, reporte.getDepartamento()); // Verifica que el departamento del reporte sea el correcto
            assertEquals("Buena gestion", reporte.getEvaluacion()); // Verifica la evaluacion del reporte
            assertEquals(4, reporte.getCalificacion()); // Verifica la calificacion del reporte
        } catch (ReporteNoGeneradoException e) {
            fail("No se esperaba una excepcion: " + e.getMessage()); // Fallo si se lanza una excepcion inesperada
        }
    }

    // Prueba la generacion de un reporte con departamento nulo
    @Test
    void testGenerarReporteDepartamento_NullDepartamento() {
        Exception exception = assertThrows(ReporteNoGeneradoException.class, () -> {
            controladorReportes.generarReporteDepartamento(null, "Buena gestion", 4); // Intenta generar un reporte sin departamento
        });
        assertEquals("El departamento no puede ser nulo.", exception.getMessage()); // Verifica el mensaje de excepcion
    }
}