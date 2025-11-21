package com.example.view;

import com.example.controller.ClienteController;
import com.example.controller.EntrenadorController;
import com.example.controller.EntradaController;
import com.example.model.Constantes;
import com.example.util.DatosGlobales;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportesView extends JFrame {
    private ClienteController clienteController;
    private EntrenadorController entrenadorController;
    private EntradaController entradaController;
    private JTabbedPane tabbedPane;

    public ReportesView() {
        clienteController = DatosGlobales.getClienteController();
        entrenadorController = DatosGlobales.getEntrenadorController();
        entradaController = DatosGlobales.getEntradaController();

        setTitle("Reportes");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("REPORTES DEL SISTEMA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 123, 255));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 12));

        tabbedPane.addTab("Resumen General", crearReporteResumen());
        tabbedPane.addTab("Clientes por Membresía", crearReporteClientesPorMembresia());
        tabbedPane.addTab("Entrenadores", crearReporteEntrenadores());
        tabbedPane.addTab("Ingresos", crearReporteIngresos());

        panelPrincipal.add(tabbedPane, BorderLayout.CENTER);

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

    private JPanel crearReporteResumen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(4, 2, 20, 20));
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel lblTotalClientes = new JLabel("Total de Clientes:");
        lblTotalClientes.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel valorClientes = new JLabel(String.valueOf(clienteController.obtenerTotalClientes()));
        valorClientes.setFont(new Font("Arial", Font.PLAIN, 14));
        valorClientes.setForeground(new Color(40, 167, 69));

        JLabel lblTotalEntrenadores = new JLabel("Total de Entrenadores:");
        lblTotalEntrenadores.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel valorEntrenadores = new JLabel(String.valueOf(entrenadorController.obtenerTotalEntrenadores()));
        valorEntrenadores.setFont(new Font("Arial", Font.PLAIN, 14));
        valorEntrenadores.setForeground(new Color(40, 167, 69));

        JLabel lblTotalEntradas = new JLabel("Total de Entradas:");
        lblTotalEntradas.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel valorEntradas = new JLabel(String.valueOf(entradaController.obtenerTotalEntradas()));
        valorEntradas.setFont(new Font("Arial", Font.PLAIN, 14));
        valorEntradas.setForeground(new Color(40, 167, 69));

        JLabel lblTotalSalarios = new JLabel("Total de Salarios:");
        lblTotalSalarios.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel valorSalarios = new JLabel("$" + (int) entrenadorController.obtenerTotalSalarios());
        valorSalarios.setFont(new Font("Arial", Font.PLAIN, 14));
        valorSalarios.setForeground(new Color(40, 167, 69));

        panelInfo.add(lblTotalClientes);
        panelInfo.add(valorClientes);
        panelInfo.add(lblTotalEntrenadores);
        panelInfo.add(valorEntrenadores);
        panelInfo.add(lblTotalEntradas);
        panelInfo.add(valorEntradas);
        panelInfo.add(lblTotalSalarios);
        panelInfo.add(valorSalarios);

        panel.add(panelInfo, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearReporteClientesPorMembresia() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        String[] columnas = {"Tipo de Membresía", "Cantidad", "Ingresos Mensuales"};
        Object[][] datos = {
            {"Mensual", clienteController.obtenerClientesPorMembresia(Constantes.MEMBRESIA_MENSUAL).size(), "$" + (clienteController.obtenerClientesPorMembresia(Constantes.MEMBRESIA_MENSUAL).size() * Constantes.PRECIO_MENSUAL)},
            {"Semanal", clienteController.obtenerClientesPorMembresia(Constantes.MEMBRESIA_SEMANAL).size(), "$" + (clienteController.obtenerClientesPorMembresia(Constantes.MEMBRESIA_SEMANAL).size() * Constantes.PRECIO_SEMANAL)},
            {"Diaria", clienteController.obtenerClientesPorMembresia(Constantes.MEMBRESIA_DIARIA).size(), "$" + (clienteController.obtenerClientesPorMembresia(Constantes.MEMBRESIA_DIARIA).size() * Constantes.PRECIO_DIARIO)}
        };

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabla = new JTable(modelo);
        tabla.setFont(new Font("Arial", Font.PLAIN, 12));
        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabla.getTableHeader().setBackground(new Color(0, 123, 255));
        tabla.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearReporteEntrenadores() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        String[] columnas = {"Especialidad", "Cantidad"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        java.util.Set<String> especialidadesUnicas = new java.util.HashSet<>();
        for (com.example.model.Entrenador e : entrenadorController.obtenerTodosEntrenadores()) {
            especialidadesUnicas.add(e.getEspecialidad());
        }

        for (String especialidad : especialidadesUnicas) {
            modelo.addRow(new Object[]{especialidad, entrenadorController.obtenerEntrenadorPorEspecialidad(especialidad).size()});
        }

        JTable tabla = new JTable(modelo);
        tabla.setFont(new Font("Arial", Font.PLAIN, 12));
        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabla.getTableHeader().setBackground(new Color(0, 123, 255));
        tabla.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearReporteIngresos() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(7, 1, 20, 20));
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        int ingresoMensual = clienteController.obtenerClientesPorMembresia(Constantes.MEMBRESIA_MENSUAL).size() * Constantes.PRECIO_MENSUAL;
        int ingresoSemanal = clienteController.obtenerClientesPorMembresia(Constantes.MEMBRESIA_SEMANAL).size() * Constantes.PRECIO_SEMANAL;
        int ingresoDiaria = clienteController.obtenerClientesPorMembresia(Constantes.MEMBRESIA_DIARIA).size() * Constantes.PRECIO_DIARIO;
        int ingresoTotal = ingresoMensual + ingresoSemanal + ingresoDiaria;
        
        double totalSalarios = entrenadorController.obtenerTotalSalarios();
        int ingresoNeto = (int) (ingresoTotal - totalSalarios);

        JLabel lblIngresoMensual = new JLabel("Ingresos Membresía Mensual: $" + ingresoMensual);
        lblIngresoMensual.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel lblIngresoSemanal = new JLabel("Ingresos Membresía Semanal: $" + ingresoSemanal);
        lblIngresoSemanal.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel lblIngresoDiaria = new JLabel("Ingresos Membresía Diaria: $" + ingresoDiaria);
        lblIngresoDiaria.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel lblIngresoTotal = new JLabel("Ingreso Total Bruto: $" + ingresoTotal);
        lblIngresoTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblIngresoTotal.setForeground(new Color(0, 123, 255));
        
        JLabel lblTotalSalarios = new JLabel("Total de Salarios (Egresos): $" + (int)totalSalarios);
        lblTotalSalarios.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotalSalarios.setForeground(new Color(220, 53, 69));
        
        JLabel lblEspacio = new JLabel("");
        
        JLabel lblIngresoNeto = new JLabel("INGRESO NETO REAL: $" + ingresoNeto);
        lblIngresoNeto.setFont(new Font("Arial", Font.BOLD, 18));
        lblIngresoNeto.setForeground(new Color(40, 167, 69));

        panelInfo.add(lblIngresoMensual);
        panelInfo.add(lblIngresoSemanal);
        panelInfo.add(lblIngresoDiaria);
        panelInfo.add(lblIngresoTotal);
        panelInfo.add(lblTotalSalarios);
        panelInfo.add(lblEspacio);
        panelInfo.add(lblIngresoNeto);

        panel.add(panelInfo, BorderLayout.CENTER);
        return panel;
    }
}