package com.IU_DIGITAL.Modelo.Excepciones;


// Clase de excepción personalizada para indicar que un reporte no pudo ser generado
public class ReporteNoGeneradoException extends Exception {
    // Constructor que recibe un mensaje de error
    public ReporteNoGeneradoException(String mensaje) {
        super(mensaje); // Llama al constructor de la clase padre (Exception)
    }
}
