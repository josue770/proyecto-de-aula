package com.example.model;

public class Cliente {
    private int id;
    private String nombre;
    private String numero;
    private String direccion;
    private String correo;
    private String tipoMembresia;
    private String fechaInicio;
    private String fechaVencimiento;

    public Cliente(int id, String nombre, String numero, String direccion, 
                   String correo, String tipoMembresia, String fechaInicio) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.direccion = direccion;
        this.correo = correo;
        this.tipoMembresia = tipoMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = calcularFechaVencimiento(fechaInicio, tipoMembresia);
    }

    private String calcularFechaVencimiento(String fechaInicio, String tipo) {
        String[] partes = fechaInicio.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);

        switch (tipo.toLowerCase()) {
            case "mensual":
                mes += 1;
                if (mes > 12) {
                    mes = 1;
                    anio += 1;
                }
                break;
            case "semanal":
                dia += 7;
                if (dia > 30) {
                    dia -= 30;
                    mes += 1;
                    if (mes > 12) {
                        mes = 1;
                        anio += 1;
                    }
                }
                break;
            case "diaria":
                dia += 1;
                if (dia > 30) {
                    dia = 1;
                    mes += 1;
                    if (mes > 12) {
                        mes = 1;
                        anio += 1;
                    }
                }
                break;
        }

        return String.format("%02d/%02d/%d", dia, mes, anio);
    }

    public int getDiasRestantes() {
        String[] partesActual = obtenerFechaActual().split("/");
        String[] partesVencimiento = fechaVencimiento.split("/");
        
        int diaActual = Integer.parseInt(partesActual[0]);
        int mesActual = Integer.parseInt(partesActual[1]);
        int anioActual = Integer.parseInt(partesActual[2]);
        
        int diaVencimiento = Integer.parseInt(partesVencimiento[0]);
        int mesVencimiento = Integer.parseInt(partesVencimiento[1]);
        int anioVencimiento = Integer.parseInt(partesVencimiento[2]);
        
        int diasRestantes = 0;
        diasRestantes += (anioVencimiento - anioActual) * 365;
        diasRestantes += (mesVencimiento - mesActual) * 30;
        diasRestantes += (diaVencimiento - diaActual);
        
        return diasRestantes;
    }

    private String obtenerFechaActual() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        return String.format("%02d/%02d/%d", 
            cal.get(java.util.Calendar.DAY_OF_MONTH),
            cal.get(java.util.Calendar.MONTH) + 1,
            cal.get(java.util.Calendar.YEAR));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numero='" + numero + '\'' +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", tipoMembresia='" + tipoMembresia + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                '}';
    }
}