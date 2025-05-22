/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.suelo;

import com.mycompany.myfarm.backend.granja.Granja;
import com.mycompany.myfarm.backend.planta.Planta;
import com.mycompany.myfarm.frontend.paneles.Partida.Granja.PanelDisponibilidad;
import com.mycompany.myfarm.frontend.paneles.Partida.Granja.PanelSueloGrama;
/**
 *
 * @author ronaldo
 */
public class SueloGrama extends Suelo {
    private int calidadDelSuelo;
    private boolean estaOcupado;
    private Planta planta;
    private PanelSueloGrama panel;
    private PanelDisponibilidad panelDisponibilidad;
    private boolean seleccionada;
    private boolean estaSucio;
    private boolean yaTieneFertilizante;
    private boolean yaFueRevisado;

    
    public SueloGrama(int[] posicion, Granja granja) {
        super(posicion, granja);
        panelDisponibilidad=new PanelDisponibilidad(this);
        panel=new PanelSueloGrama(this);
    }
    
    public int getCalidadDelSuelo() {
        return calidadDelSuelo;
    }

    public void setCalidadDelSuelo(int calidadDelSuelo) {
        this.calidadDelSuelo = calidadDelSuelo;
    }

    @Override
    public PanelSueloGrama getPanel() {
        return panel;
    }

    public PanelDisponibilidad getPanelDisponibilidad() {
        return panelDisponibilidad;
    }

    public boolean isEstaOcupado() {
        return estaOcupado;
    }

    public void setEstaOcupado(boolean estaOcupado) {
        this.estaOcupado = estaOcupado;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    public boolean isEstaSucio() {
        return estaSucio;
    }

    public void setEstaSucio(boolean estaSucio) {
        this.estaSucio = estaSucio;
    }

    public boolean isYaTieneFertilizante() {
        return yaTieneFertilizante;
    }

    public void setYaTieneFertilizante(boolean yaTieneFertilizante) {
        this.yaTieneFertilizante = yaTieneFertilizante;
    }

    public boolean isYaFueRevisado() {
        return yaFueRevisado;
    }

    public void setYaFueRevisado(boolean yaFueRevisado) {
        this.yaFueRevisado = yaFueRevisado;
    }

    
    
}
