package com.IU_DIGITAL.Modelo;


import java.util.Date;


// Clase abstracta que representa a un empleado en la organizacion
public abstract class Empleado {
    private String id;               // Identificador unico
    private String nombre;           // Nombre del empleado
    private String puesto;           // Puesto del empleado
    private TipoEmpleado tipoEmpleado; // Tipo de empleado (Permanente/Temporal)
    private Departamento departamento; // Departamento al que pertenece
    private int edad;                // Edad del empleado
    private String sexo;             // Sexo del empleado
    private Date fechaContratacion;  // Fecha de contratacion


    // Constructor que inicializa los atributos del empleado
    public Empleado(String id, String nombre, String puesto, TipoEmpleado tipoEmpleado,
                    Departamento departamento, int edad, String sexo, Date fechaContratacion) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.tipoEmpleado = tipoEmpleado;
        this.departamento = departamento;
        this.edad = edad;
        this.sexo = sexo;
        this.fechaContratacion = fechaContratacion;
    }


    // Constructor vacio
    public Empleado() {
    }


    // Getters y Setters para acceder y modificar los atributos
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getPuesto() {
        return puesto;
    }


    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }


    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }


    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }


    public Departamento getDepartamento() {
        return departamento;
    }


    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }


    public int getEdad() {
        return edad;
    }


    public void setEdad(int edad) {
        this.edad = edad;
    }


    public String getSexo() {
        return sexo;
    }


    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public Date getFechaContratacion() {
        return fechaContratacion;
    }


    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }


    // Metodo abstracto que debe ser implementado en clases hijas
    public abstract String getTipoEmpleadoString();


    // Metodo toString que devuelve el id del empleado
    public String toString() {
        return id; // Devuelve el id del empleado
    }
}
