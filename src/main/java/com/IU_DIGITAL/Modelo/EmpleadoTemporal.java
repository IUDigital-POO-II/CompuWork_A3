package com.IU_DIGITAL.Modelo;


import java.util.Date;


// Clase que representa a un empleado temporal, hereda de la clase Empleado
public class EmpleadoTemporal extends Empleado {
    private Date fechaFinalContrato; // Fecha de finalizacion del contrato
    private float pagoPorHora;        // Pago por hora del empleado


    // Constructor que inicializa los atributos del empleado temporal
    public EmpleadoTemporal(String id, String nombre, String puesto, Departamento departamento,
                            int edad, String sexo, Date fechaContratacion, Date fechaFinalContrato, float pagoPorHora) {
        // Llama al constructor de la clase padre (Empleado)
        super(id, nombre, puesto, TipoEmpleado.TEMPORAL, departamento, edad, sexo, fechaContratacion);
        this.fechaFinalContrato = fechaFinalContrato; // Inicializa la fecha de finalizacion del contrato
        this.pagoPorHora = pagoPorHora; // Inicializa el pago por hora
    }


    // Getter para obtener la fecha de finalizacion del contrato
    public Date getFechaFinalContrato() {
        return fechaFinalContrato;
    }


    // Setter para establecer la fecha de finalizacion del contrato
    public void setFechaFinalContrato(Date fechaFinalContrato) {
        this.fechaFinalContrato = fechaFinalContrato;
    }


    // Getter para obtener el pago por hora del empleado
    public float getPagoPorHora() {
        return pagoPorHora;
    }


    // Setter para establecer el pago por hora del empleado
    public void setPagoPorHora(float pagoPorHora) {
        this.pagoPorHora = pagoPorHora;
    }


    // Implementacion del metodo abstracto que devuelve el tipo de empleado como cadena
    @Override
    public String getTipoEmpleadoString() {
        return "Temporal"; // Devuelve el tipo de empleado como cadena
    }
}
