package com.IU_DIGITAL.Modelo;

import com.IU_DIGITAL.Modelo.Excepciones.ReporteNoGeneradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReporteDesempenioTest {
    // Variables para empleado, departamento y reportes
    private Empleado empleado;
    private Departamento departamento;
    private ReporteDesempenio reporteEmpleado;
    private ReporteDesempenio reporteDepartamento;

    // Metodo que se ejecuta antes de cada test, inicializando los objetos necesarios
    @BeforeEach
    void setUp() {
        // Creamos un departamento y un empleado antes de cada test
        departamento = new Departamento("01", "Desarrollo");
        empleado = new EmpleadoConcreto("E001", "Juan Perez", "Desarrollador",
                TipoEmpleado.PERMANENTE, departamento, 30, "M", new Date());
    }

    // Test para verificar la correcta creacion de un reporte de empleado
    @Test
    void testCrearReporteEmpleado() {
        // Verificamos que no se lanza ninguna excepcion al crear un reporte de empleado
        assertDoesNotThrow(() -> {
            reporteEmpleado = new ReporteDesempenio("RPT-001", empleado, "Buen desempeño", 90);
        });

        // Verificamos que el reporte de empleado se crea correctamente
        assertNotNull(reporteEmpleado); // El reporte no debe ser nulo
        assertEquals("RPT-001", reporteEmpleado.getReportId()); // Verificamos el ID del reporte
        assertEquals(empleado, reporteEmpleado.getEmpleado()); // Verificamos que el empleado sea correcto
        assertEquals("Buen desempeño", reporteEmpleado.getEvaluacion()); // Verificamos la evaluacion
        assertEquals(90, reporteEmpleado.getCalificacion()); // Verificamos la calificacion
    }

    // Test para verificar la correcta creacion de un reporte de departamento
    @Test
    void testCrearReporteDepartamento() {
        // Verificamos que no se lanza ninguna excepcion al crear un reporte de departamento
        assertDoesNotThrow(() -> {
            reporteDepartamento = new ReporteDesempenio("RPT-002", departamento, "Buen desempeño general", 85);
        });

        // Verificamos que el reporte de departamento se crea correctamente
        assertNotNull(reporteDepartamento); // El reporte no debe ser nulo
        assertEquals("RPT-002", reporteDepartamento.getReportId()); // Verificamos el ID del reporte
        assertEquals(departamento, reporteDepartamento.getDepartamento()); // Verificamos el departamento
        assertEquals("Buen desempeño general", reporteDepartamento.getEvaluacion()); // Verificamos la evaluacion
        assertEquals(85, reporteDepartamento.getCalificacion()); // Verificamos la calificacion
    }

    // Test para verificar el formato correcto del metodo toString en reportes de empleado
    @Test
    void testToStringEmpleado() throws ReporteNoGeneradoException {
        // Creamos un reporte de empleado y verificamos su formato en toString
        reporteEmpleado = new ReporteDesempenio("RPT-001", empleado, "Buen desempeño", 90);
        String expected = "Reporte de Desempeño{ID='RPT-001', Empleado=Juan Perez, Evaluación='Buen desempeño', Calificación=90}";
        assertEquals(expected, reporteEmpleado.toString()); // Verificamos que el formato sea correcto
        System.out.println(expected); // Imprimimos el reporte para ver su salida
    }

    // Test para verificar el formato correcto del metodo toString en reportes de departamento
    @Test
    void testToStringDepartamento() throws ReporteNoGeneradoException {
        // Creamos un reporte de departamento y verificamos su formato en toString
        reporteDepartamento = new ReporteDesempenio("RPT-002", departamento, "Buen desempeño general", 85);
        String expected = "Reporte de Desempeño{ID='RPT-002', Departamento=Desarrollo, Evaluación='Buen desempeño general', Calificación=85}";
        assertEquals(expected, reporteDepartamento.toString()); // Verificamos que el formato sea correcto
        System.out.println(expected); // Imprimimos el reporte para ver su salida
    }
}