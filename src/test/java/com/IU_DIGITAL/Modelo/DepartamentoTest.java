package com.IU_DIGITAL.Modelo;

// Importaciones necesarias para las pruebas
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

// Clase de pruebas para Departamento
public class DepartamentoTest {
    private Departamento departamento; // Instancia del departamento a probar
    private Empleado empleado; // Instancia del empleado a usar en las pruebas

    // Configura el entorno antes de cada prueba
    @BeforeEach
    void setUp() {
        departamento = new Departamento("01", "Desarrollo"); // Inicializa el departamento
        empleado = new EmpleadoConcreto("E001", "Juan Perez", "Desarrollador",
                TipoEmpleado.PERMANENTE, departamento, 30, "M", new Date()); // Crea un empleado
    }

    // Prueba la creación de un departamento
    @Test
    void testCrearDepartamento() {
        assertEquals("Desarrollo", departamento.getNombre()); // Verifica el nombre del departamento
        assertEquals("01", departamento.getId()); // Verifica el ID del departamento
        assertNotNull(departamento.getEmpleados()); // Verifica que la lista de empleados no sea nula
        assertTrue(departamento.getEmpleados().isEmpty()); // Verifica que la lista de empleados esté vacía
    }

    // Prueba agregar un empleado al departamento
    @Test
    void testAgregarEmpleado() {
        departamento.agregarEmpleado(empleado); // Agrega el empleado al departamento
        assertEquals(1, departamento.getEmpleados().size()); // Verifica que hay un empleado en la lista
        assertTrue(departamento.getEmpleados().contains(empleado)); // Verifica que el empleado agregado esté en la lista
    }

    // Prueba eliminar un empleado del departamento
    @Test
    void testEliminarEmpleado() {
        departamento.agregarEmpleado(empleado); // Agrega el empleado al departamento
        assertEquals(1, departamento.getEmpleados().size()); // Verifica que hay un empleado en la lista

        departamento.eliminarEmpleado(empleado); // Elimina el empleado
        assertEquals(0, departamento.getEmpleados().size()); // Verifica que la lista esté vacía
        assertFalse(departamento.getEmpleados().contains(empleado)); // Verifica que el empleado no esté en la lista
    }

    // Prueba listar empleados del departamento
    @Test
    void testListarEmpleados() {
        departamento.agregarEmpleado(empleado); // Agrega el empleado al departamento
        String empleadoNombre = empleado.getNombre(); // Guarda el nombre del empleado

        // Verifica que no se lance una excepción al listar empleados
        assertDoesNotThrow(() -> {
            departamento.listarEmpleados(); // Imprime la lista de empleados
        });

        // Verifica que el empleado se ha agregado correctamente
        assertEquals(1, departamento.getEmpleados().size()); // Verifica que hay un empleado en la lista
        assertTrue(departamento.getEmpleados().stream().anyMatch(emp -> emp.getNombre().equals(empleadoNombre))); // Verifica que el empleado esté en la lista
    }

    // Prueba el método toString del departamento
    @Test
    void testToString() {
        String expected = "ID:01 Nombre:Desarrollo"; // Asegúrate de que el formato sea correcto
        assertEquals(expected, departamento.toString()); // Verifica que la representación en cadena sea la esperada
    }
}