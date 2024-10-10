package com.IU_DIGITAL.Modelo.Excepciones;


// Clase de excepción personalizada para indicar que un empleado ya ha sido asignado
public class EmpleadoYaAsignadoException extends Exception {
    // Constructor que recibe un mensaje de error
    public EmpleadoYaAsignadoException(String mensaje) {
        super(mensaje); // Llama al constructor de la clase padre (Exception)
    }
}
