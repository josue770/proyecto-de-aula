package com.example.model;

public class Entrada {
    private String nombre;
    private int id;
    private String tipoMembresia;
    private String fechaEntrada;
    private String horaEntrada;

    public Entrada(String nombre, int id, String tipoMembresia, String fechaEntrada) {
        this.nombre = nombre;
        this.id = id;
        this.tipoMembresia = tipoMembresia;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = obtenerHoraActual();
    }

    private String obtenerHoraActual() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        return String.format("%02d:%02d:%02d",
            cal.get(java.util.Calendar.HOUR_OF_DAY),
            cal.get(java.util.Calendar.MINUTE),
            cal.get(java.util.Calendar.SECOND));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }
}