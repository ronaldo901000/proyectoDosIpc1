/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.suelo;

import com.mycompany.myfarm.backend.granja.Granja;
import com.mycompany.myfarm.frontend.paneles.Partida.Granja.PanelSueloDesierto;
import javax.swing.JPanel;

/**
 *
 * @author ronaldo
 */
public class SueloDesierto extends Suelo{
    public SueloDesierto(int[] posicion, Granja granja) {
        super(posicion, granja);
    }

    public int[] getPosicion() {
        return posicion;
    }

    public void setPosicion(int[] posicion) {
        this.posicion = posicion;
    }

    public Granja getGranja() {
        return granja;
    }

    public void setGranja(Granja granja) {
        this.granja = granja;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public JPanel getPanel() {
        return new PanelSueloDesierto(this);
    }

}
