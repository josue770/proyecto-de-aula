package com.example.view;

import com.example.controller.ClienteController;
import com.example.controller.ValidacionController;
import com.example.model.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RecepcionView extends JFrame {
    private ClienteController clienteController;
    private JTextField txtBuscarId;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    public RecepcionView() {
        clienteController = new ClienteController();

        setTitle("Recepción");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("RECEPCIÓN - CONSULTA DE CLIENTES", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 123, 255));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel lblBuscar = new JLabel("Buscar por ID:");
        txtBuscarId = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(0, 123, 255));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(e -> buscarCliente());

        JButton btnMostrarTodos = new JButton("Mostrar Todos");
        btnMostrarTodos.setBackground(new Color(40, 167, 69));
        btnMostrarTodos.setForeground(Color.WHITE);
        btnMostrarTodos.setFont(new Font("Arial", Font.BOLD, 12));
        btnMostrarTodos.setFocusPainted(false);
        btnMostrarTodos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMostrarTodos.addActionListener(e -> mostrarTodosClientes());

        panelBusqueda.add(lblBuscar);
        panelBusqueda.add(txtBuscarId);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnMostrarTodos);

        panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre", "Número", "Correo", "Membresía", "Inicio", "Vencimiento", "Días Restantes"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setFont(new Font("Arial", Font.PLAIN, 10));
        tablaClientes.setRowHeight(25);
        tablaClientes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));
        tablaClientes.getTableHeader().setBackground(new Color(0, 123, 255));
        tablaClientes.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setBackground(new Color(240, 240, 240));
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelBotones.setBackground(new Color(240, 240, 240));

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(220, 53, 69));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }

    private void buscarCliente() {
        String idStr = txtBuscarId.getText().trim();

        if (!ValidacionController.validarId(idStr)) {
            JOptionPane.showMessageDialog(this, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idStr);
        Cliente cliente = clienteController.obtenerCliente(id);

        modeloTabla.setRowCount(0);

        if (cliente != null) {
            modeloTabla.addRow(new Object[]{
                cliente.getId(),
                cliente.getNombre(),
                cliente.getNumero(),
                cliente.getCorreo(),
                cliente.getTipoMembresia(),
                cliente.getFechaInicio(),
                cliente.getFechaVencimiento(),
                cliente.getDiasRestantes()
            });
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void mostrarTodosClientes() {
        modeloTabla.setRowCount(0);
        for (Cliente cliente : clienteController.obtenerTodosClientes()) {
            modeloTabla.addRow(new Object[]{
                cliente.getId(),
                cliente.getNombre(),
                cliente.getNumero(),
                cliente.getCorreo(),
                cliente.getTipoMembresia(),
                cliente.getFechaInicio(),
                cliente.getFechaVencimiento(),
                cliente.getDiasRestantes()
            });
        }
    }
}