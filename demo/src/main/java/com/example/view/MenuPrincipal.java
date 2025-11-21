package com.example.view;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Sistema de Gestión de Gimnasio");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(45, 45, 45));

        JLabel lblTitulo = new JLabel("SISTEMA DE GIMNASIO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(7, 1, 10, 10));
        panelBotones.setBackground(new Color(45, 45, 45));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton btnRegistroCliente = crearBoton("Registro de Clientes");
        JButton btnRegistroEntrada = crearBoton("Registro de Entrada");
        JButton btnRegistroSalida = crearBoton("Registro de Salida");
        JButton btnConsultarPrecios = crearBoton("Consultar Precios");
        JButton btnEntrenadores = crearBoton("Gestión de Entrenadores");
        JButton btnRecepcion = crearBoton("Recepción");
        JButton btnReportes = crearBoton("Reportes");

        btnRegistroCliente.addActionListener(e -> abrirRegistroCliente());
        btnRegistroEntrada.addActionListener(e -> abrirRegistroEntrada());
        btnRegistroSalida.addActionListener(e -> abrirRegistroSalida());
        btnConsultarPrecios.addActionListener(e -> abrirConsultarPrecios());
        btnEntrenadores.addActionListener(e -> abrirEntrenadores());
        btnRecepcion.addActionListener(e -> abrirRecepcion());
        btnReportes.addActionListener(e -> abrirReportes());

        panelBotones.add(btnRegistroCliente);
        panelBotones.add(btnRegistroEntrada);
        panelBotones.add(btnRegistroSalida);
        panelBotones.add(btnConsultarPrecios);
        panelBotones.add(btnEntrenadores);
        panelBotones.add(btnRecepcion);
        panelBotones.add(btnReportes);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        add(panelPrincipal);
        setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(new Color(0, 123, 255));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(0, 86, 179));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(0, 123, 255));
            }
        });
        
        return boton;
    }

    private void abrirRegistroCliente() {
        new RegistroClienteView();
    }

    private void abrirRegistroEntrada() {
        new RegistroEntradaView();
    }

    private void abrirRegistroSalida() {
        new RegistroSalidaView();
    }

    private void abrirConsultarPrecios() {
        new ConsultarPreciosView();
    }

    private void abrirEntrenadores() {
        new EntrenadoresView();
    }

    private void abrirRecepcion() {
        new RecepcionView();
    }

    private void abrirReportes() {
        new ReportesView();
    }
}