/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.producto;

/**
 *
 * @author ronaldo
 */
public class Alimento extends Producto {

    private boolean paraHervivoros;

    public Alimento(int PrecioDeVenta, String nombre, boolean seObtieneConDestace, boolean paraHervivoros) {
        super(PrecioDeVenta, nombre, seObtieneConDestace);
        this.paraHervivoros=paraHervivoros;
    }

    public boolean isParaHervivoros() {
        return paraHervivoros;
    }

    public void setParaHervivoros(boolean paraHervivoros) {
        this.paraHervivoros = paraHervivoros;
    }

}
