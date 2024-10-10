package com.IU_DIGITAL.Controlador;

// Importaciones necesarias para los modelos de datos
import com.IU_DIGITAL.Modelo.Departamento;
import com.IU_DIGITAL.Modelo.Empleado;
import com.IU_DIGITAL.Modelo.EmpleadoPermanente;
import com.IU_DIGITAL.Modelo.EmpleadoTemporal;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// Clase ControladorEmpleados que gestiona la logica relacionada con los empleados
public class ControladorEmpleados {
    // Lista para almacenar empleados
    private List<Empleado> empleados;


    // Constructor que inicializa la lista de empleados
    public ControladorEmpleados() {
        this.empleados = new ArrayList<>();
    }


    // Metodo para crear un empleado
    public Empleado crearEmpleado(String id, String nombre, String puesto, Departamento departamento,
                                  int edad, String sexo, boolean esPermanente,
                                  Date fechaFinalContrato, float pagoPorHora,
                                  String beneficios, float salarioBase) {
        // Crea un empleado permanente o temporal según el booleano esPermanente
        if (esPermanente) {
            return new EmpleadoPermanente(id, nombre, puesto, departamento, edad, sexo, new Date(), beneficios, salarioBase);
        } else {
            return new EmpleadoTemporal(id, nombre, puesto, departamento, edad, sexo, new Date(), fechaFinalContrato, pagoPorHora);
        }
    }


    // Metodo para agregar un empleado a la lista
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }


    // Metodo para eliminar un empleado de la lista
    public void eliminarEmpleado(Empleado empleado) {
        empleados.remove(empleado);
    }


    // Metodo para obtener empleados por nombre de departamento
    public List<Empleado> getEmpleadosPorDepartamento(String nombreDepartamento) {
        List<Empleado> empleadosPorDepartamento = new ArrayList<>();
        // Recorre la lista de empleados y agrega los que pertenecen al departamento especificado
        for (Empleado empleado : empleados) {
            if (empleado.getDepartamento().getNombre().equals(nombreDepartamento)) {
                empleadosPorDepartamento.add(empleado);
            }
        }
        return empleadosPorDepartamento;
    }


    // Metodo para obtener un empleado por su ID
    public Empleado getEmpleadoById(String id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {
                return empleado; // Retorna el empleado si se encuentra
            }
        }
        return null; // Retorna null si no se encuentra el empleado
    }


    // Metodo para obtener todos los empleados
    public List<Empleado> getEmpleados() {
        return new ArrayList<>(empleados); // Retorna una copia de la lista de empleados
    }
}
