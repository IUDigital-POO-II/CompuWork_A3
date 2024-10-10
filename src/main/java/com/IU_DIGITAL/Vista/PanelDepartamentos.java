package com.IU_DIGITAL.Vista;


import com.IU_DIGITAL.Controlador.ControladorDepartamentos;
import com.IU_DIGITAL.Modelo.Departamento;
import com.IU_DIGITAL.Modelo.Empleado;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


// Clase que representa el panel para administrar departamentos
public class PanelDepartamentos extends JPanel {
    private ControladorDepartamentos controladorDepartamentos; // Controlador para gestionar departamentos
    private PanelEmpleados panelEmpleados; // Panel para mostrar empleados
    private PanelReportes panelReportes; // Panel para mostrar reportes


    private JTextField departmentNameField; // Campo de texto para el nombre del departamento
    private JTextField departmentIdField;   // Campo de texto para el ID del departamento
    private JList<String> departmentList;    // Lista de departamentos
    private DefaultListModel<String> listModel; // Modelo de la lista de departamentos
    private JComboBox<Integer> departamentoSelector; // Selector de departamentos
    private DefaultTableModel empleadosModel; // Modelo de la tabla de empleados
    private JTable tablaEmpleados; // Tabla para mostrar empleados


    // Constructor que inicializa el panel
    public PanelDepartamentos(ControladorDepartamentos controladorDepartamentos,
                              PanelEmpleados panelEmpleados,
                              PanelReportes panelReportes) {
        this.controladorDepartamentos = controladorDepartamentos;
        this.panelEmpleados = panelEmpleados;
        this.panelReportes = panelReportes;
        initialize(); // Llama al metodo de inicialización
    }


