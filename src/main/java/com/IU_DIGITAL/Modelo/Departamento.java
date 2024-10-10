package com.IU_DIGITAL.Modelo;


import java.util.ArrayList;
import java.util.List;


// Clase que representa un Departamento en la organizacion
public class Departamento {
    private String id; // Identificador del departamento
    private String nombre; // Nombre del departamento
    private List<Empleado> empleados; // Lista de empleados en el departamento


    // Constructor que inicializa el departamento con un ID y un nombre
    public Departamento(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.empleados = new ArrayList<>(); // Inicializa la lista de empleados
    }


    // Constructor vacio
    public Departamento() {
    }


    // Getter para el nombre del departamento
    public String getNombre() {
        return nombre;
    }


    // Setter para el nombre del departamento
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    // Getter para el ID del departamento
    public String getId() {
        return id;
    }


    // Setter para el ID del departamento
    public void setId(String id) {
        this.id = id;
    }


    // Metodo para agregar un empleado al departamento
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }


    // Metodo para eliminar un empleado del departamento
    public void eliminarEmpleado(Empleado empleado) {
        empleados.remove(empleado);
    }


    // Getter para la lista de empleados
    public List<Empleado> getEmpleados() {
        return empleados;
    }


    // Metodo que lista los nombres de los empleados
    public void listarEmpleados() {
        for (Empleado empleado : empleados) {
            System.out.println(empleado.getNombre()); // Imprime el nombre de cada empleado
        }
    }


    // Metodo toString para representar el departamento como una cadena
    @Override
    public String toString() {
        return "ID:" + id + " Nombre:" + nombre; // Representa el departamento con ID y nombre
    }
}
