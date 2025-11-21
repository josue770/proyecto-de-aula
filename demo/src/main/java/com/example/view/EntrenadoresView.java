package com.example.view;

import com.example.controller.EntrenadorController;
import com.example.controller.ValidacionController;
import com.example.model.Entrenador;
import com.example.util.DatosGlobales;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EntrenadoresView extends JFrame {
    private EntrenadorController entrenadorController;
    private JTextField txtId, txtNombre, txtNumero, txtCorreo, txtEspecialidad, txtSalario, txtFechaContratacion;
    private JTable tablaEntrenadores;
    private DefaultTableModel modeloTabla;

    public EntrenadoresView() {
        entrenadorController = DatosGlobales.getEntrenadorController();

        setTitle("Gestión de Entrenadores");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("GESTIÓN DE ENTRENADORES", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 123, 255));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(4, 4, 10, 10));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        panelFormulario.add(new JLabel("ID:"));
        txtId = new JTextField();
        panelFormulario.add(txtId);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Número:"));
        txtNumero = new JTextField();
        panelFormulario.add(txtNumero);

        panelFormulario.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelFormulario.add(txtCorreo);

        panelFormulario.add(new JLabel("Especialidad:"));
        txtEspecialidad = new JTextField();
        panelFormulario.add(txtEspecialidad);

        panelFormulario.add(new JLabel("Salario:"));
        txtSalario = new JTextField();
        panelFormulario.add(txtSalario);

        panelFormulario.add(new JLabel("Fecha Contratación (dd/MM/aaaa):"));
        txtFechaContratacion = new JTextField();
        panelFormulario.add(txtFechaContratacion);

        panelFormulario.add(new JLabel(""));

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BorderLayout());
        panelCentro.setBackground(new Color(240, 240, 240));
        panelCentro.add(panelFormulario, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre", "Número", "Correo", "Especialidad", "Salario", "Fecha Contratación", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaEntrenadores = new JTable(modeloTabla);
        tablaEntrenadores.setFont(new Font("Arial", Font.PLAIN, 11));
        tablaEntrenadores.setRowHeight(25);
        tablaEntrenadores.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));
        tablaEntrenadores.getTableHeader().setBackground(new Color(0, 123, 255));
        tablaEntrenadores.getTableHeader().setForeground(Color.WHITE);
        tablaEntrenadores.setSelectionBackground(new Color(0, 123, 255));

        JScrollPane scrollPane = new JScrollPane(tablaEntrenadores);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelCentro.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(240, 240, 240));

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(new Color(40, 167, 69));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAgregar.setFocusPainted(false);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(e -> agregarEntrenador());

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBackground(new Color(0, 123, 255));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnActualizar.addActionListener(e -> actualizarEntrenador());

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(220, 53, 69));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(e -> eliminarEntrenador());

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(108, 117, 125));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
        
        cargarEntrenadores();
        
        setVisible(true);
    }

    private void cargarEntrenadores() {
        modeloTabla.setRowCount(0);
        for (Entrenador entrenador : entrenadorController.obtenerTodosEntrenadores()) {
            modeloTabla.addRow(new Object[]{
                entrenador.getId(),
                entrenador.getNombre(),
                entrenador.getNumero(),
                entrenador.getCorreo(),
                entrenador.getEspecialidad(),
                "$" + entrenador.getSalario(),
                entrenador.getFechaContratacion(),
                entrenador.isActivo() ? "Activo" : "Inactivo"
            });
        }
    }

    private void agregarEntrenador() {
        String idStr = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String numero = txtNumero.getText().trim();
        String correo = txtCorreo.getText().trim();
        String especialidad = txtEspecialidad.getText().trim();
        String salarioStr = txtSalario.getText().trim();
        String fechaContratacion = txtFechaContratacion.getText().trim();

        String errorId = ValidacionController.obtenerErrorId(idStr);
        if (errorId != null) {
            JOptionPane.showMessageDialog(this, errorId, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarTextoNoVacio(nombre)) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarTelefono(numero)) {
            JOptionPane.showMessageDialog(this, "Número inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarEmail(correo)) {
            JOptionPane.showMessageDialog(this, "Correo inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarTextoNoVacio(especialidad)) {
            JOptionPane.showMessageDialog(this, "La especialidad no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarTextoNoVacio(salarioStr) || !salarioStr.matches("\\d+(\\.\\d{1,2})?")) {
            JOptionPane.showMessageDialog(this, "Salario inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarFecha(fechaContratacion)) {
            JOptionPane.showMessageDialog(this, "Fecha inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idStr);
        double salario = Double.parseDouble(salarioStr);
        Entrenador nuevoEntrenador = new Entrenador(id, nombre, numero, correo, especialidad, salario, fechaContratacion);

        if (entrenadorController.agregarEntrenador(nuevoEntrenador)) {
            JOptionPane.showMessageDialog(this, "Entrenador agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarEntrenadores();
        } else {
            JOptionPane.showMessageDialog(this, "Ya existe un entrenador con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarEntrenador() {
        int filaSeleccionada = tablaEntrenadores.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un entrenador para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idStr = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String numero = txtNumero.getText().trim();
        String correo = txtCorreo.getText().trim();
        String especialidad = txtEspecialidad.getText().trim();
        String salarioStr = txtSalario.getText().trim();
        String fechaContratacion = txtFechaContratacion.getText().trim();

        String errorId = ValidacionController.obtenerErrorId(idStr);
        if (errorId != null) {
            JOptionPane.showMessageDialog(this, errorId, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarTextoNoVacio(nombre)) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarTelefono(numero)) {
            JOptionPane.showMessageDialog(this, "Número inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarEmail(correo)) {
            JOptionPane.showMessageDialog(this, "Correo inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarTextoNoVacio(especialidad)) {
            JOptionPane.showMessageDialog(this, "La especialidad no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarTextoNoVacio(salarioStr) || !salarioStr.matches("\\d+(\\.\\d{1,2})?")) {
            JOptionPane.showMessageDialog(this, "Salario inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarFecha(fechaContratacion)) {
            JOptionPane.showMessageDialog(this, "Fecha inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idStr);
        double salario = Double.parseDouble(salarioStr);
        Entrenador entrenadorActualizado = new Entrenador(id, nombre, numero, correo, especialidad, salario, fechaContratacion);

        if (entrenadorController.actualizarEntrenador(id, entrenadorActualizado)) {
            JOptionPane.showMessageDialog(this, "Entrenador actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarEntrenadores();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el entrenador.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEntrenador() {
        int filaSeleccionada = tablaEntrenadores.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un entrenador para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        if (entrenadorController.eliminarEntrenador(id)) {
            JOptionPane.showMessageDialog(this, "Entrenador eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarEntrenadores();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el entrenador.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtNumero.setText("");
        txtCorreo.setText("");
        txtEspecialidad.setText("");
        txtSalario.setText("");
        txtFechaContratacion.setText("");
    }
}