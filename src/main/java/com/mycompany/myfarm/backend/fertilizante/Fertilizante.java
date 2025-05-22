/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.fertilizante;

import java.io.Serializable;

/**
 *
 * @author ronaldo
 */
public class Fertilizante implements Serializable{
    public static final long serialVersionUID = 83367643;
    private String nombre;
    private int precio;
    private int calidad;

    public Fertilizante(String nombre, int precio, int calidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.calidad = calidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getCalidad() {
        return calidad;
    }
    
    
    
}
