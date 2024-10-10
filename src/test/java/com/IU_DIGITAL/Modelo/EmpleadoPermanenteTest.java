package com.IU_DIGITAL.Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

public class EmpleadoPermanenteTest {

    // Variable para almacenar el objeto empleado permanente que se va a probar
    private EmpleadoPermanente empleadoPermanente;

    // Metodo que se ejecuta antes de cada prueba para inicializar el objeto empleado permanente
    @BeforeEach
    public void setUp() {
        // Crear un objeto EmpleadoPermanente con datos iniciales para las pruebas
        empleadoPermanente = new EmpleadoPermanente("001", "Juan Perez", "Gerente",
                new Departamento("D001", "Recursos Humanos"), 35, "Masculino",
                new Date(), "Seguro medico", 50000);
    }

    // Test para verificar que el metodo getBeneficios devuelve el beneficio correcto
    @Test
    public void testGetBeneficios() {
        // Verificamos que el beneficio inicial es "Seguro medico"
        assertEquals("Seguro medico", empleadoPermanente.getBeneficios());
    }

    // Test para verificar que se pueden cambiar los beneficios y que el metodo setBeneficios funciona correctamente
    @Test
    public void testSetBeneficios() {
        // Cambiamos el beneficio a "Bono anual"
        empleadoPermanente.setBeneficios("Bono anual");

        // Verificamos que el beneficio ha sido actualizado correctamente
        assertEquals("Bono anual", empleadoPermanente.getBeneficios());
    }

    // Test para verificar que el metodo getSalarioBase devuelve el salario correcto
    @Test
    public void testGetSalarioBase() {
        // Verificamos que el salario base inicial es 50000
        assertEquals(50000, empleadoPermanente.getSalarioBase(), 0);
    }

    // Test para verificar que se puede actualizar el salario base y que el metodo setSalarioBase funciona correctamente
    @Test
    public void testSetSalarioBase() {
        // Cambiamos el salario base a 55000
        empleadoPermanente.setSalarioBase(55000);

        // Verificamos que el salario base ha sido actualizado correctamente
        assertEquals(55000, empleadoPermanente.getSalarioBase(), 0);
    }

    // Test para verificar que el metodo getTipoEmpleadoString devuelve "Permanente"
    @Test
    public void testGetTipoEmpleadoString() {
        // Verificamos que el tipo de empleado es "Permanente"
        assertEquals("Permanente", empleadoPermanente.getTipoEmpleadoString());
    }
}