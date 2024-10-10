package com.IU_DIGITAL.Modelo;


import java.util.Date;


// Clase que representa un empleado concreto que hereda de la clase abstracta Empleado
public class EmpleadoConcreto extends Empleado {


    // Constructor que inicializa los atributos del empleado concreto
    public EmpleadoConcreto(String id, String nombre, String puesto, TipoEmpleado tipoEmpleado,
                            Departamento departamento, int edad, String sexo, Date fechaContratacion) {
        // Llama al constructor de la clase padre (Empleado) para inicializar los atributos
        super(id, nombre, puesto, tipoEmpleado, departamento, edad, sexo, fechaContratacion);
    }


    // Implementacion del metodo abstracto que devuelve el tipo de empleado como cadena
    @Override
    public String getTipoEmpleadoString() {
        return getTipoEmpleado().name(); // Devuelve el nombre del enum TipoEmpleado
    }
}
