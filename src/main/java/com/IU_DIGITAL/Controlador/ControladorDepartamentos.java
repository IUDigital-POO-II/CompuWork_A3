package com.IU_DIGITAL.Controlador;

// Importaciones necesarias para el modelo de datos y excepciones
import com.IU_DIGITAL.Modelo.Departamento;
import com.IU_DIGITAL.Modelo.Empleado;
import com.IU_DIGITAL.Modelo.Excepciones.EmpleadoYaAsignadoException;


import java.util.ArrayList;
import java.util.List;


// Clase ControladorDepartamentos que gestiona la logica de negocios relacionada con los departamentos
public class ControladorDepartamentos {
    // Lista para almacenar los departamentos
    private List<Departamento> departamentos;


    // Constructor que inicializa la lista de departamentos
    public ControladorDepartamentos() {
        this.departamentos = new ArrayList<>();
    }


    // Metodo para crear un nuevo departamento
    public Departamento crearDepartamento(String id, String nombre) {
        // Verifica si el departamento ya existe
        if (departamentoExiste(nombre)) {
            throw new IllegalArgumentException("El departamento ya existe.");
        }
        // Crea y agrega el nuevo departamento a la lista
        Departamento departamento = new Departamento(id, nombre);
        departamentos.add(departamento);
        return departamento;
    }


    // Metodo para eliminar un departamento
    public void eliminarDepartamento(Departamento departamento) {
        departamentos.remove(departamento);
    }


    // Metodo para agregar un empleado a un departamento
    public void agregarEmpleadoADepartamento(Departamento departamento, Empleado empleado) throws EmpleadoYaAsignadoException {
        // Verifica si el empleado ya esta asignado a otro departamento
        for (Departamento dep : departamentos) {
            if (dep.getEmpleados().contains(empleado)) {
                throw new EmpleadoYaAsignadoException("El empleado ya esta asignado al departamento: " + dep.getNombre());
            }
        }
        // Si no esta asignado, agrega el empleado al departamento
        departamento.agregarEmpleado(empleado);
    }


    // Metodo para eliminar un empleado de un departamento
    public void eliminarEmpleadoDeDepartamento(Departamento departamento, Empleado empleado) {
        departamento.eliminarEmpleado(empleado);
    }


    // Metodo para obtener un departamento por su ID
    public Departamento getDepartamentoById(String id) {
        for (Departamento departamento : departamentos) {
            if (departamento.getId().equals(id)) {
                return departamento; // Retorna el departamento si se encuentra
            }
        }
        return null; // Retorna null si no se encuentra el departamento
    }


    // Metodo para obtener la lista de todos los departamentos
    public List<Departamento> getDepartamentos() {
        return new ArrayList<>(departamentos); // Retorna una copia de la lista de departamentos
    }


    // Metodo privado para verificar si un departamento existe por su ID
    private boolean departamentoExiste(String id) {
        return departamentos.stream().anyMatch(dep -> dep.getId().equalsIgnoreCase(id)); // Busca coincidencias en la lista
    }


    // Metodo para modificar un departamento existente
    public void modificarDepartamento(Departamento departamento, String nuevaId, String nuevoNombre) {
        if (departamento != null) {
            departamento.setId(nuevaId); // Cambia el ID del departamento
            departamento.setNombre(nuevoNombre); // Cambia el nombre del departamento
        }
    }
}
