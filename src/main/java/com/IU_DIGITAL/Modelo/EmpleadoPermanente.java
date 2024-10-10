package com.IU_DIGITAL.Modelo;


import java.util.Date;


// Clase que representa a un empleado permanente, hereda de la clase Empleado
public class EmpleadoPermanente extends Empleado {
    private String beneficios; // Beneficios del empleado
    private float salarioBase;  // Salario base del empleado


    // Constructor que inicializa los atributos del empleado permanente
    public EmpleadoPermanente(String id, String nombre, String puesto, Departamento departamento,
                              int edad, String sexo, Date fechaContratacion, String beneficios, float salarioBase) {
        // Llama al constructor de la clase padre (Empleado)
        super(id, nombre, puesto, TipoEmpleado.PERMANENTE, departamento, edad, sexo, fechaContratacion);
        this.beneficios = beneficios; // Inicializa los beneficios
        this.salarioBase = salarioBase; // Inicializa el salario base
    }


    // Getter para obtener los beneficios del empleado
    public String getBeneficios() {
        return beneficios;
    }


    // Setter para establecer los beneficios del empleado
    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }


    // Getter para obtener el salario base del empleado
    public float getSalarioBase() {
        return salarioBase;
    }


    // Setter para establecer el salario base del empleado
    public void setSalarioBase(float salarioBase) {
        this.salarioBase = salarioBase;
    }


    // Implementacion del metodo abstracto que devuelve el tipo de empleado como cadena
    @Override
    public String getTipoEmpleadoString() {
        return "Permanente"; // Devuelve el tipo de empleado como cadena
    }
}
