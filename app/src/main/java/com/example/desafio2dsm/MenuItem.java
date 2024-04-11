package com.example.desafio2dsm;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String id;
    private String nombre;
    private double precio;

    public MenuItem() {
        // Default constructor required for Firebase
    }

    public MenuItem(String id, String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
