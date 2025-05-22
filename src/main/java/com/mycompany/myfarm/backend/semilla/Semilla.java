/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.semilla;

import com.mycompany.myfarm.backend.planta.Planta;
import java.io.Serializable;

/**
 *
 * @author ronaldo
 */
public class Semilla implements Serializable{
    public static final long serialVersionUID = 823737359;
    private String nombre;
    private int precioDeCompra;
    private Planta planta;
    private int semillasTotalesAdquiridas;

    public Semilla(Planta planta,String nombre, int precioDeCompra) {
        this.nombre = nombre;
        this.precioDeCompra = precioDeCompra;
        this.planta=planta;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecioDeCompra() {
        return precioDeCompra;
    }

    public Planta getPlanta() {
        return planta;
    }
    
    public void agregarSemillasAdquiridas(int cantidad){
        semillasTotalesAdquiridas+=cantidad;
    }
    

}
