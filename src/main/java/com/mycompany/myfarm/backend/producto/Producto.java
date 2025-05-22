/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.producto;

import java.io.Serializable;

/**
 *
 * @author ronaldo
 */
public abstract class Producto implements Serializable {

    public static final long serialVersionUID = 546674663;
    protected int precioDeVenta;
    protected String nombre;
    protected boolean seObtieneConDestace;
    protected int porcetajeDeLaProduccion;

    public Producto(int PrecioDeVenta, String nombre, boolean seObtieneConDestace) {
        this.precioDeVenta = PrecioDeVenta;
        this.nombre = nombre;
        this.seObtieneConDestace=seObtieneConDestace;
    }

    public int getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(int PrecioDeVenta) {
        this.precioDeVenta = PrecioDeVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSeObtieneConDestace() {
        return seObtieneConDestace;
    }

    public void setSeObtieneConDestace(boolean seObtieneConDestace) {
        this.seObtieneConDestace = seObtieneConDestace;
    }

    public void setPorcetajeDeLaProduccion(int porcetajeDeLaProduccion) {
        this.porcetajeDeLaProduccion = porcetajeDeLaProduccion;
    }

    public int getPorcetajeDeLaProduccion() {
        return porcetajeDeLaProduccion;
    }
    
    
}
