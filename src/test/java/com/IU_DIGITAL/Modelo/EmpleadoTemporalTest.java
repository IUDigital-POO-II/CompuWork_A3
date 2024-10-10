package com.IU_DIGITAL.Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

public class EmpleadoTemporalTest {

    // Variables para el empleado temporal y la fecha final del contrato
    private EmpleadoTemporal empleadoTemporal;
    private Date fechaFinalContrato;

    // Metodo que se ejecuta antes de cada prueba para inicializar el objeto empleado temporal
    @BeforeEach
    public void setUp() {
        // Crear un objeto EmpleadoTemporal con datos iniciales para las pruebas
        fechaFinalContrato = new Date(); // Usamos la fecha actual
        empleadoTemporal = new EmpleadoTemporal("002", "Maria Lopez", "Desarrolladora",
                new Departamento("D002", "Tecnologia"), 28, "Femenino",
                new Date(), fechaFinalContrato, 20.5f);
    }

    // Test para verificar que el metodo getFechaFinalContrato devuelve la fecha final correcta
    @Test
    public void testGetFechaFinalContrato() {
        // Verificamos que la fecha final del contrato es la misma que se asigno inicialmente
        assertEquals(fechaFinalContrato, empleadoTemporal.getFechaFinalContrato());
    }

    // Test para verificar que se puede cambiar la fecha final del contrato y que el metodo setFechaFinalContrato funciona correctamente
    @Test
    public void testSetFechaFinalContrato() {
        // Creamos una nueva fecha y la asignamos al empleado
        Date nuevaFecha = new Date();
        empleadoTemporal.setFechaFinalContrato(nuevaFecha);

        // Verificamos que la fecha final del contrato ha sido actualizada correctamente
        assertEquals(nuevaFecha, empleadoTemporal.getFechaFinalContrato());
    }

    // Test para verificar que el metodo getPagoPorHora devuelve el pago por hora correcto
    @Test
    public void testGetPagoPorHora() {
        // Verificamos que el pago por hora inicial es 20.5
        assertEquals(20.5f, empleadoTemporal.getPagoPorHora(), 0);
    }

    // Test para verificar que se puede actualizar el pago por hora y que el metodo setPagoPorHora funciona correctamente
    @Test
    public void testSetPagoPorHora() {
        // Cambiamos el pago por hora a 22.0
        empleadoTemporal.setPagoPorHora(22.0f);

        // Verificamos que el pago por hora ha sido actualizado correctamente
        assertEquals(22.0f, empleadoTemporal.getPagoPorHora(), 0);
    }

    // Test para verificar que el metodo getTipoEmpleadoString devuelve "Temporal"
    @Test
    public void testGetTipoEmpleadoString() {
        // Verificamos que el tipo de empleado es "Temporal"
        assertEquals("Temporal", empleadoTemporal.getTipoEmpleadoString());
    }
}