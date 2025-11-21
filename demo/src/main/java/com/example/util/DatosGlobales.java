package com.example.util;

import com.example.controller.ClienteController;
import com.example.controller.EntradaController;
import com.example.controller.EntrenadorController;

public class DatosGlobales {
    private static ClienteController clienteController;
    private static EntradaController entradaController;
    private static EntrenadorController entrenadorController;

    static {
        clienteController = new ClienteController();
        entradaController = new EntradaController();
        entrenadorController = new EntrenadorController();
    }

    public static ClienteController getClienteController() {
        return clienteController;
    }

    public static EntradaController getEntradaController() {
        return entradaController;
    }

    public static EntrenadorController getEntrenadorController() {
        return entrenadorController;
    }
}