package com.IU_DIGITAL.Vista;


import com.IU_DIGITAL.Controlador.ControladorEmpleados;
import com.IU_DIGITAL.Controlador.ControladorDepartamentos;
import com.IU_DIGITAL.Modelo.*;
import com.IU_DIGITAL.Modelo.Excepciones.EmpleadoYaAsignadoException;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class PanelEmpleados extends JPanel {
    // Controladores de empleados y departamentos
    private ControladorEmpleados controladorEmpleados;
    private ControladorDepartamentos controladorDepartamentos;
    private PanelReportes panelReportes;  // Referencia al panel de reportes para actualizar datos
    // Campos de texto y componentes de la interfaz
    private JTextField nombreField;
    private JTextField idField;
    private JTextField puestoField;
    private JTextField edadField;
    private JComboBox<String> sexoField;
    private JComboBox<String> tipoEmpleadoCombo;
    private JTextField salarioBaseField;
    private JTextField beneficiosField;
    private JTextField pagoPorHoraField;
    private JTextField fechaFinalContratoField;
    private JTextField fechaContratacionField;
    private JComboBox<String> departamentoCombo;
    private Empleado empleadoModificado;  // Referencia al empleado que se va a modificar
    private JButton guardarCambiosButton;
    private JTable empleadosTable;
    private DefaultTableModel tableModel;


    // Constructor del panel de empleados
    public PanelEmpleados(ControladorEmpleados controladorEmpleados, ControladorDepartamentos controladorDepartamentos, PanelReportes panelReportes) {
        this.controladorEmpleados = controladorEmpleados;
        this.controladorDepartamentos = controladorDepartamentos;
        this.panelReportes = panelReportes;
        initialize();  // Metodo para inicializar los componentes
    }


    // Metodo para inicializar los componentes de la interfaz
    private void initialize() {
        setLayout(new BorderLayout());


        // Panel de formulario para la gestion de empleados
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 6, 6));
        formPanel.setBorder(BorderFactory.createTitledBorder("Administrar Empleados"));


        // Agregamos los campos al formulario
        formPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        formPanel.add(idField);


        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);


        formPanel.add(new JLabel("Puesto:"));
        puestoField = new JTextField();
        formPanel.add(puestoField);


        formPanel.add(new JLabel("Edad:"));
        edadField = new JTextField();
        formPanel.add(edadField);


        formPanel.add(new JLabel("Sexo:"));
        sexoField = new JComboBox<>(new String[]{"Seleccione", "Masculino", "Femenino"});
        formPanel.add(sexoField);


        formPanel.add(new JLabel("Fecha de Contratacion (DD/MM/YYYY):"));
        fechaContratacionField = new JTextField();
        formPanel.add(fechaContratacionField);


        // Combo box para seleccionar el tipo de empleado
        formPanel.add(new JLabel("Tipo de Empleado:"));
        String[] tipos = {"Seleccione", "Permanente", "Temporal"};
        tipoEmpleadoCombo = new JComboBox<>(tipos);
        formPanel.add(tipoEmpleadoCombo);


        // Campos especificos para empleado permanente
        formPanel.add(new JLabel("Salario Base:"));
        salarioBaseField = new JTextField();
        salarioBaseField.setVisible(false);
        formPanel.add(salarioBaseField);


        formPanel.add(new JLabel("Beneficios:"));
        beneficiosField = new JTextField();
        beneficiosField.setVisible(false);
        formPanel.add(beneficiosField);


        // Campos especificos para empleado temporal
        formPanel.add(new JLabel("Pago por Hora:"));
        pagoPorHoraField = new JTextField();
        pagoPorHoraField.setVisible(false);
        formPanel.add(pagoPorHoraField);


        formPanel.add(new JLabel("Fecha Final de Contrato (DD/MM/YYYY):"));
        fechaFinalContratoField = new JTextField();
        fechaFinalContratoField.setVisible(false);
        formPanel.add(fechaFinalContratoField);


        // Combo box para seleccionar el departamento
        formPanel.add(new JLabel("Departamento:"));
        departamentoCombo = new JComboBox<>();
        cargarDepartamentos();  // Metodo para cargar los departamentos disponibles
        formPanel.add(departamentoCombo);


        // Boton para agregar empleado
        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(e -> agregarEmpleado(tipoEmpleadoCombo));
        formPanel.add(agregarButton);


        // Boton para modificar empleado
        JButton modificarButton = new JButton("Modificar");
        modificarButton.addActionListener(e -> modificarEmpleado());
        formPanel.add(modificarButton);


        // Boton para eliminar empleado
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(e -> eliminarEmpleado());
        formPanel.add(eliminarButton);


        // Boton para guardar cambios al modificar empleado
        guardarCambiosButton = new JButton("Guardar Cambios");
        guardarCambiosButton.setVisible(false);
        guardarCambiosButton.addActionListener(e -> {
            if (empleadoModificado != null) {
                guardarCambiosEmpleado(empleadoModificado);
            }
        });
        formPanel.add(guardarCambiosButton);


        add(formPanel, BorderLayout.NORTH);


        // Tabla de empleados para mostrar la lista de empleados
        String[] columnNames = {"ID", "Nombre", "Puesto", "Edad", "Sexo", "Fecha Contratacion", "Tipo de Empleado", "Salario Base", "Beneficios", "Pago por Hora", "Fecha Final", "Departamento"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Hacemos las celdas no editables
            }
        };
        empleadosTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(empleadosTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Empleados"));
        add(tableScrollPane, BorderLayout.CENTER);


        // Listener para mostrar/ocultar campos segun el tipo de empleado seleccionado
        tipoEmpleadoCombo.addActionListener(e -> {
            boolean esPermanente = tipoEmpleadoCombo.getSelectedItem().equals("Permanente");
            boolean esTemporal = tipoEmpleadoCombo.getSelectedItem().equals("Temporal");


            salarioBaseField.setVisible(esPermanente);
            beneficiosField.setVisible(esPermanente);
            pagoPorHoraField.setVisible(esTemporal);
            fechaFinalContratoField.setVisible(esTemporal);


            revalidate();
            repaint();  // Actualizamos la interfaz
        });
    }


    // Metodo para cargar los departamentos en el combo box
    private void cargarDepartamentos() {
        departamentoCombo.removeAllItems();
        departamentoCombo.addItem("Seleccione");
        List<Departamento> departamentos = controladorDepartamentos.getDepartamentos();
        if (departamentos != null) {
            for (Departamento departamento : departamentos) {
                departamentoCombo.addItem(departamento.getId());  // Agregamos el ID del departamento
            }
        }
    }


    // Metodo para agregar un nuevo empleado
    private void agregarEmpleado(JComboBox<String> tipoEmpleadoCombo) {
        try {
            // Validamos los campos
            String nombre = validarCampo(nombreField.getText(), "Nombre");
            String id = validarCampo(idField.getText(), "ID");


            // Verificamos si el empleado ya existe
            if (controladorEmpleados.getEmpleados().stream().anyMatch(e -> e.getId().equals(id))) {
                throw new Exception("Ya existe un empleado con el ID " + id);
            } else if (controladorDepartamentos.getDepartamentos().stream().anyMatch(dep -> dep.getEmpleados().stream().anyMatch(e -> e.getId().equals(id)))) {
                throw new EmpleadoYaAsignadoException("Ya existe un empleado con el ID " + id + " en otro departamento.");
            }


            String puesto = validarCampo(puestoField.getText(), "Puesto");
            int edad = Integer.parseInt(validarCampo(edadField.getText(), "Edad"));
            String sexo = (String) sexoField.getSelectedItem();
            if (sexo.equals("Seleccione")) {
                throw new Exception("Por favor, seleccione un sexo.");
            }


            // Validamos la fecha de contratacion
            Date fechaContratacion = validarFecha(fechaContratacionField.getText(), "Fecha de Contratacion");


            // Verificamos el departamento seleccionado
            String departamentoId = (String) departamentoCombo.getSelectedItem();
            if (departamentoId.equals("Seleccione")) {
                throw new Exception("Por favor, seleccione un departamento.");
            }


            // Obtenemos el departamento
            Departamento departamento = controladorDepartamentos.getDepartamentos().stream()
                    .filter(dep -> dep.getId().equals(departamentoId))
                    .findFirst()
                    .orElse(null);


            // Creamos el empleado segun su tipo
            Empleado empleado;
            boolean esPermanente = tipoEmpleadoCombo.getSelectedItem().equals("Permanente");
            boolean esTemporal = tipoEmpleadoCombo.getSelectedItem().equals("Temporal");
            if (!esPermanente && !esTemporal) {
                throw new Exception("Por favor, seleccione un tipo de empleado valido.");
            }


            // Si el empleado es permanente
            if (esPermanente) {
                float salarioBase = Float.parseFloat(validarCampo(salarioBaseField.getText(), "Salario Base"));
                String beneficios = validarCampo(beneficiosField.getText(), "Beneficios");
                empleado = new EmpleadoPermanente(id, nombre, puesto, departamento, edad, sexo, fechaContratacion, beneficios, salarioBase);
            } else {  // Si el empleado es temporal
                float pagoPorHora = Float.parseFloat(validarCampo(pagoPorHoraField.getText(), "Pago por Hora"));
                Date fechaFinalContrato = validarFecha(fechaFinalContratoField.getText(), "Fecha Final de Contrato");
                empleado = new EmpleadoTemporal(id, nombre, puesto, departamento, edad, sexo, fechaContratacion, fechaFinalContrato, pagoPorHora);
            }


            // Agregamos el empleado al sistema
            controladorEmpleados.agregarEmpleado(empleado);
            controladorDepartamentos.agregarEmpleadoADepartamento(departamento, empleado);


            // Actualizamos la tabla de empleados
            agregarEmpleadoATabla(empleado);


            // Limpiamos los campos del formulario
            limpiarCampos();


            // Intentamos actualizar el combo de empleados en el panel de reportes
            try {
                panelReportes.actualizarComboEmpleados();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar empleados: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();  // Mostramos el error en la consola
            }


            JOptionPane.showMessageDialog(this, "Empleado agregado exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (EmpleadoYaAsignadoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Asignacion", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numericos validos en los campos correspondientes.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Metodo para agregar un empleado a la tabla
    private void agregarEmpleadoATabla(Empleado empleado) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));


        tableModel.addRow(new Object[]{
                empleado.getId(),
                empleado.getNombre(),
                empleado.getPuesto(),
                empleado.getEdad(),
                empleado.getSexo(),
                formatoFecha.format(empleado.getFechaContratacion()),
                empleado.getTipoEmpleadoString(),
                empleado instanceof EmpleadoPermanente ? ((EmpleadoPermanente) empleado).getSalarioBase() : "N/A",
                empleado instanceof EmpleadoPermanente ? ((EmpleadoPermanente) empleado).getBeneficios() : "N/A",
                empleado instanceof EmpleadoTemporal ? ((EmpleadoTemporal) empleado).getPagoPorHora() : "N/A",
                empleado instanceof EmpleadoTemporal ? formatoFecha.format(((EmpleadoTemporal) empleado).getFechaFinalContrato()) : "N/A",
                empleado.getDepartamento().getId()
        });
    }


    // Metodo para limpiar los campos del formulario
    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        puestoField.setText("");
        edadField.setText("");
        sexoField.setSelectedItem("Seleccione");
        tipoEmpleadoCombo.setSelectedItem("Seleccione");
        salarioBaseField.setText("");
        beneficiosField.setText("");
        pagoPorHoraField.setText("");
        fechaFinalContratoField.setText("");
        fechaContratacionField.setText("");
        departamentoCombo.setSelectedItem("Seleccione");
    }


    // Metodo para validar un campo de texto
    private String validarCampo(String valor, String nombreCampo) throws Exception {
        if (valor == null || valor.trim().isEmpty()) {
            throw new Exception("El campo " + nombreCampo + " es obligatorio.");
        }
        return valor.trim();
    }


    // Metodo para validar una fecha con formato especifico
    private Date validarFecha(String fechaTexto, String nombreCampo) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);  // No permitir fechas invalidas
        try {
            return formato.parse(fechaTexto);
        } catch (ParseException e) {
            throw new Exception("El campo " + nombreCampo + " debe tener el formato dd/MM/yyyy.");
        }
    }


    // Metodo para modificar un empleado
    private void modificarEmpleado() {
        int selectedRow = empleadosTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado para modificar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }


        String id = (String) tableModel.getValueAt(selectedRow, 1);
        empleadoModificado = controladorEmpleados.getEmpleados().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);


        if (empleadoModificado != null) {
            // Cargamos los datos del empleado en los campos del formulario
            idField.setText(empleadoModificado.getId());
            nombreField.setText(empleadoModificado.getNombre());
            puestoField.setText(empleadoModificado.getPuesto());
            edadField.setText(String.valueOf(empleadoModificado.getEdad()));
            sexoField.setSelectedItem(empleadoModificado.getSexo());
            fechaContratacionField.setText(new SimpleDateFormat("dd/MM/yyyy").format(empleadoModificado.getFechaContratacion()));


            // Seleccionamos el tipo de empleado en el combo box
            if (empleadoModificado instanceof EmpleadoPermanente) {
                tipoEmpleadoCombo.setSelectedItem("Permanente");
            } else if (empleadoModificado instanceof EmpleadoTemporal) {
                tipoEmpleadoCombo.setSelectedItem("Temporal");
            }


            guardarCambiosButton.setVisible(true);  // Mostramos el boton de guardar cambios


            // Cargamos los campos especificos del empleado
            if (empleadoModificado instanceof EmpleadoPermanente) {
                salarioBaseField.setText(String.valueOf(((EmpleadoPermanente) empleadoModificado).getSalarioBase()));
                beneficiosField.setText(((EmpleadoPermanente) empleadoModificado).getBeneficios());
                pagoPorHoraField.setVisible(false);
                fechaFinalContratoField.setVisible(false);
            } else if (empleadoModificado instanceof EmpleadoTemporal) {
                pagoPorHoraField.setText(String.valueOf(((EmpleadoTemporal) empleadoModificado).getPagoPorHora()));
                fechaFinalContratoField.setText(new SimpleDateFormat("dd/MM/yyyy").format(((EmpleadoTemporal) empleadoModificado).getFechaFinalContrato()));
                salarioBaseField.setVisible(false);
                beneficiosField.setVisible(false);
            }
            departamentoCombo.setSelectedItem(empleadoModificado.getDepartamento().getId());
        }
    }


    // Metodo para guardar los cambios de un empleado modificado
    private void guardarCambiosEmpleado(Empleado empleado) {
        try {
            // Validamos los campos y actualizamos el empleado
            empleado.setId(validarCampo(idField.getText(), "ID"));
            empleado.setNombre(validarCampo(nombreField.getText(), "Nombre"));
            empleado.setPuesto(validarCampo(puestoField.getText(), "Puesto"));
            empleado.setEdad(Integer.parseInt(validarCampo(edadField.getText(), "Edad")));
            empleado.setSexo((String) sexoField.getSelectedItem());
            empleado.setFechaContratacion(validarFecha(fechaContratacionField.getText(), "Fecha de Contratacion"));


            // Actualizamos los campos especificos segun el tipo de empleado
            if (empleado instanceof EmpleadoPermanente) {
                ((EmpleadoPermanente) empleado).setSalarioBase(Float.parseFloat(validarCampo(salarioBaseField.getText(), "Salario Base")));
                ((EmpleadoPermanente) empleado).setBeneficios(validarCampo(beneficiosField.getText(), "Beneficios"));
            } else if (empleado instanceof EmpleadoTemporal) {
                ((EmpleadoTemporal) empleado).setPagoPorHora(Float.parseFloat(validarCampo(pagoPorHoraField.getText(), "Pago por Hora")));
                ((EmpleadoTemporal) empleado).setFechaFinalContrato(validarFecha(fechaFinalContratoField.getText(), "Fecha Final de Contrato"));
            }


            // Actualizamos la referencia al departamento
            empleado.setDepartamento(controladorDepartamentos.getDepartamentos().stream()
                    .filter(dep -> dep.getId().equals(departamentoCombo.getSelectedItem()))
                    .findFirst()
                    .orElse(null));


            // Actualizamos los datos en la tabla
            int selectedRow = empleadosTable.getSelectedRow();
            tableModel.setValueAt(empleado.getId(), selectedRow, 0);
            tableModel.setValueAt(empleado.getNombre(), selectedRow, 1);
            tableModel.setValueAt(empleado.getPuesto(), selectedRow, 2);
            tableModel.setValueAt(empleado.getEdad(), selectedRow, 3);
            tableModel.setValueAt(empleado.getSexo(), selectedRow, 4);
            tableModel.setValueAt(new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES")).format(empleado.getFechaContratacion()), selectedRow, 5);
            tableModel.setValueAt(empleado.getTipoEmpleadoString(), selectedRow, 6);


            if (empleado instanceof EmpleadoPermanente) {
                tableModel.setValueAt(((EmpleadoPermanente) empleado).getSalarioBase(), selectedRow, 7);
                tableModel.setValueAt(((EmpleadoPermanente) empleado).getBeneficios(), selectedRow, 8);
                tableModel.setValueAt("N/A", selectedRow, 9);  // Pago por hora no aplicable
                tableModel.setValueAt("N/A", selectedRow, 10);  // Fecha final no aplicable
            } else if (empleado instanceof EmpleadoTemporal) {
                tableModel.setValueAt("N/A", selectedRow, 7);  // Salario base no aplicable
                tableModel.setValueAt("N/A", selectedRow, 8);  // Beneficios no aplicables
                tableModel.setValueAt(((EmpleadoTemporal) empleado).getPagoPorHora(), selectedRow, 9);
                tableModel.setValueAt(((EmpleadoTemporal) empleado).getFechaFinalContrato(), selectedRow, 10);
            }


            tableModel.setValueAt(empleado.getDepartamento().getId(), selectedRow, 11);


            JOptionPane.showMessageDialog(this, "Empleado modificado exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            guardarCambiosButton.setVisible(false);  // Ocultamos el boton despues de guardar
            limpiarCampos();  // Limpiamos los campos despues de modificar
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Metodo para eliminar un empleado
    private void eliminarEmpleado() {
        int selectedRow = empleadosTable.getSelectedRow();
        if (selectedRow != -1) {
            String id = (String) tableModel.getValueAt(selectedRow, 0);
            Empleado empleado = controladorEmpleados.getEmpleados().stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (empleado != null) {
                controladorEmpleados.eliminarEmpleado(empleado);
                controladorDepartamentos.eliminarEmpleadoDeDepartamento(empleado.getDepartamento(), empleado);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Empleado eliminado exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }


    // Metodo para actualizar la tabla con los empleados actuales
    private void actualizarTabla() {
        tableModel.setRowCount(0);  // Limpiamos la tabla
        for (Empleado emp : controladorEmpleados.getEmpleados()) {
            agregarEmpleadoATabla(emp);  // Agregamos cada empleado a la tabla
        }
    }


    // Metodo para actualizar el combo box de departamentos (usado por el panel de departamentos)
    public void actualizarComboDepartamentos(String[] departamentosArray) {
        departamentoCombo.removeAllItems();
        departamentoCombo.addItem("Seleccione");
        if (departamentosArray != null) {
            for (String departamentoId : departamentosArray) {
                departamentoCombo.addItem(departamentoId);  // Agregamos cada ID de departamento
            }
        }
    }


    // Metodo para actualizar la vista de empleados en la tabla (usado por el panel de departamentos)
    public void actualizarVistaEmpleados(List<Empleado> empleados) {
        tableModel.setRowCount(0);  // Limpiamos la tabla
        for (Empleado empleado : empleados) {
            // Agregamos cada empleado a la tabla
            tableModel.addRow(new Object[]{
                    empleado.getId(),
                    empleado.getNombre(),
                    empleado.getPuesto(),
                    empleado.getEdad(),
                    empleado.getSexo(),
                    empleado.getFechaContratacion(),
                    empleado.getTipoEmpleadoString(),
                    empleado instanceof EmpleadoPermanente ? ((EmpleadoPermanente) empleado).getSalarioBase() : "N/A",
                    empleado instanceof EmpleadoPermanente ? ((EmpleadoPermanente) empleado).getBeneficios() : "N/A",
                    empleado instanceof EmpleadoTemporal ? ((EmpleadoTemporal) empleado).getPagoPorHora() : "N/A",
                    empleado instanceof EmpleadoTemporal ? ((EmpleadoTemporal) empleado).getFechaFinalContrato() : "N/A",
                    empleado.getDepartamento().getNombre()
            });
        }
    }
}
