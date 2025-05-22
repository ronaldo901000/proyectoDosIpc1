/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.planta;

import com.mycompany.myfarm.backend.producto.Producto;


/**
 *
 * @author ronaldo
 */
public class PlantaFruto extends Planta {
    private int cantidadDeVecesQueDaFrutas;
    private static final int TIEMPO_MINIMO_COSECHAR = 20000;
    private static final int TIEMPO_MAXIMO_COSECHA = 70000;
    private static final int TIEMPO_MINIMO_DESCOMPOSICION = 40000;
    private static final int TIEMPO_MAXIMO_DESCOMPOSICION = 80000;
    private static final int MAXIMO_VECES_EN_DAR_FRUTO=4;
    private static final int MINIMO_VECES_EN_DAR_FRUTO=2;
    private boolean cosechaPendiente;

    public PlantaFruto(String nombre, int cantidadDeSemillas) {
        super(nombre, cantidadDeSemillas);
        tiempoParaCosechar = generarValorAleatorio(TIEMPO_MAXIMO_COSECHA, TIEMPO_MINIMO_COSECHAR);//
        
        cantidadDeVecesQueDaFrutas = generarValorAleatorio(MAXIMO_VECES_EN_DAR_FRUTO, MINIMO_VECES_EN_DAR_FRUTO);
        tiempoParaPudrirse = generarValorAleatorio(TIEMPO_MAXIMO_DESCOMPOSICION, TIEMPO_MINIMO_DESCOMPOSICION);
        
    }

    public void restarUnaRondaDeFrutosHecha() {
        cantidadDeVecesQueDaFrutas--;
    }

    public PlantaFruto(String nombre, int cantidadDeSemillas, Producto producto) {
        super(nombre, cantidadDeSemillas);
        tiempoParaCosechar = generarValorAleatorio(TIEMPO_MAXIMO_COSECHA, TIEMPO_MINIMO_COSECHAR);
        cantidadDeVecesQueDaFrutas = generarValorAleatorio(MAXIMO_VECES_EN_DAR_FRUTO, MINIMO_VECES_EN_DAR_FRUTO);
        tiempoParaPudrirse = generarValorAleatorio(TIEMPO_MAXIMO_DESCOMPOSICION, TIEMPO_MINIMO_DESCOMPOSICION);
        this.producto = producto;
    }

    @Override
    public void run() {
        crecimientoDeLaPlanta();
        iniciarDescomposicion();
    }

    public void reiniciarAtributosPlanta() {
        fueCosechado = false;
        tiempoParaCosechar = generarValorAleatorio(TIEMPO_MAXIMO_COSECHA, TIEMPO_MINIMO_COSECHAR);
        tiempoParaPudrirse = generarValorAleatorio(TIEMPO_MAXIMO_DESCOMPOSICION, TIEMPO_MINIMO_DESCOMPOSICION);
        suelo.getPanel().actualizarIcono(1);
    }

    public int getCantidadDeVecesQueDaFrutas() {
        return cantidadDeVecesQueDaFrutas;
    }

    public void setCantidadDeVecesQueDaFrutas(int cantidadDeVecesQueDaFrutas) {
        this.cantidadDeVecesQueDaFrutas = cantidadDeVecesQueDaFrutas;
    }

    public boolean isCosechaPendiente() {
        return cosechaPendiente;
    }

    public void setCosechaPendiente(boolean cosechaPendiente) {
        this.cosechaPendiente = cosechaPendiente;
    }

    public int getTiempoParaCosechar() {
        return tiempoParaCosechar;
    }

    public void setTiempoParaCosechar(int tiempoParaCosechar) {
        this.tiempoParaCosechar = tiempoParaCosechar;
    }

    public static int getTIEMPO_MINIMO_COSECHAR() {
        return TIEMPO_MINIMO_COSECHAR;
    }

    public static int getTIEMPO_MAXIMO_COSECHA() {
        return TIEMPO_MAXIMO_COSECHA;
    }

    public static int getTIEMPO_MINIMO_DESCOMPOSICION() {
        return TIEMPO_MINIMO_DESCOMPOSICION;
    }

    public static int getTIEMPO_MAXIMO_DESCOMPOSICION() {
        return TIEMPO_MAXIMO_DESCOMPOSICION;
    }

}
