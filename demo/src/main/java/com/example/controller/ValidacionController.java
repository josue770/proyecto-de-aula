package com.example.controller;

public class ValidacionController {
    
    public static boolean validarId(String idStr) {
        if (idStr == null || idStr.trim().isEmpty()) {
            return false;
        }
        if (!idStr.matches("\\d+")) {
            return false;
        }
        try {
            int id = Integer.parseInt(idStr);
            return id >= 10000000 && id <= 9999999999L;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String obtenerErrorId(String idStr) {
        if (idStr == null || idStr.trim().isEmpty()) {
            return "El ID no puede estar vacío.";
        }
        if (!idStr.matches("\\d+")) {
            return "El ID solo debe contener números.";
        }
        if (idStr.length() < 8 || idStr.length() > 10) {
            return "El ID debe tener entre 8 y 10 dígitos. Ingresaste " + idStr.length() + " dígitos.";
        }
        try {
            long id = Long.parseLong(idStr);
            if (id <= 0) {
                return "El ID debe ser mayor a 0.";
            }
        } catch (NumberFormatException e) {
            return "El ID no es válido.";
        }
        return null;
    }

    public static boolean validarFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            return false;
        }
        
        String[] partes = fecha.split("/");
        if (partes.length != 3) {
            return false;
        }
        String dia = partes[0];
        String mes = partes[1];
        String año = partes[2];
        if (!dia.matches("\\d{2}") || !mes.matches("\\d{2}") || !año.matches("\\d{4}")) {
            return false;
        }
        try {
            int diaInt = Integer.parseInt(dia);
            int mesInt = Integer.parseInt(mes);
            int añoInt = Integer.parseInt(año);
            if (diaInt < 1 || diaInt > 31 || mesInt < 1 || mesInt > 12 || añoInt < 2000) {
                return false;
            }
            if ((mesInt == 2 && diaInt > 28) || 
                (mesInt == 4 && diaInt > 30) || 
                (mesInt == 6 && diaInt > 30) || 
                (mesInt == 9 && diaInt > 30) || 
                (mesInt == 11 && diaInt > 30)) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static boolean validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return false;
        }
        return telefono.matches("\\d{7,10}");
    }

    public static boolean validarTextoNoVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static int calcularDiasRestantes(String fechaEntrada, String fechaSalida) {
        String[] partesEntrada = fechaEntrada.split("/");
        String[] partesSalida = fechaSalida.split("/");
        
        int diaEntrada = Integer.parseInt(partesEntrada[0]);
        int mesEntrada = Integer.parseInt(partesEntrada[1]);
        int añoEntrada = Integer.parseInt(partesEntrada[2]);
        
        int diaSalida = Integer.parseInt(partesSalida[0]);
        int mesSalida = Integer.parseInt(partesSalida[1]);
        int añoSalida = Integer.parseInt(partesSalida[2]);
        
        int diasRestantes = 0;
        diasRestantes += (añoSalida - añoEntrada) * 365;
        diasRestantes += (mesSalida - mesEntrada) * 30;
        diasRestantes += (diaSalida - diaEntrada);
        
        return diasRestantes;
    }

    public static boolean validarPeriodoMembresia(String tipoMembresia, String fechaEntrada, String fechaSalida) {
        int diasRestantes = calcularDiasRestantes(fechaEntrada, fechaSalida);
        
        switch (tipoMembresia.toLowerCase()) {
            case "mensual":
                return diasRestantes <= 30;
            case "semanal":
                return diasRestantes <= 7;
            case "diaria":
                return diasRestantes <= 1;
            default:
                return false;
        }
    }
}