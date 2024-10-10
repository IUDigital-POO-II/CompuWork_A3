package com.IU_DIGITAL;

import com.IU_DIGITAL.Vista.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Usamos SwingUtilities.invokeLater para asegurarnos de que la creacion y manipulacion
        // de componentes de la interfaz grafica se realice en el hilo de despacho de eventos (EDT).
        SwingUtilities.invokeLater(() -> {

            // Crear una instancia de la ventana principal de la aplicacion
            VentanaPrincipal app = new VentanaPrincipal();

            // Hacer visible la ventana principal
            app.setVisible(true);
        });
    }
}