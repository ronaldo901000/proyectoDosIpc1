/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.granja;

import com.mycompany.myfarm.backend.suelo.SueloAgua;
import com.mycompany.myfarm.backend.suelo.SueloGrama;

/**
 *
 * @author ronaldo
 */
public class ControladorDeHilosGranja {
    private Granja granja;

    public ControladorDeHilosGranja(Granja granja) {
        this.granja = granja;
    }
 
    public void controlarHilosPesca() {
        for (int i = 0; i < granja.getFilas(); i++) {
            for (int j = 0; j < granja.getColumnas(); j++) {
                if (granja.getSuelos()[i][j] instanceof SueloAgua) {
                    SueloAgua suelo = (SueloAgua) granja.getSuelos()[i][j];
                    if (suelo.isHayBarco()) {
                        if (!suelo.isDetenerse()) {
                            suelo.setDetenerse(true);
                        } else {
                            suelo.setDetenerse(false);
                        }
                    }
                }
            }
        }
    }
    
    
    public void detenerHiloPlantas() {
        for (int i = 0; i < granja.getFilas(); i++) {
            for (int j = 0; j < granja.getColumnas(); j++) {
                if (granja.getSuelos()[i][j] instanceof SueloGrama) {
                    SueloGrama suelo = (SueloGrama) granja.getSuelos()[i][j];
                    if (suelo.getPlanta() != null) {
                        suelo.getPlanta().setDetenerse(true);
                    }
                }
            }
        }
    }

    public void continuarHiloPlantas() {
        for (int i = 0; i < granja.getFilas(); i++) {
            for (int j = 0; j < granja.getColumnas(); j++) {
                if (granja.getSuelos()[i][j] instanceof SueloGrama) {
                    SueloGrama suelo = (SueloGrama) granja.getSuelos()[i][j];
                    if (suelo.getPlanta() != null) {
                        suelo.getPlanta().setDetenerse(false);
                    }
                }
            }
        }
    }
    
    public void detenerHilosParcelas() {
        for (int i = 0; i < granja.getParcelas().getTamaño(); i++) {
            Parcela parcela = granja.getParcelas().obtenerContenido(i);
            if (parcela.getAnimales().getTamaño() > 0) {
                parcela.detenerHiloAnimales();
            }
        }
    }

    public void continuarHilosParcelas() {
        for (int i = 0; i < granja.getParcelas().getTamaño(); i++) {
            Parcela parcela = granja.getParcelas().obtenerContenido(i);
            if (parcela.getAnimales().getTamaño() > 0) {
                parcela.continuarHiloAnimales();
            }
        }
    }
    
    public void terminarTodo(){
        terminarHilosParcelas();
        terminarHilosSiembras();
    }
    public void terminarHilosParcelas() {
        for (int i = 0; i < granja.getParcelas().getTamaño(); i++) {
            Parcela parcela = granja.getParcelas().obtenerContenido(i);
            if (parcela.getAnimales().getTamaño() > 0) {
                parcela.terminarHiloAnimales();
            }
        }
    }

    public void terminarHilosSiembras() {
        for (int i = 0; i < granja.getFilas(); i++) {
            for (int j = 0; j < granja.getColumnas(); j++) {
                if (granja.getSuelos()[i][j] instanceof SueloGrama) {
                    SueloGrama suelo = (SueloGrama) granja.getSuelos()[i][j];
                    if (suelo.getPlanta() != null) {
                        suelo.getPlanta().setSeAcaboPartida(true);
                    }
                }
            }
        }
    }

    public Granja getGranja() {
        return granja;
    }

    public void setGranja(Granja granja) {
        this.granja = granja;
    }
    
    
}
