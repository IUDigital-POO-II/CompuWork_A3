package com.IU_DIGITAL.Controlador;

// Importaciones necesarias para los modelos de datos y excepciones
import com.IU_DIGITAL.Modelo.Departamento;
import com.IU_DIGITAL.Modelo.Empleado;
import com.IU_DIGITAL.Modelo.Excepciones.ReporteNoGeneradoException;
import com.IU_DIGITAL.Modelo.ReporteDesempenio;


import java.util.List;


// Clase ControladorReportes que gestiona la generacion de reportes
public class ControladorReportes {
    // Controlador de empleados utilizado para acceder a la lista de empleados
    private ControladorEmpleados controladorEmpleados;


    // Constructor que recibe un ControladorEmpleados
    public ControladorReportes(ControladorEmpleados controladorEmpleados) {
        this.controladorEmpleados = controladorEmpleados;
    }


    // Metodo para obtener la lista de todos los empleados
    public List<Empleado> getEmpleados() {
        return controladorEmpleados.getEmpleados();
    }


    // Metodo para generar un reporte para un empleado
    public ReporteDesempenio generarReporteEmpleado(Empleado empleado, String evaluacion, int calificacion) throws ReporteNoGeneradoException {
        // Verifica si el empleado es nulo
        if (empleado == null) {
            throw new ReporteNoGeneradoException("El empleado no puede ser nulo.");
        }
        // Genera un nuevo reporte de desempenio para el empleado
        return new ReporteDesempenio("RPT-001", empleado, evaluacion, calificacion); // Genera un ID unico en la vista
    }


    // Metodo para generar un reporte para un departamento
    public ReporteDesempenio generarReporteDepartamento(Departamento departamento, String evaluacion, int calificacion) throws ReporteNoGeneradoException {
        // Verifica si el departamento es nulo
        if (departamento == null) {
            throw new ReporteNoGeneradoException("El departamento no puede ser nulo.");
        }
        // Genera un nuevo reporte de desempenio para el departamento
        return new ReporteDesempenio("RPT-002", departamento, evaluacion, calificacion); // Genera un ID unico en la vista
    }
}
