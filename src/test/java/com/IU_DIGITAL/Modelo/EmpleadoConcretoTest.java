package com.IU_DIGITAL.Modelo;

// Importaciones necesarias para las pruebas
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

// Clase de pruebas para EmpleadoConcreto
public class EmpleadoConcretoTest {
    private EmpleadoConcreto empleado; // Instancia del empleado a probar

    // Configura el entorno antes de cada prueba
    @BeforeEach
    void setUp() {
        Departamento departamento = new Departamento("001", "IT"); // Inicializa un departamento
        TipoEmpleado tipoEmpleado = TipoEmpleado.PERMANENTE; // Define el tipo de empleado
        empleado = new EmpleadoConcreto("E001", "Juan Perez", "Desarrollador", tipoEmpleado,
                departamento, 30, "Masculino", new Date()); // Crea un empleado
    }

    // Prueba la obtención del ID del empleado
    @Test
    void testGetId() {
        assertEquals("E001", empleado.getId()); // Verifica que el ID sea el correcto
    }

    // Prueba la obtención del nombre del empleado
    @Test
    void testGetNombre() {
        assertEquals("Juan Perez", empleado.getNombre()); // Verifica que el nombre sea el correcto
    }

    // Prueba la obtención del puesto del empleado
    @Test
    void testGetPuesto() {
        assertEquals("Desarrollador", empleado.getPuesto()); // Verifica que el puesto sea el correcto
    }

    // Prueba la obtención del tipo de empleado en formato de cadena
    @Test
    void testGetTipoEmpleadoString() {
        assertEquals("PERMANENTE", empleado.getTipoEmpleadoString()); // Verifica que el tipo de empleado sea correcto
    }

    // Prueba la obtención del departamento del empleado
    @Test
    void testGetDepartamento() {
        assertNotNull(empleado.getDepartamento()); // Verifica que el departamento no sea nulo
        assertEquals("001", empleado.getDepartamento().getId()); // Verifica que el ID del departamento sea correcto
    }

    // Prueba la obtención de la edad del empleado
    @Test
    void testGetEdad() {
        assertEquals(30, empleado.getEdad()); // Verifica que la edad sea la correcta
    }

    // Prueba la obtención del sexo del empleado
    @Test
    void testGetSexo() {
        assertEquals("Masculino", empleado.getSexo()); // Verifica que el sexo sea el correcto
    }

    // Prueba la obtención de la fecha de contratación del empleado
    @Test
    void testGetFechaContratacion() {
        assertNotNull(empleado.getFechaContratacion()); // Verifica que la fecha de contratación no sea nula
    }
}