    // Metodo para inicializar los componentes del panel
    private void initialize() {
        setLayout(new BorderLayout());


        // Panel de formulario para agregar/actualizar departamentos
        JPanel formPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Administrar Departamentos"));


        formPanel.add(new JLabel("ID:"));
        departmentIdField = new JTextField(); // Campo para ID del departamento
        formPanel.add(departmentIdField);


        formPanel.add(new JLabel("Nombre:"));
        departmentNameField = new JTextField(); // Campo para nombre del departamento
        formPanel.add(departmentNameField);


        // Botón para agregar un nuevo departamento
        JButton addDepartmentButton = new JButton("Agregar Departamento");
        addDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = departmentIdField.getText().trim(); // Obtiene ID del campo de texto
                String nombre = departmentNameField.getText().trim(); // Obtiene nombre del campo de texto
                if (!idText.isEmpty() && !nombre.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idText); // Convierte ID a entero
                        controladorDepartamentos.crearDepartamento(String.valueOf(id), nombre); // Crea el departamento
                        actualizarListaDepartamentos(); // Actualiza la lista de departamentos
                        departmentIdField.setText(""); // Limpia el campo de ID
                        departmentNameField.setText(""); // Limpia el campo de nombre
                        JOptionPane.showMessageDialog(null, "Departamento agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El ID y el nombre del departamento no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        formPanel.add(addDepartmentButton); // Agrega el botón al panel


        // Botón para modificar un departamento existente
        JButton modifyDepartmentButton = new JButton("Modificar Departamento");
        modifyDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = departmentList.getSelectedValue(); // Obtiene el departamento seleccionado
                String idText = departmentIdField.getText().trim(); // Obtiene el nuevo ID
                String nuevoNombre = departmentNameField.getText().trim(); // Obtiene el nuevo nombre


                if (selectedValue != null && !nuevoNombre.isEmpty() && !idText.isEmpty()) {
                    try {
                        // Extrae el ID original del departamento seleccionado
                        String originalIdString = selectedValue.split(" - ")[0].split(": ")[1]; // ID: XX
                        int originalId = Integer.parseInt(originalIdString); // Convierte a entero


                        // Crea un nuevo ID a partir del campo de texto
                        int nuevoId = Integer.parseInt(idText);


                        // Busca el departamento correspondiente por ID original
                        Departamento departamento = controladorDepartamentos.getDepartamentos().stream()
                                .filter(dep -> dep.getId().equals(String.valueOf(originalId)))
                                .findFirst().orElse(null);


                        if (departamento != null) {
                            // Actualiza el departamento con el nuevo ID y nombre
                            controladorDepartamentos.modificarDepartamento(departamento, String.valueOf(nuevoId), nuevoNombre);
                            actualizarListaDepartamentos(); // Actualiza la lista de departamentos
                            departmentIdField.setText(""); // Limpia el campo de ID
                            departmentNameField.setText(""); // Limpia el campo de nombre
                            JOptionPane.showMessageDialog(null, "Departamento actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un departamento, escriba un nuevo nombre y el ID correspondiente.");
                }
            }
        });
        formPanel.add(modifyDepartmentButton); // Agrega el botón al panel


        // Botón para eliminar un departamento
        JButton deleteDepartmentButton = new JButton("Eliminar Departamento");
        deleteDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = departmentList.getSelectedValue(); // Obtiene el departamento seleccionado
                if (selectedValue != null) {
                    try {
                        // Extrae el ID de la cadena seleccionada
                        String idString = selectedValue.split(" - ")[0].split(": ")[1]; // ID: XX
                        int id = Integer.parseInt(idString); // Convierte a entero


                        // Busca el departamento correspondiente por ID
                        Departamento departamento = controladorDepartamentos.getDepartamentos().stream()
                                .filter(dep -> dep.getId().equals(String.valueOf(id))) // Comparar por ID como String
                                .findFirst().orElse(null);


                        if (departamento != null) {
                            controladorDepartamentos.eliminarDepartamento(departamento); // Llama al metodo en el controlador
                            actualizarListaDepartamentos(); // Actualiza la vista de departamentos
                            JOptionPane.showMessageDialog(null, "Departamento eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Departamento no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Error al obtener el ID del departamento.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un departamento para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        formPanel.add(deleteDepartmentButton); // Agrega el botón al panel


        add(formPanel, BorderLayout.NORTH); // Agrega el panel de formulario al panel principal


        // Lista de departamentos
        listModel = new DefaultListModel<>(); // Modelo de la lista
        departmentList = new JList<>(listModel); // Crea la lista con el modelo
        JScrollPane listScrollPane = new JScrollPane(departmentList); // Agrega scroll a la lista
        listScrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Departamentos"));
        add(listScrollPane, BorderLayout.CENTER); // Agrega la lista al panel principal


        // Panel para el selector de departamentos y tabla de empleados
        JPanel bottomPanel = new JPanel(new BorderLayout());


        JPanel selectorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        selectorPanel.add(new JLabel("Seleccionar Departamento:"));
        departamentoSelector = new JComboBox<>(); // Crea el selector de departamentos
        departamentoSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEmpleadosDeDepartamento(); // Muestra los empleados del departamento seleccionado
            }
        });
        selectorPanel.add(departamentoSelector); // Agrega el selector al panel
        bottomPanel.add(selectorPanel, BorderLayout.NORTH); // Agrega el panel al panel inferior


        // Tabla de empleados
        String[] columnNames = {"ID", "Nombre", "Puesto", "Edad", "Sexo"}; // Nombres de las columnas
        empleadosModel = new DefaultTableModel(columnNames, 0); // Modelo de la tabla
        tablaEmpleados = new JTable(empleadosModel); // Crea la tabla
        JScrollPane tableScrollPane = new JScrollPane(tablaEmpleados); // Agrega scroll a la tabla
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Empleados del Departamento Seleccionado"));
        bottomPanel.add(tableScrollPane, BorderLayout.CENTER); // Agrega la tabla al panel inferior


        add(bottomPanel, BorderLayout.SOUTH); // Agrega el panel inferior al panel principal


        // Inicializa la lista y el selector de departamentos
        actualizarListaDepartamentos(); // Llama al metodo para actualizar la lista de departamentos
    }


    // Metodo para actualizar la lista de departamentos en la vista
    private void actualizarListaDepartamentos() {
        listModel.clear(); // Limpia el modelo de la lista
        departamentoSelector.removeAllItems(); // Limpia el selector de departamentos


        List<Departamento> departamentos = controladorDepartamentos.getDepartamentos(); // Obtiene la lista de departamentos


        // Crea un arreglo para pasar a PanelEmpleados
        String[] departamentosArray = new String[departamentos.size()];


        for (int i = 0; i < departamentos.size(); i++) {
            Departamento departamento = departamentos.get(i);


            // Para el JList (información completa)
            listModel.addElement("ID: " + departamento.getId() + " - Nombre: " + departamento.getNombre());


            // Para el JComboBox (solo el ID)
            departamentoSelector.addItem(Integer.parseInt(departamento.getId()));


            // Rellenar el arreglo para el JComboBox en PanelEmpleados
            departamentosArray[i] = departamento.getId(); // Solo el ID
        }


        // Actualiza el JComboBox en otros paneles
        panelEmpleados.actualizarComboDepartamentos(departamentosArray);
        panelReportes.actualizarComboDepartamentos(departamentosArray);


        if (departamentoSelector.getItemCount() > 0) {
            departamentoSelector.setSelectedIndex(0); // Selecciona el primer departamento
            mostrarEmpleadosDeDepartamento(); // Muestra los empleados del departamento seleccionado
        } else {
            empleadosModel.setRowCount(0); // Limpia la tabla si no hay departamentos
        }
    }


    // Metodo para mostrar empleados del departamento seleccionado
    private void mostrarEmpleadosDeDepartamento() {
        // Obtiene el ID del departamento seleccionado
        Integer departamentoSeleccionadoId = (Integer) departamentoSelector.getSelectedItem();
        if (departamentoSeleccionadoId != null) {
            // Busca el departamento correspondiente al ID seleccionado
            Departamento departamento = controladorDepartamentos.getDepartamentos().stream()
                    .filter(dep -> dep.getId().equals(String.valueOf(departamentoSeleccionadoId))) // Compara como String
                    .findFirst()
                    .orElse(null);
            if (departamento == null) {
                JOptionPane.showMessageDialog(null, "Departamento no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (departamento != null) {
                // Limpia la tabla antes de agregar nuevos datos
                empleadosModel.setRowCount(0);
                // Agrega empleados del departamento a la tabla
                List<Empleado> empleados = departamento.getEmpleados(); // Obtiene la lista de empleados
                System.out.println("Número de empleados en el departamento: " + empleados.size());
                for (Empleado empleado : empleados) {
                    empleadosModel.addRow(new Object[]{
                            empleado.getId(),
                            empleado.getNombre(),
                            empleado.getPuesto(),
                            empleado.getEdad(),
                            empleado.getSexo()
                    });
                }


                // Actualiza la vista de empleados en PanelEmpleados
                panelEmpleados.actualizarVistaEmpleados(empleados);
            }
        }
    }
}
