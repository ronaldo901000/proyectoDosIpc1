/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.partida;

import com.mycompany.myfarm.backend.granja.Granja;
import com.mycompany.myfarm.backend.granjero.Granjero;

/**
 *
 * @author ronaldo
 */
public class Partida {
    private String nombre;
    private Granja granja;
    private int duracionDePartida;
    private Granjero granjero;
    private int duracion;

    public Partida(String nombre, Granja granja, Granjero granjero) {
        this.nombre = nombre;
        this.granja = granja;
        this.granjero = granjero;
    }

    public void iniciarPartida() {
        granjero.setSalioDeLaPartida(false);
        granja.continuarTodo();
        granja.mostrarPanel();
        Thread hilo = new Thread(granjero);
        hilo.start();
    }
    
    public void aumentarDuracionPartida(){
        duracion++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Granja getGranja() {
        return granja;
    }

    public void setGranja(Granja granja) {
        this.granja = granja;
    }

    public int getDuracionDePartida() {
        return duracionDePartida;
    }

    public void setDuracionDePartida(int duracionDePartida) {
        this.duracionDePartida = duracionDePartida;
    }

    public Granjero getGranjero() {
        return granjero;
    }

    public void setGranjero(Granjero granjero) {
        this.granjero = granjero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }



}
