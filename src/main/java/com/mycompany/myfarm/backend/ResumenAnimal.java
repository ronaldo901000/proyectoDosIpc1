/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend;

import java.io.Serializable;

/**
 *
 * @author ronaldo
 */
public class ResumenAnimal implements Serializable{
    public static final long serialVersionUID = 53574567;
    private String nombreAnimal;
    private int cantidadCriasVendidas;
    private int cantidadUnidadesDestazada;

    public ResumenAnimal(String nombrePlanta, int cantidadCriasVendidas, int cantidadUnidadesDestazada) {
        this.nombreAnimal = nombrePlanta;
        this.cantidadCriasVendidas = cantidadCriasVendidas;
        this.cantidadUnidadesDestazada = cantidadUnidadesDestazada;
    }

    public String getNombrePlanta() {
        return nombreAnimal;
    }

    public void setNombrePlanta(String nombrePlanta) {
        this.nombreAnimal = nombrePlanta;
    }

    public int getCantidadCriasVendidas() {
        return cantidadCriasVendidas;
    }

    public void setCantidadCriasVendidas(int cantidadCriasVendidas) {
        this.cantidadCriasVendidas = cantidadCriasVendidas;
    }

    public int getCantidadUnidadesDestazada() {
        return cantidadUnidadesDestazada;
    }

    public void setCantidadUnidadesDestazada(int cantidadUnidadesDestazada) {
        this.cantidadUnidadesDestazada = cantidadUnidadesDestazada;
    }
    
    

}
