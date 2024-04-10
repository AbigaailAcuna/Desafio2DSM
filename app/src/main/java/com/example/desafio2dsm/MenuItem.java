package com.example.desafio2dsm;
public class MenuItem {
    private String nombre;
    private double precio;

    public MenuItem() {
        // Default constructor required for Firebase
    }

    public MenuItem(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
