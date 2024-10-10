package com.IU_DIGITAL.Controlador;

import com.IU_DIGITAL.Modelo.Departamento;
import com.IU_DIGITAL.Modelo.Empleado;
import com.IU_DIGITAL.Modelo.EmpleadoConcreto;
import com.IU_DIGITAL.Modelo.Excepciones.EmpleadoYaAsignadoException;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ControladorDepartamentosTest {

    // Inicializamos el controlador que sera usado en los tests
    private ControladorDepartamentos controlador = new ControladorDepartamentos();

    // Test para verificar que se puede crear correctamente un departamento
    @Test
    void testCrearDepartamento() {
        // Creamos un departamento con ID "D1" y nombre "Marketing"
        Departamento departamento = controlador.crearDepartamento("D1", "Marketing");

        // Verificamos que el departamento no sea nulo, y que sus atributos sean correctos
        assertNotNull(departamento);
        assertEquals("D1", departamento.getId());
        assertEquals("Marketing", departamento.getNombre());
    }

    // Test para verificar que se puede eliminar correctamente un departamento
    @Test
    void testEliminarDepartamento() {
        // Creamos un departamento y luego lo eliminamos
        Departamento departamento = controlador.crearDepartamento("D1", "Marketing");
        controlador.eliminarDepartamento(departamento);

        // Verificamos que el departamento ya no exista
        assertNull(controlador.getDepartamentoById("D1"));
    }

    // Test para agregar un empleado a un departamento correctamente
    @Test
    void testAgregarEmpleadoADepartamento() throws EmpleadoYaAsignadoException {
        // Creamos un departamento y un empleado
        Departamento departamento = controlador.crearDepartamento("D1", "Marketing");
        Empleado empleado = new EmpleadoConcreto("E1", "Juan Perez", "dev", null, null, 18, "Masculino", new Date());

        // Agregamos el empleado al departamento
        controlador.agregarEmpleadoADepartamento(departamento, empleado);

        // Verificamos que el empleado se haya agregado correctamente al departamento
        assertTrue(departamento.getEmpleados().contains(empleado));
    }

    // Test para verificar que no se puede agregar un empleado ya asignado a otro departamento
    @Test
    void testAgregarEmpleadoYaAsignado() throws EmpleadoYaAsignadoException {
        // Creamos dos departamentos y un empleado
        Departamento departamento1 = controlador.crearDepartamento("D1", "Marketing");
        Departamento departamento2 = controlador.crearDepartamento("D2", "Ventas");
        Empleado empleado = new EmpleadoConcreto("E1", "Juan Perez", "dev", null, null, 18, "Masculino", new Date());

        // Agregamos el empleado al primer departamento
        controlador.agregarEmpleadoADepartamento(departamento1, empleado);

        // Intentamos agregar el mismo empleado a otro departamento y esperamos la excepcion
        EmpleadoYaAsignadoException thrown = assertThrows(EmpleadoYaAsignadoException.class, () -> {
            controlador.agregarEmpleadoADepartamento(departamento2, empleado);
        });

        // Verificamos que el mensaje de la excepcion sea correcto
        assertEquals("El empleado ya esta asignado al departamento: Marketing", thrown.getMessage());
    }

    // Test para buscar un departamento por su ID
    @Test
    void testGetDepartamentoById() {
        // Creamos un departamento y lo buscamos por su ID
        Departamento departamento = controlador.crearDepartamento("D1", "Marketing");
        Departamento encontrado = controlador.getDepartamentoById("D1");

        // Verificamos que el departamento se haya encontrado y que tenga el nombre correcto
        assertNotNull(encontrado);
        assertEquals("Marketing", encontrado.getNombre());
    }

    // Test para verificar que se pueden obtener todos los departamentos creados
    @Test
    void testGetDepartamentos() {
        // Creamos dos departamentos
        controlador.crearDepartamento("D1", "Marketing");
        controlador.crearDepartamento("D2", "Ventas");

        // Verificamos que el controlador contiene dos departamentos
        assertEquals(2, controlador.getDepartamentos().size());
    }

    // Test para modificar el nombre de un departamento
    @Test
    void testModificarDepartamento() {
        // Creamos un departamento y lo modificamos
        Departamento departamento = controlador.crearDepartamento("D1", "Marketing");
        controlador.modificarDepartamento(departamento, "D1", "Marketing Modificado");

        // Verificamos que el nombre del departamento haya cambiado correctamente
        assertEquals("Marketing Modificado", departamento.getNombre());
    }
}