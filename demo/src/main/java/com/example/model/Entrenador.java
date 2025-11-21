package com.example.model;

public class Entrenador {
    private int id;
    private String nombre;
    private String numero;
    private String correo;
    private String especialidad;
    private double salario;
    private String fechaContratacion;
    private boolean activo;

    public Entrenador(int id, String nombre, String numero, String correo,
                     String especialidad, double salario, String fechaContratacion) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.correo = correo;
        this.especialidad = especialidad;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.activo = true;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numero='" + numero + '\'' +
                ", correo='" + correo + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", salario=" + salario +
                ", fechaContratacion='" + fechaContratacion + '\'' +
                ", activo=" + activo +
                '}';
    }
}