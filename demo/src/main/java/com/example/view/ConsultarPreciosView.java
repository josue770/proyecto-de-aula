package com.example.view;

import com.example.model.Constantes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConsultarPreciosView extends JFrame {
    private JTable tablaPrecios;

    public ConsultarPreciosView() {
        setTitle("Consultar Precios");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("TARIFAS Y PRECIOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 123, 255));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        String[] columnas = {"Tipo de Membresía", "Precio", "Devolución"};
        Object[][] datos = {
            {"Mensual", "$" + Constantes.PRECIO_MENSUAL, "$" + (int)Constantes.DEVOLUCION_MENSUAL},
            {"Semanal", "$" + Constantes.PRECIO_SEMANAL, "$" + (int)Constantes.DEVOLUCION_SEMANAL},
            {"Diaria", "$" + Constantes.PRECIO_DIARIO, "$" + (int)Constantes.DEVOLUCION_DIARIA}
        };

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaPrecios = new JTable(modelo);
        tablaPrecios.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaPrecios.setRowHeight(30);
        tablaPrecios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaPrecios.getTableHeader().setBackground(new Color(0, 123, 255));
        tablaPrecios.getTableHeader().setForeground(Color.WHITE);
        tablaPrecios.setSelectionBackground(new Color(0, 123, 255));

        JScrollPane scrollPane = new JScrollPane(tablaPrecios);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BorderLayout());
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        JLabel lblInfo = new JLabel(
            "<html><b>Información:</b><br>" +
            "- Mensual: Acceso completo durante 30 días<br>" +
            "- Semanal: Acceso completo durante 7 días<br>" +
            "- Diaria: Acceso de un día completo</html>"
        );
        lblInfo.setFont(new Font("Arial", Font.PLAIN, 11));
        panelInfo.add(lblInfo, BorderLayout.WEST);

        panelPrincipal.add(panelInfo, BorderLayout.SOUTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(new Color(240, 240, 240));

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(220, 53, 69));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnCerrar);

        add(panelPrincipal);
        setVisible(true);
    }
}