package com.IU_DIGITAL.Vista;


import com.IU_DIGITAL.Controlador.ControladorDepartamentos;
import com.IU_DIGITAL.Controlador.ControladorEmpleados;
import com.IU_DIGITAL.Controlador.ControladorReportes;


import javax.swing.*;
import java.awt.*;


public class VentanaPrincipal extends JFrame {
    // Controladores para manejar la logica de negocio
    private ControladorDepartamentos controladorDepartamentos;
    private ControladorEmpleados controladorEmpleados;
    private ControladorReportes controladorReportes;


    // Paneles de la interfaz grafica
    private PanelDepartamentos panelDepartamentos;
    private PanelEmpleados panelEmpleados;
    private PanelReportes panelReportes;


    // Constructor de la ventana principal
    public VentanaPrincipal() {
        // Inicializar los controladores para los diferentes modulos
        controladorDepartamentos = new ControladorDepartamentos();
        controladorEmpleados = new ControladorEmpleados();
        controladorReportes = new ControladorReportes(controladorEmpleados);


        // Crear e inicializar el panel de reportes, que genera reportes de empleados y departamentos
        panelReportes = new PanelReportes(controladorEmpleados, controladorDepartamentos, controladorReportes);


        // Crear el panel de empleados y pasarle el panel de reportes, para actualizar la informacion de reportes
        panelEmpleados = new PanelEmpleados(controladorEmpleados, controladorDepartamentos, panelReportes);


        // Crear el panel de departamentos, pasandole los paneles de empleados y reportes para que interactuen
        panelDepartamentos = new PanelDepartamentos(controladorDepartamentos, panelEmpleados, panelReportes);


        // Configurar las propiedades de la ventana principal
        setTitle("Sistema de Gestion de Recursos Humanos - CompuWork");  // Titulo de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Cerrar la aplicacion al cerrar la ventana
        setSize(1280, 720);  // Tamaño inicial de la ventana
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla


        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maximizar la ventana al abrir


        // Configurar el layout principal de la ventana
        setLayout(new BorderLayout());


        // Añadir los paneles a la ventana principal
        add(panelDepartamentos, BorderLayout.WEST);  // Panel de departamentos en el lado izquierdo
        add(panelEmpleados, BorderLayout.CENTER);    // Panel de empleados en el centro
        add(panelReportes, BorderLayout.EAST);       // Panel de reportes en el lado derecho


        // Hacer visible la ventana
        setVisible(true);
    }
}
