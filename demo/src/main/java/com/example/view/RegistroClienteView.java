package com.example.view;

import com.example.controller.ClienteController;
import com.example.controller.ValidacionController;
import com.example.model.Cliente;
import javax.swing.*;
import java.awt.*;

public class RegistroClienteView extends JFrame {
    private ClienteController clienteController;
    private JTextField txtId, txtNombre, txtNumero, txtDireccion, txtCorreo, txtFechaInicio;
    private JComboBox<String> cmbMembresia;

    public RegistroClienteView() {
        clienteController = new ClienteController();
        
        setTitle("Registro de Clientes");
        setSize(500, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("REGISTRO DE CLIENTES", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 123, 255));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(7, 2, 10, 10));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblId = new JLabel("ID (8-10 dígitos):");
        txtId = new JTextField();
        
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        
        JLabel lblNumero = new JLabel("Número:");
        txtNumero = new JTextField();
        
        JLabel lblDireccion = new JLabel("Dirección:");
        txtDireccion = new JTextField();
        
        JLabel lblCorreo = new JLabel("Correo:");
        txtCorreo = new JTextField();
        
        JLabel lblMembresia = new JLabel("Tipo Membresía:");
        String[] membresias = {"Mensual", "Semanal", "Diaria"};
        cmbMembresia = new JComboBox<>(membresias);
        
        JLabel lblFechaInicio = new JLabel("Fecha Inicio (dd/MM/aaaa):");
        txtFechaInicio = new JTextField();

        panelFormulario.add(lblId);
        panelFormulario.add(txtId);
        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(lblNumero);
        panelFormulario.add(txtNumero);
        panelFormulario.add(lblDireccion);
        panelFormulario.add(txtDireccion);
        panelFormulario.add(lblCorreo);
        panelFormulario.add(txtCorreo);
        panelFormulario.add(lblMembresia);
        panelFormulario.add(cmbMembresia);
        panelFormulario.add(lblFechaInicio);
        panelFormulario.add(txtFechaInicio);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelBotones.setBackground(new Color(240, 240, 240));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(40, 167, 69));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(e -> guardarCliente());

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(220, 53, 69));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }

    private void guardarCliente() {
        String idStr = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String numero = txtNumero.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String correo = txtCorreo.getText().trim();
        String tipoMembresia = cmbMembresia.getSelectedItem().toString().toLowerCase();
        String fechaInicio = txtFechaInicio.getText().trim();

        if (!ValidacionController.validarId(idStr)) {
            JOptionPane.showMessageDialog(this, 
                "ID inválido. Debe tener entre 8 y 10 dígitos.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarTextoNoVacio(nombre)) {
            JOptionPane.showMessageDialog(this, 
                "El nombre no puede estar vacío.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarTelefono(numero)) {
            JOptionPane.showMessageDialog(this, 
                "Número inválido. Debe tener entre 7 y 10 dígitos.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarTextoNoVacio(direccion)) {
            JOptionPane.showMessageDialog(this, 
                "La dirección no puede estar vacía.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarEmail(correo)) {
            JOptionPane.showMessageDialog(this, 
                "Correo electrónico inválido.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarFecha(fechaInicio)) {
            JOptionPane.showMessageDialog(this, 
                "Fecha inválida. Use formato dd/MM/aaaa.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idStr);
        Cliente nuevoCliente = new Cliente(id, nombre, numero, direccion, correo, tipoMembresia, fechaInicio);

        if (clienteController.agregarCliente(nuevoCliente)) {
            JOptionPane.showMessageDialog(this, 
                "Cliente registrado exitosamente", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Ya existe un cliente con ese ID.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtNumero.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtFechaInicio.setText("");
        cmbMembresia.setSelectedIndex(0);
    }
}