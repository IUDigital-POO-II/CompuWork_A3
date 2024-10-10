package com.IU_DIGITAL.Modelo;


import com.IU_DIGITAL.Modelo.Excepciones.ReporteNoGeneradoException;


// Clase que representa un reporte de desempeño de un empleado o departamento
public class ReporteDesempenio {
    private String reportId; // ID unico para el reporte
    private Empleado empleado; // Empleado asociado al reporte
    private Departamento departamento; // Puede ser nulo si es un reporte de empleado
    private String evaluacion; // Descripcion del desempeno
    private int calificacion; // Calificacion numerica del desempeno


    // Constructor para crear un reporte de empleado
    public ReporteDesempenio(String reportId, Empleado empleado, String evaluacion, int calificacion) throws ReporteNoGeneradoException {
        if (empleado == null) { // Verifica si el empleado es nulo
            throw new ReporteNoGeneradoException("No se puede generar el reporte porque el empleado es inexistente.");
        }
        this.reportId = reportId; // Asigna el ID del reporte
        this.empleado = empleado; // Asigna el empleado
        this.evaluacion = evaluacion; // Asigna la evaluacion
        this.calificacion = calificacion; // Asigna la calificacion
    }


    // Constructor para crear un reporte de departamento
    public ReporteDesempenio(String reportId, Departamento departamento, String evaluacion, int calificacion) throws ReporteNoGeneradoException {
        if (departamento == null) { // Verifica si el departamento es nulo
            throw new ReporteNoGeneradoException("No se puede generar el reporte porque el departamento es inexistente.");
        }
        this.reportId = reportId; // Asigna el ID del reporte
        this.departamento = departamento; // Asigna el departamento
        this.evaluacion = evaluacion; // Asigna la evaluacion
        this.calificacion = calificacion; // Asigna la calificacion
    }


    // Getters y Setters para acceder a los atributos
    public String getReportId() {
        return reportId; // Devuelve el ID del reporte
    }


    public Empleado getEmpleado() {
        return empleado; // Devuelve el empleado
    }


    public Departamento getDepartamento() {
        return departamento; // Devuelve el departamento
    }


    public String getEvaluacion() {
        return evaluacion; // Devuelve la evaluacion
    }


    public int getCalificacion() {
        return calificacion; // Devuelve la calificacion
    }


    // Metodo para representar el objeto como una cadena
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Reporte de Desempeño{");
        sb.append("ID='").append(reportId).append('\''); // Agrega el ID al StringBuilder
        if (empleado != null) {
            sb.append(", Empleado=").append(empleado.getNombre()); // Agrega el nombre del empleado si existe
        } else if (departamento != null) {
            sb.append(", Departamento=").append(departamento.getNombre()); // Agrega el nombre del departamento si existe
        }
        sb.append(", Evaluación='").append(evaluacion).append('\''); // Agrega la evaluacion
        sb.append(", Calificación=").append(calificacion); // Agrega la calificacion
        sb.append('}'); // Cierra el StringBuilder
        return sb.toString(); // Devuelve la representacion en cadena del reporte
    }
}
