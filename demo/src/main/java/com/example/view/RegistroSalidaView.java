package com.example.view;

import com.example.controller.ValidacionController;
import com.example.model.Constantes;
import javax.swing.*;
import java.awt.*;

public class RegistroSalidaView extends JFrame {
    private JTextField txtId, txtNombre, txtFechaSalida;
    private JComboBox<String> cmbMembresia;
    private JLabel lblPrecio;

    public RegistroSalidaView() {
        setTitle("Registro de Salida");
        setSize(450, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("REGISTRO DE SALIDA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 123, 255));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();

        JLabel lblId = new JLabel("ID (8-10 dígitos):");
        txtId = new JTextField();

        JLabel lblMembresia = new JLabel("Tipo Membresía:");
        String[] membresias = {"Mensual", "Semanal", "Diaria"};
        cmbMembresia = new JComboBox<>(membresias);
        cmbMembresia.addActionListener(e -> actualizarPrecio());

        JLabel lblFechaSalida = new JLabel("Fecha Salida (dd/MM/aaaa):");
        txtFechaSalida = new JTextField();

        JLabel lblMostrarPrecio = new JLabel("Precio:");
        lblPrecio = new JLabel("$0");
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 14));
        lblPrecio.setForeground(new Color(40, 167, 69));

        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(lblId);
        panelFormulario.add(txtId);
        panelFormulario.add(lblMembresia);
        panelFormulario.add(cmbMembresia);
        panelFormulario.add(lblFechaSalida);
        panelFormulario.add(txtFechaSalida);
        panelFormulario.add(lblMostrarPrecio);
        panelFormulario.add(lblPrecio);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelBotones.setBackground(new Color(240, 240, 240));

        JButton btnRegistrar = new JButton("Registrar Salida");
        btnRegistrar.setBackground(new Color(40, 167, 69));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrar.addActionListener(e -> registrarSalida());

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

    private void actualizarPrecio() {
        String tipoMembresia = cmbMembresia.getSelectedItem().toString().toLowerCase();
        int precio = 0;

        switch (tipoMembresia) {
            case "mensual":
                precio = Constantes.PRECIO_MENSUAL;
                break;
            case "semanal":
                precio = Constantes.PRECIO_SEMANAL;
                break;
            case "diaria":
                precio = Constantes.PRECIO_DIARIO;
                break;
        }

        lblPrecio.setText("$" + precio);
    }

    @SuppressWarnings("unused")
    private void registrarSalida() {
        String nombre = txtNombre.getText().trim();
        String idStr = txtId.getText().trim();
        String tipoMembresia = cmbMembresia.getSelectedItem().toString().toLowerCase();
        String fechaSalida = txtFechaSalida.getText().trim();

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

        if (!ValidacionController.validarFecha(fechaSalida)) {
            JOptionPane.showMessageDialog(this,
                "Fecha inválida. Use formato dd/MM/aaaa.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
            "Salida registrada exitosamente\nCliente: " + nombre + "\nPrecio: " + lblPrecio.getText(),
            "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtId.setText("");
        txtFechaSalida.setText("");
        cmbMembresia.setSelectedIndex(0);
    }
}