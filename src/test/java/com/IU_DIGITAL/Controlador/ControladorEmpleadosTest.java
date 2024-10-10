package com.IU_DIGITAL.Controlador;

// Importaciones necesarias para los modelos de datos y las pruebas
import com.IU_DIGITAL.Modelo.Departamento;
import com.IU_DIGITAL.Modelo.Empleado;
import com.IU_DIGITAL.Modelo.EmpleadoPermanente;
import com.IU_DIGITAL.Modelo.EmpleadoTemporal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

// Clase de pruebas para ControladorEmpleados
public class ControladorEmpleadosTest {
    private ControladorEmpleados controlador; // Controlador a probar
    private Departamento departamento; // Departamento para los empleados

    // Configura el entorno antes de cada prueba
    @BeforeEach
    void setUp() {
        controlador = new ControladorEmpleados(); // Inicializa el controlador
        departamento = new Departamento("001", "Recursos Humanos"); // Crea un departamento
    }

    // Prueba la creación de un empleado permanente
    @Test
    void testCrearEmpleadoPermanente() {
        Empleado empleado = controlador.crearEmpleado("E001", "Juan Perez", "Gerente", departamento, 30, "Masculino",
                true, null, 0, "Seguro médico", 50000);

        assertNotNull(empleado); // Verifica que el empleado no sea nulo
        assertTrue(empleado instanceof EmpleadoPermanente); // Verifica que sea un empleado permanente
        assertEquals("E001", empleado.getId()); // Verifica el ID del empleado
        assertEquals("Juan Perez", empleado.getNombre()); // Verifica el nombre del empleado
    }

    // Prueba la creación de un empleado temporal
    @Test
    void testCrearEmpleadoTemporal() {
        Empleado empleado = controlador.crearEmpleado("E002", "Maria Lopez", "Asistente", departamento, 25, "Femenino",
                false, new Date(), 20, "Ninguno", 0);

        assertNotNull(empleado); // Verifica que el empleado no sea nulo
        assertTrue(empleado instanceof EmpleadoTemporal); // Verifica que sea un empleado temporal
        assertEquals("E002", empleado.getId()); // Verifica el ID del empleado
        assertEquals("Maria Lopez", empleado.getNombre()); // Verifica el nombre del empleado
    }

    // Prueba agregar un empleado a la lista
    @Test
    void testAgregarEmpleado() {
        Empleado empleado = controlador.crearEmpleado("E003", "Carlos Garcia", "Analista", departamento, 28, "Masculino",
                true, null, 0, "Seguro médico", 40000);
        controlador.agregarEmpleado(empleado); // Agrega el empleado al controlador

        assertEquals(1, controlador.getEmpleados().size()); // Verifica que haya un empleado en la lista
        assertEquals(empleado, controlador.getEmpleados().get(0)); // Verifica que el empleado agregado sea el correcto
    }

    // Prueba eliminar un empleado de la lista
    @Test
    void testEliminarEmpleado() {
        Empleado empleado = controlador.crearEmpleado("E004", "Laura Martinez", "Desarrolladora", departamento, 26, "Femenino",
                true, null, 0, "Seguro médico", 45000);
        controlador.agregarEmpleado(empleado); // Agrega el empleado al controlador
        controlador.eliminarEmpleado(empleado); // Elimina el empleado

        assertEquals(0, controlador.getEmpleados().size()); // Verifica que la lista esté vacía
    }

    // Prueba obtener empleados por departamento
    @Test
    void testGetEmpleadosPorDepartamento() {
        Empleado empleado1 = controlador.crearEmpleado("E005", "Pedro Sanchez", "Analista", departamento, 32, "Masculino",
                true, null, 0, "Seguro médico", 55000);
        Empleado empleado2 = controlador.crearEmpleado("E006", "Ana Ruiz", "Contadora", new Departamento("002", "Finanzas"), 29, "Femenino",
                true, null, 0, "Seguro médico", 52000);

        controlador.agregarEmpleado(empleado1); // Agrega el empleado al departamento
        controlador.agregarEmpleado(empleado2); // Agrega un empleado de otro departamento

        assertEquals(1, controlador.getEmpleadosPorDepartamento("Recursos Humanos").size()); // Verifica el conteo de empleados
        assertEquals(empleado1, controlador.getEmpleadosPorDepartamento("Recursos Humanos").get(0)); // Verifica que el empleado sea el correcto
    }

    // Prueba obtener un empleado por ID
    @Test
    void testGetEmpleadoById() {
        Empleado empleado = controlador.crearEmpleado("E007", "Jorge Torres", "Director", departamento, 35, "Masculino",
                true, null, 0, "Seguro médico", 70000);
        controlador.agregarEmpleado(empleado); // Agrega el empleado al controlador

        Empleado encontrado = controlador.getEmpleadoById("E007"); // Busca el empleado por ID
        assertNotNull(encontrado); // Verifica que el empleado no sea nulo
        assertEquals(empleado, encontrado); // Verifica que el empleado encontrado sea el correcto
    }

    // Prueba obtener un empleado por ID que no existe
    @Test
    void testGetEmpleadoById_NoExiste() {
        Empleado encontrado = controlador.getEmpleadoById("E999"); // Busca un ID que no existe
        assertNull(encontrado); // Verifica que el resultado sea nulo
    }
}
