package com.example.controller;

import com.example.model.Entrada;
import java.util.ArrayList;
import java.util.List;

public class EntradaController {
    private List<Entrada> entradas;

    public EntradaController() {
        this.entradas = new ArrayList<>();
    }

    public boolean registrarEntrada(Entrada entrada) {
        for (Entrada e : entradas) {
            if (e.getId() == entrada.getId()) {
                return false;
            }
        }
        entradas.add(entrada);
        return true;
    }

    public Entrada obtenerEntrada(int id) {
        for (Entrada e : entradas) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public List<Entrada> obtenerTodasEntradas() {
        return new ArrayList<>(entradas);
    }

    public boolean eliminarEntrada(int id) {
        for (int i = 0; i < entradas.size(); i++) {
            if (entradas.get(i).getId() == id) {
                entradas.remove(i);
                return true;
            }
        }
        return false;
    }

    public int obtenerTotalEntradas() {
        return entradas.size();
    }

    public List<Entrada> obtenerEntradasPorTipoMembresia(String tipoMembresia) {
        List<Entrada> resultado = new ArrayList<>();
        for (Entrada e : entradas) {
            if (e.getTipoMembresia().equalsIgnoreCase(tipoMembresia)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public boolean verificarEntradaActiva(int id) {
        return obtenerEntrada(id) != null;
    }
}