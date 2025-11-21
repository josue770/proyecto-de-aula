package com.example.controller;

import com.example.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private List<Cliente> clientes;

    public ClienteController() {
        this.clientes = new ArrayList<>();
    }

    public boolean agregarCliente(Cliente cliente) {
        for (Cliente c : clientes) {
            if (c.getId() == cliente.getId()) {
                return false;
            }
        }
        clientes.add(cliente);
        return true;
    }

    public Cliente obtenerCliente(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Cliente> obtenerTodosClientes() {
        return new ArrayList<>(clientes);
    }

    public boolean actualizarCliente(int id, Cliente clienteActualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.set(i, clienteActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarCliente(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.remove(i);
                return true;
            }
        }
        return false;
    }

    public int obtenerTotalClientes() {
        return clientes.size();
    }

    public List<Cliente> obtenerClientesPorMembresia(String tipoMembresia) {
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente c : clientes) {
            if (c.getTipoMembresia().equalsIgnoreCase(tipoMembresia)) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}