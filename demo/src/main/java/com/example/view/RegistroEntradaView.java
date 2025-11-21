package com.example.view;

import com.example.controller.EntradaController;
import com.example.controller.ValidacionController;
import com.example.model.Entrada;
import javax.swing.*;
import java.awt.*;

public class RegistroEntradaView extends JFrame {
    private EntradaController entradaController;
    private JTextField txtId, txtNombre, txtFechaEntrada;
    private JComboBox<String> cmbMembresia;

    public RegistroEntradaView() {
        entradaController = new EntradaController();
        
        setTitle("Registro de Entrada");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("REGISTRO DE ENTRADA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 123, 255));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        
        JLabel lblId = new JLabel("ID (8-10 dígitos):");
        txtId = new JTextField();
        
        JLabel lblMembresia = new JLabel("Tipo Membresía:");
        String[] membresias = {"Mensual", "Semanal", "Diaria"};
        cmbMembresia = new JComboBox<>(membresias);
        
        JLabel lblFechaEntrada = new JLabel("Fecha Entrada (dd/MM/aaaa):");
        txtFechaEntrada = new JTextField();

        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(lblId);
        panelFormulario.add(txtId);
        panelFormulario.add(lblMembresia);
        panelFormulario.add(cmbMembresia);
        panelFormulario.add(lblFechaEntrada);
        panelFormulario.add(txtFechaEntrada);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelBotones.setBackground(new Color(240, 240, 240));

        JButton btnRegistrar = new JButton("Registrar Entrada");
        btnRegistrar.setBackground(new Color(40, 167, 69));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrar.addActionListener(e -> registrarEntrada());

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(220, 53, 69));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }

    private void registrarEntrada() {
        String nombre = txtNombre.getText().trim();
        String idStr = txtId.getText().trim();
        String tipoMembresia = cmbMembresia.getSelectedItem().toString().toLowerCase();
        String fechaEntrada = txtFechaEntrada.getText().trim();

        if (!ValidacionController.validarTextoNoVacio(nombre)) {
            JOptionPane.showMessageDialog(this, 
                "El nombre no puede estar vacío.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarId(idStr)) {
            JOptionPane.showMessageDialog(this, 
                "ID inválido. Debe tener entre 8 y 10 dígitos.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidacionController.validarFecha(fechaEntrada)) {
            JOptionPane.showMessageDialog(this, 
                "Fecha inválida. Use formato dd/MM/aaaa.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idStr);
        Entrada nuevaEntrada = new Entrada(nombre, id, tipoMembresia, fechaEntrada);

        if (entradaController.registrarEntrada(nuevaEntrada)) {
            JOptionPane.showMessageDialog(this, 
                "Entrada registrada exitosamente", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Ya existe una entrada activa para este cliente.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtId.setText("");
        txtFechaEntrada.setText("");
        cmbMembresia.setSelectedIndex(0);
    }
}