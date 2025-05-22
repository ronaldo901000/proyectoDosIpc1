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
public class ResumenPlanta implements Serializable{
    public static final long serialVersionUID = 845784343;
    private String nombre;
    private int totalSemillasAdquiridas;
    private int celdasSembradas;

    public ResumenPlanta(String nombre, int totalSemillasAdquiridas, int celdasSembradas) {
        this.nombre = nombre;
        this.totalSemillasAdquiridas = totalSemillasAdquiridas;
        this.celdasSembradas = celdasSembradas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTotalSemillasAdquiridas() {
        return totalSemillasAdquiridas;
    }

    public void setTotalSemillasAdquiridas(int totalSemillasAdquiridas) {
        this.totalSemillasAdquiridas = totalSemillasAdquiridas;
    }

    public int getCeldasSembradas() {
        return celdasSembradas;
    }

    public void setCeldasSembradas(int celdasSembradas) {
        this.celdasSembradas = celdasSembradas;
    }

}
