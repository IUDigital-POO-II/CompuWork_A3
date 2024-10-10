package com.IU_DIGITAL.Vista;


import com.IU_DIGITAL.Controlador.ControladorDepartamentos;
import com.IU_DIGITAL.Controlador.ControladorEmpleados;
import com.IU_DIGITAL.Controlador.ControladorReportes;
import com.IU_DIGITAL.Modelo.Departamento;
import com.IU_DIGITAL.Modelo.Empleado;
import com.IU_DIGITAL.Modelo.Excepciones.ReporteNoGeneradoException;
import com.IU_DIGITAL.Modelo.ReporteDesempenio;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class PanelReportes extends JPanel {
    // Controladores para empleados, departamentos y reportes
    private ControladorEmpleados controladorEmpleados;
    private ControladorDepartamentos controladorDepartamentos;
    private ControladorReportes controladorReportes;


    // Campos de texto y combo boxes para la interfaz
    private JTextField calificacionField; // Campo para la calificacion
    private List<ReporteDesempenio> reportesGenerados; // Lista para almacenar los reportes generados
    private JComboBox<String> departamentoCombo;
    private JComboBox<String> empleadoCombo;
    private JTextField descripcionField;
    private JTable empleadosTable;
    private DefaultTableModel tableModel;
    private int reportIdCounter = 1; // Contador para generar IDs de reportes


    // Constructor del panel de reportes
    public PanelReportes(ControladorEmpleados controladorEmpleados, ControladorDepartamentos controladorDepartamentos, ControladorReportes controladorReportes) {
        this.controladorReportes = controladorReportes;
        this.controladorEmpleados = controladorEmpleados;
        this.controladorDepartamentos = controladorDepartamentos;
        reportesGenerados = new ArrayList<>();
        initialize();  // Inicializar los componentes de la interfaz
    }


    // Metodo para inicializar los componentes de la interfaz
    private void initialize() {
        setLayout(new BorderLayout());


        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Administrar Reportes"));


        // Combo box para seleccionar el departamento
        formPanel.add(new JLabel("Departamento:"));
        departamentoCombo = new JComboBox<>();
        cargarDepartamentos();  // Metodo para cargar los departamentos en el combo
        formPanel.add(departamentoCombo);


        // Combo box para seleccionar el empleado
        formPanel.add(new JLabel("Empleado:"));
        empleadoCombo = new JComboBox<>();
        actualizarComboEmpleados();  // Metodo para cargar los empleados en el combo
        formPanel.add(empleadoCombo);


        // Campo de texto para la descripcion del reporte
        formPanel.add(new JLabel("Descripcion:"));
        descripcionField = new JTextField();
        formPanel.add(descripcionField);


        // Campo de texto para la calificacion
        formPanel.add(new JLabel("Calificacion:"));
        calificacionField = new JTextField();
        formPanel.add(calificacionField);


        // Boton para generar reporte de departamento
        JButton generateReportDeptButton = new JButton("Generar Reporte Departamento");
        generateReportDeptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteDepartamento();  // Llamar al metodo para generar reporte de departamento
            }
        });
        formPanel.add(generateReportDeptButton);


        // Boton para generar reporte de empleado
        JButton generateReporteEmpButton = new JButton("Generar Reporte Empleado");
        generateReporteEmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteEmpleado();  // Llamar al metodo para generar reporte de empleado
            }
        });
        formPanel.add(generateReporteEmpButton);


        add(formPanel, BorderLayout.NORTH);  // Agregar el panel de formulario al norte de la interfaz


        // Tabla para mostrar los reportes generados
        empleadosTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(empleadosTable);
        tableModel = new DefaultTableModel(new String[]{"ID", "Empleado/Departamento", "Descripcion de reporte", "Calificacion"}, 0);
        empleadosTable.setModel(tableModel);
        add(scrollPane, BorderLayout.CENTER);  // Agregar la tabla en el centro de la interfaz
    }


    // Metodo para actualizar los departamentos en el combo box
    public void actualizarComboDepartamentos(String[] departamentosArray) {
        departamentoCombo.removeAllItems();
        departamentoCombo.addItem("Seleccione");  // Opcion inicial
        if (departamentosArray != null) {
            for (String departamentoId : departamentosArray) {
                departamentoCombo.addItem(departamentoId);  // Agregar IDs de departamentos
            }
        }
    }


    // Metodo para cargar los departamentos desde el controlador
    private void cargarDepartamentos() {
        departamentoCombo.removeAllItems();
        departamentoCombo.addItem("Seleccione");
        List<Departamento> departamentos = controladorDepartamentos.getDepartamentos();
        if (departamentos != null) {
            for (Departamento departamento : departamentos) {
                departamentoCombo.addItem(departamento.getId());  // Agregar IDs de departamentos
            }
        }
    }


    // Metodo para actualizar los empleados en el combo box
    public void actualizarComboEmpleados() {
        empleadoCombo.removeAllItems();
        empleadoCombo.addItem("Seleccione");
        List<Empleado> empleados = controladorEmpleados.getEmpleados();
        if (empleados != null) {
            for (Empleado empleado : empleados) {
                empleadoCombo.addItem(empleado.getId());  // Agregar IDs de empleados
            }
        }
    }


    // Metodo para generar un reporte de departamento
    private void generarReporteDepartamento() {
        String departamentoId = (String) departamentoCombo.getSelectedItem();
        if (departamentoId == null || departamentoId.equals("Seleccione")) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un departamento.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }


        String descripcion = descripcionField.getText().trim();
        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una descripcion del reporte.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }


        int calificacion;
        try {
            calificacion = Integer.parseInt(calificacionField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una calificacion valida.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }


        // Generamos un ID para el reporte
        String reportId = String.format("RPT-%03d", reportIdCounter++);
        Departamento departamento = controladorDepartamentos.getDepartamentoById(departamentoId);


        if (departamento == null) {
            JOptionPane.showMessageDialog(this, "Departamento no encontrado.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }


        // Intentamos generar el reporte
        try {
            ReporteDesempenio reporte = controladorReportes.generarReporteDepartamento(departamento, descripcion, calificacion);
            reportesGenerados.add(reporte);  // Agregamos el reporte a la lista
            tableModel.addRow(new Object[]{reportId, "DepartamentoID: " + departamentoId, descripcion, calificacion});
            JOptionPane.showMessageDialog(this, "Reporte de departamento generado con exito.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (ReporteNoGeneradoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Metodo para generar un reporte de empleado
    private void generarReporteEmpleado() {
        String empleadoId = (String) empleadoCombo.getSelectedItem();
        if (empleadoId == null || empleadoId.equals("Seleccione")) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un empleado.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }


        String descripcion = descripcionField.getText().trim();
        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una descripcion del reporte.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }


        int calificacion;
        try {
            calificacion = Integer.parseInt(calificacionField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una calificacion valida.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }


        // Generamos un ID para el reporte
        String reportId = String.format("RPT-%03d", reportIdCounter++);
        Empleado empleado = controladorEmpleados.getEmpleadoById(empleadoId);


        if (empleado == null) {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }


        // Intentamos generar el reporte
        try {
            ReporteDesempenio reporte = controladorReportes.generarReporteEmpleado(empleado, descripcion, calificacion);
            reportesGenerados.add(reporte);  // Agregamos el reporte a la lista
            tableModel.addRow(new Object[]{reportId, "EmpleadoID: " + empleadoId, descripcion, calificacion});
            JOptionPane.showMessageDialog(this, "Reporte de empleado generado con exito.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (ReporteNoGeneradoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
