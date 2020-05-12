package com.example.parcial2;

public class Contacto {
    int id;
    int Cedula;
    String Nombre;
    int Estrato;
    String Salario;
    String NivelEducativo;
    public Contacto(){

    }

    public Contacto(int id, int cedula, String nombre, int estrato, String salario, String nivelEducativo) {
        this.id = id;
        Cedula = cedula;
        Nombre = nombre;
        Estrato = estrato;
        Salario = salario;
        NivelEducativo = nivelEducativo;
    }

    public Contacto(int cedula, String nombre, int estrato, String salario, String nivelEducativo) {
        Cedula = cedula;
        Nombre = nombre;
        Estrato = estrato;
        Salario = salario;
        NivelEducativo = nivelEducativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCedula() {
        return Cedula;
    }

    public void setCedula(int cedula) {
        Cedula = cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getEstrato() {
        return Estrato;
    }

    public void setEstrato(int estrato) {
        Estrato = estrato;
    }

    public String getSalario() {
        return Salario;
    }

    public void setSalario(String salario) {
        Salario = salario;
    }

    public String getNivelEducativo() {
        return NivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        NivelEducativo = nivelEducativo;
    }
}
