package com.example.controller;

import com.example.model.Entrenador;
import java.util.ArrayList;
import java.util.List;

public class EntrenadorController {
    private List<Entrenador> entrenadores;

    public EntrenadorController() {
        this.entrenadores = new ArrayList<>();
    }

    public boolean agregarEntrenador(Entrenador entrenador) {
        for (Entrenador e : entrenadores) {
            if (e.getId() == entrenador.getId()) {
                return false;
            }
        }
        entrenadores.add(entrenador);
        return true;
    }

    public Entrenador obtenerEntrenador(int id) {
        for (Entrenador e : entrenadores) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public List<Entrenador> obtenerTodosEntrenadores() {
        return new ArrayList<>(entrenadores);
    }

    public List<Entrenador> obtenerEntrenadoresActivos() {
        List<Entrenador> resultado = new ArrayList<>();
        for (Entrenador e : entrenadores) {
            if (e.isActivo()) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public boolean actualizarEntrenador(int id, Entrenador entrenadorActualizado) {
        for (int i = 0; i < entrenadores.size(); i++) {
            if (entrenadores.get(i).getId() == id) {
                entrenadores.set(i, entrenadorActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean desactivarEntrenador(int id) {
        Entrenador entrenador = obtenerEntrenador(id);
        if (entrenador != null) {
            entrenador.setActivo(false);
            return true;
        }
        return false;
    }

    public boolean eliminarEntrenador(int id) {
        for (int i = 0; i < entrenadores.size(); i++) {
            if (entrenadores.get(i).getId() == id) {
                entrenadores.remove(i);
                return true;
            }
        }
        return false;
    }

    public int obtenerTotalEntrenadores() {
        return entrenadores.size();
    }

    public List<Entrenador> obtenerEntrenadorPorEspecialidad(String especialidad) {
        List<Entrenador> resultado = new ArrayList<>();
        for (Entrenador e : entrenadores) {
            if (e.getEspecialidad().equalsIgnoreCase(especialidad)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public double obtenerTotalSalarios() {
        double total = 0;
        for (Entrenador e : entrenadores) {
            if (e.isActivo()) {
                total += e.getSalario();
            }
        }
        return total;
    }
}