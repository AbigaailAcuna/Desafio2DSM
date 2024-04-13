package com.example.desafio2dsm;

public class HistorialItem {
    private String nombre;
    private double precio;
    //private int cantidad;
    //private double subtotal;
    private String fecha;

    public HistorialItem() {
        // Constructor vacío requerido por Firebase
    }

    public HistorialItem(String nombre, double precio/*, int cantidad, double subtotal*/, String fecha) {
        this.nombre = nombre;
        this.precio = precio;
        //this.cantidad = cantidad;
       // this.subtotal = subtotal;
        this.fecha = fecha;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
